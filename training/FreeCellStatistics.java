/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codejam;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huseyngasimov
 */
public class FreeCellStatistics {
    static String working_dir = "//Users//huseyngasimov//git//CodeJam//CodeJam//inputoutput_files//FreeCellStatistics//";
    static String input_filename = "A-large-practice.in";
    static String output_filename = "A-large-practice.out";
    
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(working_dir + input_filename));
            FileWriter fstream = new FileWriter(working_dir + output_filename);
            BufferedWriter out = new BufferedWriter(fstream);
            int T = Integer.parseInt(br.readLine());
            
            for (int caseno = 1; caseno <= T; caseno++) {                 
                String[] line = br.readLine().split(" ");
                int pD = Integer.parseInt(line[1]);
                int pG = Integer.parseInt(line[2]);
                
                if ((pD > 0 && pG == 0) || (pD < 100 && pG == 100)) {
                    printResult(out, "Case #" + caseno + ": Broken");
                    continue;
                }
                
                int dD = calcInc(pD, 100);                
                                
                if (line[0].length() > 3 || Integer.parseInt(line[0]) >= dD)
                    printResult(out, "Case #" + caseno + ": Possible");  
                else 
                    printResult(out, "Case #" + caseno + ": Broken");
            }
            
            br.close();
            out.close();
            fstream.close();
        }
        catch(Exception ex) {
            System.err.println(ex);
        }        
    }
    
    static void printResult(BufferedWriter out, String s) {
        try {
            System.out.println(s);
            out.write(s + "\n");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    static int calcInc(int i, int j) {
        if (i == 1) return j;
        if (j == 1) return i;
        if (i == 0 || j == 0) return 1;
        
        for (int x = 2; x <= i && x <= j; x++)
            if ((i % x == 0) && (j % x == 0))
                return calcInc(i/x, j/x);
        
        return Math.max(i, j);
    }
}
