package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Code Jam 2014 Qualification, Problem C
 */
public class MinesweeperMaster {
    private static String working_dir = "io//codejam//2014//MinesweeperMaster//";
    private static String input_filename = "C-large.in";
    private static String output_filename = "large.out.txt";
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int t = in.nextInt();
        for (int caseno = 1; caseno <= t; caseno++)
            printResult(out, caseno, solve(in.nextInt(), in.nextInt(), in.nextInt()));        
        
        out.flush();
    }
    
    private static String solve(int R, int C, int M) {
        int dots = R * C - M; 
        
        if (M > 0 && R > 1 && C > 1 && (dots == 2 || dots == 3)) 
            return "\nImpossible";

        char[][] grid = new char[R][C];
        grid[0][0] = 'c'; dots--;
        if (C > 1 && dots > 0) { grid[0][1] = '.'; dots--; }
        if (R > 1 && dots > 0) { grid[1][0] = '.'; dots--; }
        if (R > 1 && C > 1 && dots > 0) { grid[1][1] = '.'; dots--; }
        
        if (R == 1) {
            int i = 2;
            for (; i < C && dots > 0; i++) {
                grid[0][i] = '.';
                dots--;
            }
            for (; i < C; i++) grid[0][i] = '*';
        }
        else if (C == 1) {
            int i = 2;
            for (; i < R && dots > 0; i++) {
                grid[i][0] = '.';
                dots--;
            }
            for (; i < R; i++) grid[i][0] = '*';
        }
        else {
            if (dots == 1) return "\nImpossible";
            int k = 2;
            for (; k < C && dots > 0; k++) {
                if (dots == 3) {
                    if (k > 2 && R > 2) {
                        grid[2][0] = grid[2][1] = grid[2][2] = '.';
                        dots = 0;
                        break;
                    }
                    else
                        return "\nImpossible";                    
                }
                
                if (dots > 0) { grid[0][k] = '.'; dots--; }
                if (dots > 0) { grid[1][k] = '.'; dots --; }                
            }
            
            if (dots > 1) {                 
                for (int i = 2; i < R && dots > 0; i++) {
                    int j = 0;
                    for (; j < C; j++) {
                        if (j == C-2 && dots == 3) {
                            if (j > 0) {
                                grid[i][j] = '.'; dots--;
                                break;
                            }
                            else
                                return "\nImpossible";
                        }
                        
                        if (dots > 0) { grid[i][j] = '.'; dots--; }
                    }
                }       
            }
        }
        
        fillMines(grid);
        return toString(grid);
    }
    
    private static void fillMines(char[][] grid) {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '\0') grid[i][j] = '*';
    }
    
    private static String toString(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (char[] a : grid) {
            sb.append('\n').append(String.copyValueOf(a));
        }    
        return sb.toString();
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}