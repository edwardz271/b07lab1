public class Polynomial {
    // Instance variables
    private double[] Poly_Cf;
    private int[] Poly_Exp;

    // Constructor with no args
    public Polynomial() {
        this.Poly_Cf = new double[]{0};
        this.Poly_Exp =new int[]{0};
    }

    // Constructor n
    public Polynomial(double[] Cf, int [] exponents) {
        this.Poly_Cf = Cf;
        this.Poly_Exp = exponents;
    }

    public Polynomial(File poly_file){
            BufferedReader reader = new BufferedReader(Filereader(poly_file));
            String txt_poly = reader.readLine();
            int total_op = 0; 
            for(int i = 0; i<txt_poly.length; i++){
                if(txt_poly[i] == '+' || txt_poly[i] == '-'){
                    total_op++;  
                }
            }
            int total_terms = total_op+1;

            double [] coefficient = new double [total_terms];
            int [] exponent = new int [total_terms];

            int index = 0; 
            String [] no_plus = txt_poly.split("+");
            for(String ss: no_plus){
                String [] no_minus = ss.split("-");
                for(int i = 0; i < no_minus.length;i++){
                    if(i == 0){
                        int x_count = 0;
                        for(int j=0; j<no_minus[i].length; j++){
                            if(no_minus[i] == 'x'){
                                x_count++;
                            }
                        }
                        if (x_count == 0){
                                coefficient[index] = no_minus[i];
                                expoenent[index] = 0;
                        } 
                        else{
                               String [] cf_then_exp = no_minus[i].split("x");
                               coefficient[index] = cf_then_exp[0];
                               exponent[index] = cf_then_exp[1];
                        }                     
                    }
                    else{
                                String [] cf_then_exp = no_minus[i].split("x");
                               coefficient[index] = cf_then_exp[0];
                               exponent[index] = cf_then_exp[1];
                    }
                    index++;
                }
            }

            return Polynomial(coefficient, exponent);
            //total terms = number of operators + 1 induct on number of terms, total terms is a postivie integer
            //create an array with that length
            //create an index incrememnter starting at 0
            //split the array at the plus signs (removes all the plus signs)
            //each of the substrings in the array have no plus signs then, so it is only possible that a negative sign exists
            //split each the subarrays at minus sign into a new array
            //then for all strings after the first thing in array, it is a minus poly
            //for each string in the subarray, if it is the first string, then put it in the 
            //current incrememneter index
            //if x exists in the first string then get the exponent, otherwise it is the constant, so put 0 for the exponent slot
            //otherwise, get the term after the x, put that in exponenent then put -*number in the coefficient
            //increment the incrementer by 1
    }

 	public Polynomial add(Polynomial argPoly)
	{
        //check how many exponents are shared between the polynomials
        // a+b - intersected element = a or b
        // create 2 new arrays with that length, that is the length of the polynomial we want to create
        //copy all of this coeffs into the new coeffs array
        // copy all exponents of this into the new exps array
        //iterate through the other polynomials stuff
        //if the exponent is equal to any of the elements in this exponents then add the corresponding coefficients together
        //otherwise the expoenent isn't equal to any of the elements in this expoennts, so we want to add them to the new array
        //iterate to find a 0 then add the exponent and the coeffient in the new list
        //then sort both of the arrays accordingly
        //then check the number of zeroes in the new arrays (coefficients -1+1 = 0)
        //if there isn't any then create a polynomial with the arrays
        //if there are then count them
        // create a new array that is same length as the old one minues the number of zeroes
        //iterate through the old list, whenever the coeffiient isn't a 0 then add it to the new list
        int match = 0; 
        for(int i = 0; i<this.Poly_Cf.length;i++){
            for(int j =0; j<argPoly.Poly_Cf.lenght; j++){
                if(this.Poly_Cf[i]==argPoly.Poly_Cf[j]){
                    match++;
                }
            }
        }
        
        int total_length = this.Poly_Cf.length + argPoly.Poly_Cf.lenght - match;
        double [] newCf = new double [total_length];
        int[] newExp = new int [total_length];

        for(int i = 0; i< this.Poly_Cf.length; i++){
            newCf[i] = this.Poly_Cf[i];
            newExp[i] = this.Poly_Exp[i];
        }

        for(int j = 0; j< argPoly.Poly_Cf.length; j++){
            boolean equals = false
            for(int h = 0; h<newCf.length; h++){
                if(newExp[h]==newExp[h]){
                    equals = true; 
                    newCf[h] += argPoly[j]; //add coeffient there
                }
            }
            // if went through all and couldn't find a matchign coefficinet
            if(equals == false){
                int i = 0; 
                while(newExp[i]!=0){
                    i++; //find the last exponent of first
                }
                newExp[i] = argPoly.Poly_Exp[j];
                newCf[i] = argPoly.Poly_Cf[j];
            }
        }


        boolean swap = false;
		int tempExp = 0;
		double tempCoeff = 0;
		for (int i = 0; i < newExp.length - 1; i++) {
			for (int j = 0; j < newExp.length - i - 1; j++) {
				if (newExp[j] > newExp[j + 1]) {
					tempExp = newExp[j];
					newExp[j] = newExp[j + 1];
					newExp[j + 1] = tempExp;
					tempCoeff = newCF[j];
					newCf[j] = newCf[j + 1];
					newCf[j + 1] = tempCoeff;
					swap = true;
				}
			}
			if (swap == false) {
				break;//no swaps needed break alr sorted
			}
		}

        int total_zeroes = 0; 
        for(int i = 0; i<newExp;i++){
            if(newCf[i] == 0 ){
                total_zeroes++;
            }
        }

        if(total_zeroes == 0){
            return Polynomial(newCf, newExp);
        }
        else{
            int no_zero_len = total_length - num_zeroes;
            double [] nzp_coeff = new double[no_zero_len];
            int [] nzp_exp = new int[no_zero_len];
            int tracker = 0; 
            for(int i = 0; i<newCF.length; i++){
                if(newCF.length != 0){
                    nzp_coeff[tracker] = newCf[i];
                    nzp_exp[tracker] = newExp[i];
                    tracker++;
                }
            }
            return Polynomial(nzp_coeff, nzp_exp);
        }
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

        int total = this.Poly_Cf.length + multiplier.Poly_Exp.length - 1; //check with induction
		double[] mCoeff = new double[total];
		int[] mExp = new int[total];
		for (int i = 0; i < Coeffsort.length; i++) {
			for (int j = 0; j < argCoeffsort.length; j++) {
				if (mExp[i + j] != 0) {
					mExp[i + j] = this.Poly_Exp[i] + multiplier.Poly_Exp[j];
					mCoeff[i + j] += this.Poly_Cf[i] * multip   lier.Poly_Cf[j];
				}
				else {
					resCoeff[i + j] += Coeffsort[i] * argCoeffsort[j];
				}
			}
		}
		return new Polynomial(mCoeff, mExp);
		
    }

    public void saveToFile(String filename){

    }
}


