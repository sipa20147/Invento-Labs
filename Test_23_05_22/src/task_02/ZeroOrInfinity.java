package task_02;
/** 
* Task 02:
* "Approaches zero or infinity"
*/

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class ZeroOrInfinity {
	public static void main(String[] args) {
		ZeroOrInfinity fact = new ZeroOrInfinity();
		int num = 0;
		Boolean check = false;
		Scanner in = new Scanner(System.in);
		System.out.print("Input num > 1: ");

		while (!check) {
			num = in.nextInt();
			if (num > 0)
				check = true;
		}
		in.close();

		System.out.println("un = " + fact.calcExpression(num));
		BigDecimal scaled = fact.calcExpression(1000).setScale(0, RoundingMode.HALF_UP);
		System.out.println("Result is tends to: " + scaled);

	}

	public BigDecimal calcExpression(int s) {
		BigDecimal factSum = BigDecimal.ZERO;

		for (int i = 1; i <= s; i++) {
			factSum = factSum.add(this.getFactorial(i));
		}
		MathContext context = new MathContext(6, RoundingMode.HALF_UP);
		BigDecimal un = new BigDecimal(
				(BigDecimal.ONE.divide(this.getFactorial(s), context).multiply(factSum)).toString(), context);
		return un;
	}

	public BigDecimal getFactorial(int f) {
		BigDecimal result = BigDecimal.ONE;

		if (f == 1)
			return result;

		for (int i = 1; i <= f; i++)
			result = result.multiply(BigDecimal.valueOf(i));
		return result;
	}

}
