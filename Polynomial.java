package b07lab2fix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {

	// Instance variables
    public double[] Poly_Cf;
    public int[] Poly_Exp;

    // Constructor with no arguments
    public Polynomial() {
        this.Poly_Cf = new double[]{0};
        this.Poly_Exp =new int[]{0};
    }

    // Constructor n
    public Polynomial(double[] Cf, int [] exponents) {
        this.Poly_Cf = Cf;
        this.Poly_Exp = exponents;
    }

    public Polynomial(File poly_file) throws IOException{
            BufferedReader reader;
			reader = new BufferedReader(new FileReader(poly_file));
            String txt_poly = reader.readLine();
            txt_poly = txt_poly.replace("+", " +");
            txt_poly = txt_poly.replace("-", " -");
            reader.close(); //or else string not saved
            
            //creating array equal to total num of terms in string
            //length fact provable w induction
            int total_op = 0; 
            for(int i = 0; i<txt_poly.length(); i++){
                if(txt_poly.charAt(i) == '+' || txt_poly.charAt(i) == '-'){
                    total_op++;  
                }
            }
            int total_terms = total_op+1; 
            double [] coefficient = new double [total_terms];
            int [] exponent = new int [total_terms];
            //end create
            
            //split at space to seperate into indivudal terms
            String [] idvdl_terms = txt_poly.split(" ");
            
            int index = 0;
            for(String term: idvdl_terms) {
            	int x_count = 0;
                for(int j=0; j<term.length(); j++){
                    if(term.charAt(j) == 'x'){
                        x_count++;
                    }
                }
                //constant term
                if (x_count == 0){
                	//check positivity
                	if(term.charAt(0)=='-') {
                		coefficient[index] = -1*Double.parseDouble(term);
                		exponent[index] = 0;
                	}
                	else {
                		coefficient[index] = Double.parseDouble(term);
                		exponent[index] = 0;
                	}
                } 
                //isn't constant
                else{
                   String [] cf_then_exp = term.split("x");
                   //check if the first term of poly isn't a constant
                   if(!term.contains("+")||!term.contains("-")) {
                	   coefficient[index] = Double.parseDouble(cf_then_exp[0]);
                	   exponent[index] = Integer.parseInt(cf_then_exp[1]);
                   }
                   //is a preceding term in polynomial
                   else if(cf_then_exp[0].charAt(0)=='+') {
                	   coefficient[index] = Double.parseDouble(cf_then_exp[0]);
                	   exponent[index] = Integer.parseInt(cf_then_exp[1]);
                   }
                   else {
                	   coefficient[index] = -1* Double.parseDouble(cf_then_exp[0]);
                	   exponent[index] = Integer.parseInt(cf_then_exp[1]); 
                   }
                }                     
                index++;
            }          
            this.Poly_Cf = coefficient;
            this.Poly_Exp = exponent;
            //Plan:
            //replace each operator in the string with space before it
            //split at the space, then we have an array with each of the individual terms 
            //once have the individual terms
            //do x count
            //if have an x, split them at the x
            //first thing in array is coeff, 2nd is exponent
            //otherwise no x, so it is just a constant
            //assign values correspondingly
    }

 	public Polynomial add(Polynomial argPoly)
	{	
 		//get number of matching exponents
        int match = 0; 
        for(int i = 0; i<this.Poly_Cf.length;i++){
            for(int j =0; j<argPoly.Poly_Cf.length; j++){
                if(this.Poly_Exp[i]==argPoly.Poly_Exp[j]){
                    match++;
                }
            }
        }
        
        //total length of union is sum of each - matching b/c double counted
        int total_length = this.Poly_Cf.length + argPoly.Poly_Cf.length - match;
        double [] newCf = new double [total_length];
        int[] newExp = new int [total_length];

        //copy over the contents of this into the first available array spots
        for(int i = 0; i< this.Poly_Cf.length; i++){
            newCf[i] = this.Poly_Cf[i];
            newExp[i] = this.Poly_Exp[i];
        }

        //iterate through the argPoly stuff
        for(int j = 0; j< argPoly.Poly_Cf.length; j++){
        	//check if that exponent is shared
            boolean equals = false;
            //iterate through each of the spots filled in by this in new array
            //if found set equals to true and then add the coefficient in that spot
            for(int h = 0; h<this.Poly_Cf.length; h++){
                if(newExp[h]==argPoly.Poly_Exp[j]){
                    equals = true; 
                    newCf[h] += argPoly.Poly_Cf[j]; //add coefficient there
                    break;
                }
            }
            // if went through all and couldn't find a matching coefficient
            // go to the first uninitialzied spot in new array then add the exponent and cf info
            if(equals == false){
            	for(int i = 1; i<newExp.length;i++) {
            		if(newExp[i]==0) {
                        newExp[i] = argPoly.Poly_Exp[j];
                        newCf[i] = argPoly.Poly_Cf[j];
                        break;
            		}
            	}
            }
        }
        
        //sort the array by exponents, bubble sort
        boolean swap = false;
		int tempExp = 0;
		double tempCoeff = 0;
		for (int i = 0; i < newExp.length - 1; i++) {
			for (int j = 0; j < newExp.length - i - 1; j++) {
				if (newExp[j] > newExp[j + 1]) {
					tempExp = newExp[j];
					newExp[j] = newExp[j + 1];
					newExp[j + 1] = tempExp;
					//swap coeff to match
					tempCoeff = newCf[j];
					newCf[j] = newCf[j + 1];
					newCf[j + 1] = tempCoeff;
					swap = true;
				}
			}
			if (swap == false) {
				break;//no swaps needed break alr sorted
			}
		}
		
		//check if once added it became 0, -3+3 = 0
        int total_zeroes = 0; 
        for(int i = 0; i < newCf.length;i++){
            if(newCf[i] == 0 ){
                total_zeroes++;
            }
        }

        if(total_zeroes == 0){
            return new Polynomial(newCf, newExp);
        }
        else{//something when added made it 0
            int no_zero_len = total_length - total_zeroes;
            double [] nzp_coeff = new double[no_zero_len];
            int [] nzp_exp = new int[no_zero_len];
            int tracker = 0; 
            for(int i = 0; i<newCf.length; i++){
                if(newCf[i] != 0){
                    nzp_coeff[tracker] = newCf[i];
                    nzp_exp[tracker] = newExp[i];
                    tracker++;
                }
            }
            return new Polynomial(nzp_coeff, nzp_exp);
        }
        }
 	
 	public double evaluate(double x) {
        double result = 0.0;
        int n = this.Poly_Cf.length;

        for (int i = 0; i < n; i++) {
            result += this.Poly_Cf[i] * Math.pow(x,this.Poly_Exp[i]);
        }
        return result;
    }


    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial multiplier){
        //double get the total length to the resulting polynomial when multiplied
        //create new arrays of length equal to the total length
        //iterate through this array's exponents
        //if the exponent is 0 then just update coefficient of new array
        //otherwise if the exponent is not 0 then update each index in exp array that it multiplies by by expsort[i]+ argexpsort[j]
        //also update each corresponding coefficient array by Coeffsort[i] * argCoeffsort[j];
    	
    	//total number of terms of polynomial, verify with induction
        int total = this.Poly_Cf.length + multiplier.Poly_Exp.length - 1;
        //create arrays to store cf and exp
		double[] mCoeff = new double[total];
		int[] mExp = new int[total];
		
		//for term in this poly, multiply it with all other terms
		//for each term
		//if the term 
		for (int i = 0; i < this.Poly_Cf.length; i++) {
			for (int j = 0; j < multiplier.Poly_Cf.length; j++) {
				//set coeff equal to current this term multiplied by current j-multiplier term
				//then iterate through all multiplier terms
				mCoeff[i + j] += this.Poly_Cf[i] * multiplier.Poly_Cf[j];
				//if the exponent isn't 0, then the corresponding exponents should be updated
				if (this.Poly_Exp[i] != 0) {
					mExp[i + j] = this.Poly_Exp[i] + multiplier.Poly_Exp[j];
					
				}					
			}
		}
		
		return new Polynomial(mCoeff, mExp);
		
    }

    public void saveToFile(String poly_file) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(poly_file));
        
        for(int i = 0; i<this.Poly_Cf.length; i++){
        	//first term, can be a constant
            if(i==0){
            	//check positivity of the first coefficient
            	//if positive just write the constant
            	//then if the positive term isn't a constant then write x and the exponent
                if(this.Poly_Cf[i]>0){
                    writer.write(Double.toString(this.Poly_Cf[i]));
                    if(this.Poly_Exp[i]!=0){
                        writer.write('x'+Integer.toString(this.Poly_Exp[i]));
                    }
                }
                // first term is negative, write - then the coefficient
                // if isn't constant write x then the exponent
                else{
                    writer.write(Double.toString(this.Poly_Cf[i]));
                    if(this.Poly_Exp[i]!=0){
                        writer.write('x'+Integer.toString(this.Poly_Exp[i]));
                    }
                }  
            	}
            	//not the first term of the poly, must have an operator before it
            	//also cannot be a constant
            else{
            	if(this.Poly_Cf[i]>0){
            		writer.write("+" + Double.toString(this.Poly_Cf[i]));
                    writer.write('x' + Integer.toString(this.Poly_Exp[i]));
                }
                //negative coeff
            	else{
            		writer.write(Double.toString(this.Poly_Cf[i]));
                    writer.write('x'+Integer.toString(this.Poly_Exp[i]));
                }
            }      
        }
        writer.close();
        //takes a polynomial and a file name then converts that polynomial to a stirng and saves it in the file
        //iterate through the coefficient arrays
        //for iteration
        //if first iteration
        //if the coefficient is positive, write the coefficient
        //if the coefficient is negative, write - + cf
        //check exponent
        //if exponent is not 0 then write x and then the exponent
        //otherwise do the same but with a '+' + cf if cf is positive
    }
}

