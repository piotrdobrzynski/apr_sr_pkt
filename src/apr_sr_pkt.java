public class apr_sr_pkt {

    static double y[] = new double[4];
    static double x[] = new double[4];
    static double s[][] = new double[3][3];
    static double a[] = new double[3];
    static double t[] = new double[3];
    static double n,m;

    public static double[] solve(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

    public static double suma_s(int i, int j){

        double wyn=0;
        for(int a=0;a<m;a++){
            wyn += Math.pow(x[a],(i+j));
        }

        return wyn;
    }

    public static double suma_t(int i){

        double wyn=0;
        for(int a=0;a<m;a++){
            wyn += (Math.pow(x[a],(i)))*y[a];
        }

        return wyn;
    }

    public static void pokaz(){

        for (int i=0;i<m-1;i++){
            for (int j=0;j<m-1;j++){
                System.out.print(s[i][j]+" ");
            }
            System.out.println();
        }

        for (int i=0;i<m-1;i++){
            System.out.println(t[i]);
        }
    }

    public static void wyn(){

        for (int i=0;i<m-1;i++){
            System.out.println(a[i]);
        }
    }



    public static void apr(){

        for (int i=0;i<m-1;i++){
            for (int j=0;j<m-1;j++){
                s[i][j] = suma_s(i,j);
            }
        }

        for (int i=0;i<m-1;i++){
            t[i] = suma_t(i);
        }
    }

    public static void main(String[] args) {

        y[0] = 6; // wartosci y
        y[1] = 19;
        y[2] = 40;
        y[3] = 69;

        x[0] = 1; // wartosci x
        x[1] = 2;
        x[2] = 3;
        x[3] = 4;

        n = 2; // stopien wielomianu
        m = 4; // liczba punktow

        apr();
        pokaz();
        a = solve(s,t);
        wyn();
}
}
