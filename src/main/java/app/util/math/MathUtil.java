package app.util.math;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MathUtil {
	
	/**
	 * Display double in 2 decimal points
	 */
	private static DecimalFormat df2 = new DecimalFormat(".##");
	 
	public static void main(String[] args) {
 
		double input = 32.123456;
		System.out.println("double : " + input);
		System.out.println("double (default) : " + df2.format(input));
 
		df2.setRoundingMode(RoundingMode.UP);
		System.out.println("double (UP) : " + df2.format(input));
 
		df2.setRoundingMode(RoundingMode.DOWN);
		System.out.println("double (DOWN) : " + df2.format(input));
		
		System.out.println("double : " + String.format("%.2f", input));
 
	}
}
