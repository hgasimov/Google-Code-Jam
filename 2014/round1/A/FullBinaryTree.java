package codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Code Jam 2014 Round 1A, Problem B
 */
public class FullBinaryTree {
    private static String working_dir = "io//codejam//2014//FullBinaryTree//";
    private static String input_filename = "B-large-practice.in";
    private static String output_filename = "large.out.txt";    
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(working_dir + input_filename));
        PrintWriter out = new PrintWriter(new File(working_dir + output_filename));
        
        int T = in.nextInt();
        for (int caseno = 1; caseno <= T; caseno++) {
            int N = in.nextInt();
            List<Integer>[] G = new List[N+1];
            for (int j = 1; j <= N; j++) G[j] = new ArrayList<>();
                    
            for (int j = 1; j < N; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                G[x].add(y);
                G[y].add(x);
            }
            
            int minDel = N;
            for (int root = 1; root <= N; root++) {                  
                minDel = Math.min(minDel, solve(G, root, 0));
            }
            
            printResult(out, caseno, String.valueOf(minDel));
        }
        
        out.flush();
    }
    
    private static int solve(List<Integer>[] G, int root, int parent) {
        List<Integer> children = getChildren(G, root, parent);
        
        if (children.isEmpty()) {
            return 0;
        }
        else if (children.size() == 1) {
            return count(G, root, parent) - 1;
        }
        else if (children.size() == 2) {
            return solve(G, children.get(0), root) + solve(G, children.get(1), root);
        }
        else if (children.size() > 2){
            int minDel = G.length;
            int[] cnt = new int[children.size()];
            for (int i = 0; i < children.size(); i++) cnt[i] = count(G, children.get(i), root);
            int sum = sum(cnt);
            
            int[] slv = new int[children.size()];
            for (int i = 0; i < children.size(); i++) slv[i] = solve(G, children.get(i), root);
            
            for (int i = 0; i < children.size()-1; i++) {                
                for (int j = i+1; j < children.size(); j++) {                    
                    int del = sum - cnt[i] - cnt[j] + slv[i] + slv[j];
                    minDel = Math.min(minDel, del);
                }
            }
            return minDel;
        }
        
        return G.length;
    }
    
    private static int count(List<Integer>[] G, int root, int parent) {
        int sum = 1;
        for (int i : G[root])
            if (i != parent) 
                sum += count(G, i, root);
        return sum;
    }

    private static int sum(int[] cnt) {
        int sum = 0;
        for (int i : cnt) sum += i;
        return sum;
    }
    
    private static void printResult(PrintWriter out, int caseno, String s) {
        s = "Case #" + caseno + ": " + s;
        out.println(s);
        System.out.println(s);
    }
    
    private static List<Integer> getChildren(List<Integer>[] G, int root, int parent) {
        List<Integer> children = new ArrayList<>();
        for (int i = 0; i < G[root].size(); i++)
            if (G[root].get(i) != parent) 
                children.add(G[root].get(i));
        return children;
    }
}
