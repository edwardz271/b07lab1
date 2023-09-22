public class Polynomial {
    // Instance variables
    private double[] Poly_Cf;

    // Constructor with no args
    public Polynomial() {
        this.Poly_Cf = new double[]{0};
    }

    // Constructor 
    public Polynomial(double[] Cf) {
        this.Poly_Cf = Cf;
    }

    // Method to add two polynomials
    public Polynomial add(Polynomial other) {
        int length1 = this.Poly_Cf.length;
        int length2 = other.Poly_Cf.length;
        int maxLength = Math.max(length1, length2);
        double[] resultCoefficients = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double coef1 = 0;
            double coef2 = 0;

            if (i < length1) {
                coef1 = this.Poly_Cf[i];
            }

            if (i < length2) {
                coef2 = other.Poly_Cf[i];
            }

            resultCoefficients[i] = coef1 + coef2;
        }

        return new Polynomial(resultCoefficients);
    }

    
    public double evaluate(double x) {
        double result = 0.0;
        int n = this.Poly_Cf.length;

        for (int i = 0; i < n; i++) {
            result += this.Poly_Cf[i] * Math.pow(x, i);
        }

        return result;
    }


    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
}


