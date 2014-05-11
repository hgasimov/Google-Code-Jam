package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2014 Round 1C, Problem B (small case)
 */
public class ReorderingTrainCars {
    private static String working_dir = "io//codejam//2014//ReorderingTrainCars//";
    private static String input_filename = "B-small-attempt0.in";
    private static String output_filename = "small.out.txt";   
    
    private static final long m = 1000000007;
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            int N = in.nextInt();
            String[] sets = new String[N];
            
            for (int i = 0; i < N; i++) {
                sets[i] = in.next();
            }
            
            printResult(out, caseno, String.valueOf(solve(sets, "", N, new boolean[N], 0)));
        }
        
        out.flush();
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }

    private static long solve(String[] sets, String train, int N, boolean[] marked, int level) {
        if (level == N) {
            return 1;
        }
        
        long sum = 0;
        for (int i = 0; i < N; i++) 
            if (!marked[i]) {                
                marked[i] = true;
                
                String newTrain = train + sets[i];
                if (isValid(newTrain)) {
                    sum += solve(sets, newTrain, N, marked, level+1);
                }
                
                marked[i] = false;     
            }
        
        return sum;
    }
    
    private static boolean isValid(String s) {
        if ("".equals(s)) return true;
        
        char[] count = new char[128];
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i)]++;
        
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            for (int j = i; j < i + count[c]; j++)
                if (s.charAt(j) != c) return false;
            
            i += count[c];
        }
        
        return true;
    }
}
