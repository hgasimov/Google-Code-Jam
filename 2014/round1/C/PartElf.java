package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2014 Round 1C, Problem A
 */
public class PartElf {
    private static String working_dir = "io//codejam//2014//PartElf//";
    private static String input_filename = "A-large.in";
    private static String output_filename = "large.out.txt";    
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            String[] line = in.next().split("/");
            long P = Long.parseLong(line[0]);
            long Q = Long.parseLong(line[1]);
            
            long gcd = gcd(P, Q);
            P /= gcd;
            Q /= gcd;
            if (isPowOfTwo(Q)) {
                printResult(out, caseno, String.valueOf(highestOne(Q) - highestOne(P)));
            }
            else {
                printResult(out, caseno, "impossible");
            }
        }
        
        out.flush();
    }

    private static long gcd(long x, long y) {
        while (x > 0 && y > 0) {
            if (x < y) y %= x;
            else x %= y;
        }
        return x + y;
    }
    
    private static boolean isPowOfTwo(long i) { 
        if (i < 2) return false;
        return Long.lowestOneBit(i) == Long.highestOneBit(i); 
    }
    
    private static int highestOne(long i) {
        long j = 1;
        for (int k = 32; k >= 0; k--)
            if ((i & (j << k)) > 0)
                return k;
        return -1;
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
