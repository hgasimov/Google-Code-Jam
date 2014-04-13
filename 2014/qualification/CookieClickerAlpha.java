package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2014 Qualification, Problem B
 */
public class CookieClickerAlpha {
    private static String working_dir = "io//codejam//2014//CookieClickerAlpha//";
    private static String input_filename = "B-large.in";
    private static String output_filename = "large.out.txt";
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int t = in.nextInt();
        for (int caseno = 1; caseno <= t; caseno++) {
            double ans = solve(in.nextDouble(), in.nextDouble(), in.nextDouble());
            int i = (int)1E9;
            ans = Math.rint(ans * i)/i;
            printResult(out, caseno, String.valueOf(ans));
        }
        
        out.flush();
    }
    
    private static double solve(double C, double F, double X) {
        if (C >= X) return X/2.0;
        
        double speed = 2.0;
        double time = C/speed;
        X -= C;
        
        while (X > 0) {
            double t1 = X / speed;
            double t2 = (X + C) / (speed + F);
            
            if (t2 < t1) {
                speed += F;
                X += C;
            }
            
            if (C < X) {
                time += C / speed;
                X -= C;
            }
            else {
                time += X / speed;
                X = 0;
            }
        }
        
        return time;
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
