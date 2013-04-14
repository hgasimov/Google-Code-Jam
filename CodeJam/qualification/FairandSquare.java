/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FacebookHackerCup;

import java.io.*;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huseyngasimov
 */
public class FairandSquare {
    static String working_dir = "//Users//huseyngasimov//git//CodeJam//CodeJam//inputoutput_files//FairandSquare//";
    static String input_filename = "C-large-2.in";
    static String output_filename = "C-large-2.out";
    static boolean DEBUG = false;
    static int testCase = 3;
    
    private BigInteger A, B;
    private BigInteger nmb;
    
    public static void main(String[] args) {
        //BigInteger bi = new BigInteger("8");      
        //bi = nextPolyndrome(bi);
        //System.out.println(bi.toString() + " + " + isPolyndrome(bi));
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(working_dir + input_filename));
            FileWriter fstream = new FileWriter(working_dir + output_filename);
            BufferedWriter out = new BufferedWriter(fstream);
            int m = Integer.parseInt(br.readLine());
            
            for (int caseno = 1; caseno <= m; caseno++) {                 
                String[] line = br.readLine().split(" ");
                FairandSquare fs = new FairandSquare(line[0], line[1]);
                String result = fs.numFS().toString();
                                       
                System.out.println("Case #" + caseno + ": " + result);
                writeLineToFile(out, "Case #" + caseno + ": " + result);
            }
            
            br.close();
            out.close();
            fstream.close();
        }
        catch(Exception ex) {
            System.err.println(ex);
        }        
    }
    
    static void writeLineToFile(BufferedWriter out, String s) {
        try {
            out.write(s + "\n");
        } catch (IOException ex) {
            Logger.getLogger(BalancedSmileys.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public FairandSquare(String a, String b) {
        A = new BigInteger(a);
        B = new BigInteger(b);
        nmb = new BigInteger("0");
        
        BigInteger i = sqrtFloor(A);
        if (A.compareTo(i.pow(2)) != 0 ) i = nextPolyndrome(i);        
        BigInteger i2 = i.pow(2);
        
        while (i2.compareTo(B) <= 0) {
            if (isPolyndrome(i2)) {
                //System.out.println("i2 = " + i2.toString());
                nmb = nmb.add(BigInteger.ONE);
            }
            
            i = nextPolyndrome(i);
            i2 = i.pow(2);
        }
    }
    
    public BigInteger numFS() {          
        return nmb;
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
    
    private static boolean isPolyndrome(BigInteger bi) {
        String si = bi.toString();
        int n = si.length();
        if (n == 1) return true;
        
        for(int i = 0; i <= n/2; i++)
            if (si.charAt(i) != si.charAt(n-i-1))
                return false;
        
        return true;
    }
    
    private static BigInteger nextPolyndrome(BigInteger bi) {
        String si = bi.toString();
        int n = si.length();
        
        if (n == 1) {
            if (si.equals("9")) return new BigInteger("11");
            else return bi.add(BigInteger.ONE);
        }
        
        double mid = (double) n/2;       
        String base = si.substring(0, (int) Math.ceil(mid));  
        //System.out.println(base);
        
        BigInteger basePoly = createPolyndrome(base, n % 2 == 1);
        
        if (basePoly.compareTo(bi) > 0) return basePoly;
        
        BigInteger bibase = new BigInteger(base); 
        int prevl = base.length(); // previous length of the base
        base = bibase.add(BigInteger.ONE).toString(); // new base
        
        boolean stand = ((n + base.length() - prevl) % 2 == 1) ;                
        return createPolyndrome(base, stand);
    }
    
    private static BigInteger createPolyndrome(String base, boolean stand) {
        char[] cp;
        if (stand) cp = new char[2*base.length()-1];
        else cp = new char[2*base.length()];
        
        char[] cbase = base.toCharArray();
        System.arraycopy(cbase, 0, cp, 0, cbase.length);
        
        int n = cp.length;
        for(int i = cbase.length; i < n; i++) {            
            cp[i] =  cp[n-i-1];
        }
                
        return new BigInteger(String.valueOf(cp));
    }
    
    
    
}
