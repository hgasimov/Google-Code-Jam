package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2013 Round 1C, Problem B (small case)
 */
public class Pogo {    
    private static String working_dir = "io//codejam//2014//Pogo//";
    private static String input_filename = "B-small-practice.in";
    private static String output_filename = "small.out.txt";    
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            int x = in.nextInt(),
                y = in.nextInt();
            String xs = x < 0 ? "EW" : "WE";
            String ys = y < 0 ? "NS" : "SN";
            
            StringBuilder sb = new StringBuilder();
            append(sb, xs, Math.abs(x));
            append(sb, ys, Math.abs(y));
            
            printResult(out, caseno, sb.toString());
        }
        
        out.flush();
    }
    
    private static void append(StringBuilder sb, String s, int n) {
        for (int i = 0; i < n; i++) sb.append(s);
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
