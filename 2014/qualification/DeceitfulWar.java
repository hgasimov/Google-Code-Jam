package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * Code Jam 2014 Qualification, Problem D
 */
public class DeceitfulWar {
    private static String working_dir = "io//codejam//2014//DeceitfulWar//";
    private static String input_filename = "D-large-practice.in";
    private static String output_filename = "large.out.txt";
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            int N = in.nextInt();
            TreeSet<Double> nao = readBlocks(in, N);
            TreeSet<Double> ken = readBlocks(in, N);            
            
            printResult(out, caseno, deceitfulWar(new TreeSet(nao), new TreeSet(ken), N) + " " + war(nao, ken, N));
        }                
        
        out.flush();
    }
    
    private static int deceitfulWar(TreeSet<Double> nao, TreeSet<Double> ken, int N) {
        int naoScore = 0;
        for (int i = 0; i < N; i++) {
            if (nao.first() < ken.first()) {
                nao.pollFirst();
                ken.pollLast();
            }
            else {
                naoScore++;
                nao.pollFirst();
                ken.pollFirst();
            }
        }
        
        return naoScore;
    }
    
    private static int war(TreeSet<Double> nao, TreeSet<Double> ken, int N) {
        int naoScore = 0;
        for (int i = 0; i < N; i++) {
            Double chosenNao = nao.pollFirst();
            Double chosenKen = ken.ceiling(chosenNao);
            if (chosenKen == null) {
                naoScore++;
                ken.pollFirst();
            }
            else {
                ken.remove(chosenKen);
            }
        }
        
        return naoScore;
    }
    
    private static TreeSet<Double> readBlocks(Scanner in, int N) {
        TreeSet<Double> a = new TreeSet<>();
        for (int i = 0; i < N; i++)
            a.add(in.nextDouble());
        return a;
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
}
