package model.algorithm.PSO;

class Function {

    public static int maxWeight = 17;

    /**
     * Calculate the result of (x^4)-2(x^3).
     * Domain is (-infinity, infinity).
     * Minimum is -1.6875 at x = 1.5.
     * @param x     the x component
     * @return      the y component
     */
    static double functionA (double x) {
        int[] weight;
        weight = new int[]{6,5,4,3,2,1};

        String arr = new String( Integer.toBinaryString( (int)Math.abs(x) )  );
        int totalweight = 0;

        if (arr.length() > 6) return 100;
        int lengthDif = 6 - arr.length();
        
        for (int i = arr.length()-1; i >= 0; i--){
            if (arr.charAt(i) == '1')  {
                totalweight += weight[i+lengthDif]; 
            }
        }
        if (totalweight > maxWeight) return 100;
        return maxWeight - totalweight;
    }

}