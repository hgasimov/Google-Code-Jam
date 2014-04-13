package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2014 Qualification, Problem A
 */
public class MagicTrick {
    static String working_dir = "io//codejam//2014//MagicTrick//";
    static String input_filename = "A-small-attempt0.in";
    static String output_filename = "out.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int t = in.nextInt();
        for (int testNo = 1; testNo <= t; testNo++) {
            int[] row1 = readDeck(in, in.nextInt());
            int[] row2 = readDeck(in, in.nextInt());
            
            int[] cards = new int[17];
            for (int i : row1) cards[i]++;
                        
            int ans = -1;
            for (int i: row2) {
                cards[i]++;
                if (cards[i] == 2) {
                    if (ans < 0)
                        ans = i;
                    else {
                        ans = -2;
                        break;
                    }
                }
            }
            
            if (ans > 0) {                
                printResult(out, "Case #" + testNo + ": " + ans);
            }
            else {
                printResult(out, ans == -2 ? 
                        "Case #" + testNo + ": Bad magician!" : 
                        "Case #" + testNo + ": Volunteer cheated!");
            }
        }
        
        out.flush();
    }
    
    private static int[] readDeck(Scanner in, int ans) {
        int[] ret = null;
        for (int i = 1; i <= 4; i++) {
            if (i == ans) {
                ret = new int[4];
                for (int j = 0; j < 4; j++)
                    ret[j] = in.nextInt();
            }
            else {
                for (int j = 0; j < 4; j++)
                    in.nextInt();            
            }
        }
        
        return ret;
    }
    
    private static void printResult(PrintWriter out, String s) {
        out.println(s);
        System.out.println(s);
    }
}
