package task_01;
/** 
* Task 01:
* "IP Addresses"
*/

import java.util.Scanner;

public class IpAddressToIntConverter {
	public static void main(String[] args) throws Exception {
		IpAddressToIntConverter con = new IpAddressToIntConverter();

		Scanner in = new Scanner(System.in);
		System.out.print("Input IP adress: ");
		String ip = in.nextLine();

		System.out.println("IP Address convert to int32: " + con.ipToInt(ip));

		System.out.print("Input int32 number: ");
		int num = in.nextInt();
		in.close();

		System.out.println("IP Address: " + con.intToIp(num));

	}

	public int ipToInt(String ipAddress) throws Exception {
		int result = 0;
		String[] ipAddressArr = null;
		Boolean err = false;

		try {
			if (ipAddress != null && !ipAddress.isEmpty()) {
				ipAddressArr = ipAddress.split("\\.");

				if (ipAddressArr.length != 4 || ipAddress.endsWith(".") || ipAddress.startsWith(".")) {
					throw new Exception("You entered not 4 values or incorrectly separated them");
				}
				for (int i = ipAddressArr.length - 1; i >= 0; i--) {
					int ip = Integer.parseInt(ipAddressArr[3 - i]);
					if (ip < 0 || ip > 255) {
						err = true;
						break;
					}
					result |= ip << (i * 8);
				}
			}
		} catch (NumberFormatException ex) {
			err = true;
		}
		if (err) {
			System.out.println("invalid input by value! Exit");
			System.exit(1);
		}
		return result;
	}

	public String intToIp(int ip) {
		StringBuilder sb = new StringBuilder(15);

		for (int i = 0; i < 4; i++) {
			sb.insert(0, Long.toString(ip & 0xff));
			if (i < 3) {
				sb.insert(0, '.');
			}
			ip = ip >> 8;
		}
		return sb.toString();
	}
}
