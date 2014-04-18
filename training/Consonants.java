package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2013 Round 1C, Problem A
 */
public class Consonants {
    private static String working_dir = "io//codejam//2014//Consonants//";
    private static String input_filename = "A-large-practice.in";
    private static String output_filename = "large.out.txt";
    private static char[] vowels = {'a', 'e', 'i', 'o', 'u' };
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        
        for (int caseno = 1; caseno <= T; caseno++) {
            char[] s = in.next().toLowerCase().toCharArray();
            int L = s.length;
            int n = in.nextInt();
            
            long sum = 0;
            int start = 0;
            int j = -1;            
            for (int i = 0; i < L; i++) 
                if (isConsonant(s[i])) {
                    if (j == -1) j = i; 
                                            
                    if (i - j == n - 1) {
                        long prev = j - start + 1;
                        long after = L - i;
                        sum += prev * after;
                        start = i - n + 2;
                        j++;
                    }
                }
                else {
                    j = -1;
                }
            
            printResult(out, caseno, String.valueOf(sum));        
        }
        
        out.flush();
    }
    
    private static boolean isConsonant(char c) {
        for (char v : vowels) if (c == v) return false;
        return true;
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
