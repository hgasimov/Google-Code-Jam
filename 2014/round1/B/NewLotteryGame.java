package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2013 Round 1B, Problem B (small case)
 */
public class NewLotteryGame {
    private static String working_dir = "io//codejam//2014//NewLotteryGame//";
    private static String input_filename = "B-small-attempt0.in";
    private static String output_filename = "small.out.txt";    
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            int A = in.nextInt(); 
            int B = in.nextInt(); 
            int K = in.nextInt(); 
            
            int count = 0;
            for (int i = 0; i < A; i++)
                for (int j = 0; j < B; j++)
                    if ((i & j) < K) count++;
                                
            printResult(out, caseno, String.valueOf(count));
        }
        
        out.flush();
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
