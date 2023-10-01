package b07lab2fix;

import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws IOException
	{
		// Testing empty case
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		// Testing add
		System.out.println("Testing add");
//		double[] c1 = {-2, 6, 5};
//		int[] e1 = {1, 0, 3};
		double[] c1 = {6, -2, 5};
		int[] e1 = {0, 1, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = {2, -1, 3, 2};
		int[] e2 = {0, 1, 2, 3};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("First Polynomial Add");
		for (int i = 0; i < s.Poly_Cf.length; i++) {
			System.out.println("Coeff = " + s.Poly_Cf[i]);
			System.out.println("Exp = " + s.Poly_Exp[i]);
		}

//		double[] bc1 = {5, 9, 22, 10};
//		int[]  be1 = {3, 7, 23, 4};
//		Polynomial bp1 = new Polynomial(bc1, be1);
//		double[] bc2 = {-22, 3, 11, 19, 17};
//		int[] be2 =     {23, 1, 0, 7, 13};
		double[] bc1 = {5, 9, 10, 22};
		int[]  be1 = {3, 7, 4, 23};
		Polynomial bp1 = new Polynomial(bc1, be1);
		double[] bc2 = {11, 3, 19, 17, -22};
		int[] be2 =     {0, 1, 7, 13, 23};
		Polynomial bp2 = new Polynomial(bc2, be2);
		Polynomial bct = bp2.add(bp1);
		System.out.println("Second Polynomial Add");
		for (int i = 0; i < bct.Poly_Cf.length; i++) {
			System.out.println("Coeff = " + bct.Poly_Cf[i]);
			System.out.println("Exp = " + bct.Poly_Exp[i]);
		}

		// Testing evaluate
		System.out.println("Testing evaluate");
		System.out.println("s(1) = " + s.evaluate(1));
		System.out.println("s(2) = " + s.evaluate(2));
		System.out.println("bct(0) = " + bct.evaluate(0));
		System.out.println("bct(1) = " + bct.evaluate(1));
		System.out.println("bct(2) = " + bct.evaluate(2));
		// Testing multiply
		System.out.println("Testing Multiply");
		//mult 1
		double[] c3 = {-1.0, 1.0};
		int[] e3 = {0, 1};
		Polynomial p3 = new Polynomial(c3, e3);
		double[] c4 = {-2.0, 1.0};
		int[] e4 = {0, 1};
		Polynomial p4 = new Polynomial(c4, e4);
		Polynomial s2 = p3.multiply(p4);
		System.out.println("First Multiply");
		for (int i = 0; i < s2.Poly_Cf.length; i++) {
			System.out.println("Coeff = " + s2.Poly_Cf[i]);
			System.out.println("Exp = " + s2.Poly_Exp[i]);
		}
		
		//mult2
//		double[] c5 = {5, 7, 11};
//		int[] e5 = {1, 0, 2};
		double[] c5 = {7, 5, 11};
		int[] e5 = {0, 1, 2};
		Polynomial p5 = new Polynomial(c5, e5);
		double[] c6 = {2, 8, 7, 6};
		int[] e6 = {0, 1, 2, 3};
		Polynomial p6 = new Polynomial(c6, e6);
		Polynomial s3 = p6.multiply(p5);
		System.out.println("Second multiply");
		for (int i = 0; i < s3.Poly_Cf.length; i++) {
			System.out.println("Coeff = " + s3.Poly_Cf[i]);
			System.out.println("Exp = " + s3.Poly_Exp[i]);
		}
		
		//mult3
		double[] c8 = {6, -2, 4,1};
		int[] e8 = {0, 1, 2,3};
		Polynomial p8 = new Polynomial(c8, e8);
		double[] c9 = {7, 2};
		int[] e9 = {0, 1};
		Polynomial p9 = new Polynomial(c9, e9);
		Polynomial s10 = p8.multiply(p9);
		System.out.println("Third multiply");
		for (int i = 0; i < s10.Poly_Cf.length; i++) {
			System.out.println("Coeff = " + s10.Poly_Cf[i]);
			System.out.println("Exp = " + s10.Poly_Exp[i]);
		}
		
		//end mult tests
		System.out.println("Testing hasRoot");
		if (s2.hasRoot(2)) {
			System.out.println("2 is a root of s2");
		}
		else {
			System.out.println("2 is not a root of s2");
		}
		if (s2.hasRoot(1)) {
			System.out.println("1 is a root of s2");
		}
		else {
			System.out.println("1 is not a root of s2");
		}
		if (s2.hasRoot(0)) {
			System.out.println("0 is a root of s2");
		}
		else {
			System.out.println("0 is not a root of s2");
		}
		if (s3.hasRoot(-2)) {
			System.out.println("-2 is a root of s3");
		}
		else {
			System.out.println("-2 is not a root of s3");
		}
		if (s3.hasRoot(4)) {
			System.out.println("4 is a root of s3");
		}
		else {
			System.out.println("4 is not a root of s3");
		}
		
		
		System.out.println("Testing File reading");
		File f = new File("C:\\Users\\edzha\\eclipse-workspace\\b07lab2fix\\Test_File_reading");
		Polynomial p7 = new Polynomial(f);
		for (int i = 0; i < p7.Poly_Cf.length; i++) {
			System.out.println("Coeff = " + p7.Poly_Cf[i]);
			System.out.println("Exp = " + p7.Poly_Exp[i]);
		}
		System.out.println("Writing to File");
		s3.saveToFile("output1.txt");
		double []lab2Poly_Cf = {5,-3,7};
		int []lab2Poly_Exp =   {0,2,8};
		Polynomial lab2 = new Polynomial(lab2Poly_Cf, lab2Poly_Exp);
		lab2.saveToFile("output2.txt");
		double []lab2Poly_Cf2 = {6,-2,5};
		int []lab2Poly_Exp2 =   {0,1,3};
		Polynomial lab2_2 = new Polynomial(lab2Poly_Cf2, lab2Poly_Exp2);
		lab2_2.saveToFile("output3.txt");
	}
}
