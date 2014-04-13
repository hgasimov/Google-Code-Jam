/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codejam;

import java.io.*;
import java.math.BigInteger;

/**
 *
 * @author huseyngasimov
 */
public class Bullseye {
    static String working_dir = "//Users//huseyngasimov//git//CodeJam//CodeJam//inputoutput_files//Bullseye//";
    static String input_filename = "A-large.in";
    static String output_filename = "A-large.out";
    
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(working_dir + input_filename));
            FileWriter fstream = new FileWriter(working_dir + output_filename);
            BufferedWriter out = new BufferedWriter(fstream);
            int T = Integer.parseInt(br.readLine());
            
            for (int caseno = 1; caseno <= T; caseno++) {                 
                String[] line = br.readLine().split(" ");
                BigInteger r = new BigInteger(line[0]);
                BigInteger t = new BigInteger(line[1]);
                
                BigInteger two_r = r.add(r);                
                BigInteger res = sqrtD(r, t).add(BigInteger.ONE).subtract(two_r).divide(new BigInteger("4"));
                
                printResult(out, "Case #" + caseno + ": " +res.toString());
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
    
    private static BigInteger sqrtD(BigInteger r, BigInteger t) {
        BigInteger tmp = r.add(r).subtract(BigInteger.ONE);
        BigInteger eight = new BigInteger("8");
        tmp = tmp.multiply(tmp).add(t.multiply(eight));
        return sqrtFloor(tmp);
    }
    
    // sqrt floor
    private static BigInteger sqrtFloor(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        while(b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
            else a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }
}
