package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2013 Round 1B, Problem A
 */
public class TheRepeater {
    private static String working_dir = "io//codejam//2014//TheRepeater//";
    private static String input_filename = "A-large.in";
    private static String output_filename = "large.out.txt";    
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            int N = in.nextInt();
            String[] strings = new String[N];
            for (int i = 0; i < N; i++) strings[i] = in.next();
            
            printResult(out, caseno, solve(strings, N) + " (" + N + ")");
        }
        
        out.flush();
    }
    
    private static String solve(String[] strings, int N) {
        int sum = 0;
        int[] a = new int[N]; 
        while(!end(a, strings)) {
            if (!sameChars(a, strings)) return "Fegla Won";
            
            int[] count = new int[N];
            for (int i = 0; i < N; i++) {
                int end = run(strings[i], a[i]);
                count[i] = end-a[i];                
                a[i] = end;
            }
            
            int maxmean = max(count);
            int mindev = Integer.MAX_VALUE;
            for (int mean = min(count); mean <= maxmean; mean++) {
                mindev = Math.min(mindev, dev(count, mean));
            }
            
            sum += mindev;
        }
        
        return String.valueOf(sum);
    }
    
    private static int max(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i : a) max = Math.max(max, i);
        return max;
    }
    
    private static int min(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int i : a) min = Math.min(min, i);
        return min;
    }
    
    private static int dev(int[] a, int m) {
        int sum = 0;
        for(int i : a) sum += Math.abs(i - m);
        return sum;
    }
    
    private static int run(String s, int start) {
        int i = start;
        while (i < s.length() && s.charAt(i) == s.charAt(start)) i++;
        return i;
    }
    
    private static boolean sameChars(int[] a, String[] strings) {
        if (a[0] >= strings[0].length()) return false;
        
        for (int i = 0; i < a.length; i++)
            if (a[i] >= strings[i].length() || strings[i].charAt(a[i]) != strings[0].charAt(a[0]))
                return false;
        return true;
    }
    
    private static boolean end(int[] a, String[] sa) {
        for (int i = 0; i < a.length; i++)
            if (a[i] < sa[i].length()) return false;
        return true;
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
