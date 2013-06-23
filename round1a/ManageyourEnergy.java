package codejam;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author huseyngasimov
 */
public class ManageyourEnergy {
    static String working_dir = "//Users//huseyngasimov//git//CodeJam//CodeJam//inputoutput_files//ManageyourEnergy//";
    static String input_filename = "B-large-practice.in";
    static String output_filename = "B-large-practice.out";
    
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(working_dir + input_filename));
            FileWriter fstream = new FileWriter(working_dir + output_filename);
            BufferedWriter out = new BufferedWriter(fstream);
            int T = Integer.parseInt(br.readLine());
            
            for (int caseno = 1; caseno <= T; caseno++) {                 
                String[] line = br.readLine().split(" ");
                long E = Long.parseLong(line[0]);
                long R = Long.parseLong(line[1]);
                int N = Integer.parseInt(line[2]);
                
                line = br.readLine().split(" ");
                long[][] v = new long[N][3]; // 0 - gain, 1 - spent energy, 2 - energy remained after repairing
                long[][] sv = new long[N][2]; // sorted v 
                boolean[] marked = new boolean[N];
                for (int i = 0; i < N; i++) {
                    v[i][0] = Integer.parseInt(line[i]); // gain
                    sv[i][0] = i;
                    sv[i][1] = v[i][0]; // gain
                }                
                
                Arrays.sort(sv, new Comparator<long[]>() {
                    @Override
                    public int compare(long[] t, long[] t1) {
                        if (t[1] < t1[1]) return 1;
                        if (t[1] > t1[1]) return -1;
                        if (t[0] > t1[0]) return 1; 
                        else return -1;
                    }                
                });                               
                
                for (int ind = 0; ind < N; ind++) {
                    long max = sv[ind][1];
                    int max_index = (int) sv[ind][0];
                    fillMaxAvail(v, E, R, marked, max_index);                    
                    marked[max_index] = true;
                }                
                
                long result = 0;
                for (int i = 0; i < N; i++) {                    
                    result += v[i][0] * v[i][1];
                }
                
                printResult(out, "Case #" + caseno + ": " + String.valueOf(result));
            }
            
            br.close();
            out.close();
            fstream.close();
        }
        catch(Exception ex) {
            System.err.println(ex);
        }        
    }
    
    private static void printV(long[][] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.println(v[i][0] + " " + v[i][1] + " " + v[i][2]);
        }
            
    }
    
    private static void fillMaxAvail(long[][] v, long E, long R, boolean[] marked, int ind) {
        int N = v.length;
        
        int down;
        for (down = ind - 1; down > -1; down--) 
            if (marked[down]) break;
        
        long maxAvail_down; // maxAvail forced by down
        if (down < 0) maxAvail_down = E;
        else {
            long eGain = (ind - down - 1) * R; // energy can be gained from down to ind
            maxAvail_down = Math.min(E, eGain + v[down][2]);
        }
          
        int up;
        for (up = ind + 1; up < N; up++) 
            if (marked[up]) break;
            
        long maxAvail_up; // maxAvail forced by up
        if (up > N-1) maxAvail_up = E;
        else {
            long eGain = (up - ind) * R; // energy can be gained from ind to up
            long necessary = Math.max(v[up][1], v[up][1] + v[up][2] - R);
            long minSaved = Math.max(necessary - eGain, 0); // energy saved for up
            maxAvail_up = E - minSaved;
        }
        
        long maxAvail_inter = E; // maxAvail forced by intermediate gain
        if (down >= 0 && up < N) {
            long eGain = (up - down - 1) * R; // energy can be gained from down to up
            long necessary = Math.max(v[up][1], v[up][1] + v[up][2] - R);
            maxAvail_inter = Math.max(v[down][2] + eGain - necessary, 0); // energy saved for up
        }
                
        long maxAvail = Math.min(maxAvail_down, maxAvail_up);
        maxAvail = Math.min(maxAvail, maxAvail_inter);
        v[ind][1] = maxAvail;
        v[ind][2] = Math.min(E, maxAvail_down - maxAvail + R);                 
    }
    
    static void printResult(BufferedWriter out, String s) {
        try {
            System.out.println(s);
            out.write(s + "\n");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    static void out(String s) {
        System.out.println(s);
    }
}
