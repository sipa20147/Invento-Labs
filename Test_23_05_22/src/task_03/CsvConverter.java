package task_03;

/**
* Task 03:
* "Unauthorized deliveries"
*/
import java.io.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class CsvConverter {

	public static void main(String[] args) {	

		System.out.println("Input connection param: ");
		Scanner in = new Scanner(System.in);
		System.out.print("Input jdbcURL: ");
		String jdbcURL = in.nextLine();

		System.out.print("Input username: ");
		String username = in.nextLine();
		
		System.out.print("Input password: ");
		String password = in.nextLine();	
		in.close();
/*
		String jdbcURL = "jdbc:mysql://localhost:3306/db_test";
		String username = "root";
		String password = "123456";
*/
		String csvFilePathLogins = "logins.csv";
		String csvFilePathPosting = "postings.csv";

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			System.out.println("Connection succesfull!");
		} catch (Exception ex) {
			System.out.println("Connection failed...");
			System.out.println(ex);
			System.exit(1);
		}

		try {

			connection = DriverManager.getConnection(jdbcURL, username, password);
			connection.setAutoCommit(false);

			BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePathLogins));
			String lineText = null;

			HashSet<String> activeUserName = new HashSet<>();
			HashMap<String, Integer> applications = new HashMap<String, Integer>();
			HashMap<String, Integer> jobs = new HashMap<String, Integer>();
			HashMap<String, Integer> departments = new HashMap<String, Integer>();

			HashMap<String, Integer> employees = new HashMap<String, Integer>();

			InsertDataToSql insertData = new InsertDataToSql();

			lineReader.readLine();

			while ((lineText = lineReader.readLine()) != null) {
				String[] data = lineText.split(",");
				if (data.length != 5) {
					throw new Exception("incorrect number of columns in read file");
				}
				String application = data[0];
				String appAccountName = data[1];
				boolean isActive = Boolean.parseBoolean(data[2]);
				String jobTitle = data[3];
				String departament = data[4];

				if (isActive) {
					activeUserName.add(appAccountName);
				}
				if (!applications.containsKey(application)) {
					applications.put(application, applications.size() + 1);
					insertData.insertToApplication(connection,
							new Application(applications.get(application).intValue(), application));
				}

				if (!jobs.containsKey(jobTitle)) {
					jobs.put(jobTitle, jobs.size() + 1);
					insertData.insertToJob(connection, new Job(jobs.get(jobTitle).intValue(), jobTitle));
				}
				if (!departments.containsKey(departament)) {
					departments.put(departament, departments.size() + 1);
					insertData.insertToDepartament(connection,
							new Departament(departments.get(departament).intValue(), departament));
				}
				if (!employees.containsKey(appAccountName)) {
					employees.put(appAccountName, employees.size() + 1);
					insertData.insertToEmployee(connection,
							new Employee(employees.get(appAccountName).intValue(), appAccountName, isActive,
									applications.get(application).intValue(), jobs.get(jobTitle).intValue(),
									departments.get(departament).intValue()));
				}
			}

			lineReader.close();

			int supplyItemPk = 0;
			lineReader = new BufferedReader(new FileReader(csvFilePathPosting));
			lineText = null;

			HashMap<String, Integer> SupplyMatDocToId = new HashMap<String, Integer>();
			HashMap<String, Integer> materials = new HashMap<String, Integer>();
			HashMap<String, Integer> measurements = new HashMap<String, Integer>();
			HashMap<String, Integer> currencies = new HashMap<String, Integer>();

			lineReader.readLine();
			lineReader.readLine();
			while ((lineText = lineReader.readLine()) != null) {
				supplyItemPk++;
				String[] data = lineText.split(";");

				if (data.length != 10) {
					throw new Exception("incorrect number of columns in read file");
				}

				String matDoc = data[0];
				int item = Integer.parseInt(data[1].trim());
				Date docDate = new SimpleDateFormat("dd.MM.yyyy").parse(data[2].trim());
				Date pstngDate = new SimpleDateFormat("dd.MM.yyyy").parse(data[3].trim());
				String materialDescription = data[4].trim();
				int quantity = Integer.parseInt(data[5].trim());
				String bun = data[6].trim();

				NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
				Number number = format.parse(data[7].trim());
				Double amount = number.doubleValue();

				String lcCrcy = data[8].trim();
				String userName = data[9].trim();

				if (!SupplyMatDocToId.containsKey(matDoc)) {
					SupplyMatDocToId.put(matDoc, SupplyMatDocToId.size() + 1);
					insertData.insertToSupply(connection,
							new Supply(SupplyMatDocToId.get(matDoc).intValue(), matDoc, docDate, pstngDate,
									employees.getOrDefault(userName, Integer.valueOf(0)).intValue(),
									activeUserName.contains(userName)));
				}

				if (!materials.containsKey(materialDescription)) {
					materials.put(materialDescription, materials.size() + 1);
					insertData.insertToMaterial(connection,
							new Material(materials.get(materialDescription).intValue(), materialDescription));
				}

				if (!measurements.containsKey(bun)) {
					measurements.put(bun, measurements.size() + 1);
					insertData.insertToMeasurement(connection,
							new Measurement(measurements.get(bun).intValue(), bun));
				}

				if (!currencies.containsKey(lcCrcy)) {
					currencies.put(lcCrcy, currencies.size() + 1);
					insertData.insertToCurrency(connection,
							new Currency(currencies.get(lcCrcy).intValue(), lcCrcy));
				}

				insertData.insertToSupplyItem(connection,
						new SupplyItem(
								supplyItemPk, item, quantity, measurements.get(bun).intValue(),
								amount, SupplyMatDocToId.get(matDoc).intValue(),
								materials.get(materialDescription).intValue(),
								currencies.get(lcCrcy).intValue()));
			}

			lineReader.close();
			connection.commit();
			connection.close();

		} catch (IOException ex) {
			System.err.println(ex);
		} catch (SQLException ex) {
			ex.printStackTrace();

			try {
				if (connection != null) {
					connection.rollback();
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("well done");
	}
}
