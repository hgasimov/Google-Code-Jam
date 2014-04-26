package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * Code Jam 2014 Round 1A, Problem A
 */
public class ChargingChaos {
    private static String working_dir = "io//codejam//2014//ChargingChaos//";
    private static String input_filename = "A-small-practice.in";
    private static String output_filename = "small2.out.txt";    
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            int N = in.nextInt();
            int L = in.nextInt();            
            char[][] outlets = read(in, N, L);            
            char[][] devices = read(in, N, L);  
            
            Arrays.sort(devices, new NaturalOrder(L-1));
            
            int flips = solve(outlets, devices, N, L, 0);
            printResult(out, caseno, flips <= L ? String.valueOf(flips) : "NOT POSSIBLE");
        }
        
        out.flush();
    }
    
    private static int solve(char[][] outlets, char[][] devices, int N, int L, int d) {
        if (d >= L) return 0;
        int outOnes = countOnes(outlets, d);
        int devOnes = countOnes(devices, d);

        int sum = L+1;
        if (outOnes == devOnes && isOK(outlets, devices, d)) {
            sum = Math.min(sum, solve(outlets, devices, N, L, d+1));
        }

        if (N - outOnes == devOnes) {
            flip(outlets, d);
            if (isOK(outlets, devices, d))
                sum = Math.min(sum, 1 + solve(outlets, devices, N, L, d+1));
            flip(outlets, d);                
        }

        return sum;
    }
    
    private static int countOnes(char[][] a, int d) {
        int sum = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i][d] == '1') sum++;
        return sum;
    }
    
    private static char[][] read(Scanner in, int N, int L) {
        char[][] a = new char[N][L];
        for (int i = 0; i < N; i++) 
            System.arraycopy(in.next().toCharArray(), 0, a[i], 0, L);
        return a;
    }

    private static boolean isOK(char[][] outlets, char[][] devices, int d) {
        Arrays.sort(outlets, new NaturalOrder(d));
        for (int i = 0; i < outlets.length; i++)
            if (outlets[i][d] != devices[i][d]) return false;
        return true;
    }

    private static void flip(char[][] devices, int d) {
        for (int i = 0; i < devices.length; i++)
            devices[i][d] = (devices[i][d] == '0' ? '1' : '0');
    }
    
    private static class NaturalOrder implements Comparator<char[]> {
        int d;
        public NaturalOrder (int i) {d = i; }

        @Override
        public int compare(char[] o1, char[] o2) {
            for (int i = 0; i <= d; i++)
                if (o1[i] != o2[i]) return o1[i] - o2[i];
            return 0;
        }
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
