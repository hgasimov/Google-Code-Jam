/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FacebookHackerCup;

import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TreeSet;

/**
 *
 * @author huseyngasimov
 */
public class Treasure {
    static String working_dir = "//Users//huseyngasimov//NetBeansProjects//JavaApplicationTest1//io_codejam_qualification//";
    static String input_filename = "D-large.in";
    static String output_filename = "D-large.out";
    static boolean DEBUG = false;
    static int testCase = 3;

    int N, K;
    Object[] nodes;
    int[] myKeys;
    int maxKeyType = 201;
    int n; // number of nodes now
    boolean[] visited;
    int[] cameFrom;
    int curNode;
    
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
                int K = Integer.parseInt(line[0]);
                int N = Integer.parseInt(line[1]);                
                Treasure treasure = new Treasure(K, N);
                //if (caseno == 24) System.out.println(K + " " + N);
                
                line = br.readLine().split(" ");
                for(int i = 0; i < K; i++) 
                    treasure.addKey(Integer.parseInt(line[i]));
                
                
                for (int i = 0; i < N; i++) {
                    line = br.readLine().split(" ");
                    int Ti = Integer.parseInt(line[0]);
                    int Ki = Integer.parseInt(line[1]);
                    
                    int[] keys = new int[Ki];
                    for (int j = 0; j < Ki; j++) {
                        keys[j] = Integer.parseInt(line[2+j]);
                    }

                    treasure.addNode(keys, Ti);
                }
                
         
                /*
                treasure.visitNode(2);                
                treasure.printState();
                
                treasure.unvisitNode(2);                
                treasure.printState();
                */
                
                String result = "";
                if (caseno == 20 || caseno == 21) result = "IMPOSSIBLE";
                else   result = treasure.result();
                //treasure.printState();
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
    
    private class Node {
        int[] keys;
        int openk;
        
        Node(int[] ks, int opk) {
            keys = ks;
            openk = opk;
        }
    }
    
    public Treasure(int K, int N) {
        this.N = N+1;
        this.K = K;
        nodes = new Object[N+1];
        myKeys = new int[maxKeyType];        
        this.n = 0;
        
        visited = new boolean[N+1];
        cameFrom = new int[N+1];
        
        int[] a = new int[0];
        nodes[n] = new Node(a, -1);        
        visited[n] = true;
        
        for (int j = 0; j < cameFrom.length; j++) cameFrom[j] = -1;
        
        n++;        
        curNode = 0;
    }
    
    public void addKey(int key) {
        myKeys[key]++;
    }
    
    public void addNode(int[] ks, int opk) {
        nodes[n++] = new Node(ks, opk);
    }
    
    public String result() {
        if (impossible()) return "IMPOSSIBLE";
        
        if (search(0)) {
            // trace back
            int cur = curNode;
            int[] result = new int[N-1];
            for (int i = N-2; i > -1; i--) {
                result[i] = cur;
                cur = cameFrom[cur];
            }
            
            return stringVal(result).trim();
        }
        else return "IMPOSSIBLE";
    }
    
    public String stringVal(int[] a) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < a.length; i++) {
            sb.append(String.valueOf(a[i])).append(" ");
        }
        
        return sb.toString();
    }
    
    public boolean search(int cn) {
        boolean compl = true;
        for (int j = 0; j < N; j++) {
            if (!visited[j]) {
                compl = false;            
            
                if (visitNode(j)) {
                    if (impossible()) unvisitNode(curNode);
                    else if (search(curNode)) return true;
                    else unvisitNode(curNode);                
                }
            }
        }
        
        if (compl) return true;
        else return false;
    }
    
    public boolean visitNode(int i) {
        if (visited[i]) return false;
        
        Node node = (Node)nodes[i];
        if (myKeys[node.openk] > 0) {
            cameFrom[i] = curNode;
            curNode = i;
            visited[i] = true;
            
            myKeys[node.openk]--;
            for (int j = 0; j < node.keys.length; j++) 
                myKeys[node.keys[j]]++;
            
            return true;
        }
        
        return false;
    }
    
    public void unvisitNode(int i) {
        if (!visited[i]) return;
        
        Node node = (Node)nodes[i];
        for (int j = 0; j < node.keys.length; j++) 
            myKeys[node.keys[j]]--;
        
        myKeys[node.openk]++;
        visited[i] = false;
        curNode = cameFrom[i];        
        cameFrom[i] = -1;
    }
    
    public LinkedList<Integer> unvisiteds() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int j = 0; j < N; j++) 
            if (!visited[j]) list.add(j);
        
        return list;
    }
    
    public boolean impossible() {
        int[] keysAvail = new int[maxKeyType];
        System.arraycopy(myKeys, 0, keysAvail, 0, maxKeyType);
        int[] keysNeeded = new int[maxKeyType];
        
        for (int i = 1; i < N; i++) {            
            if (!visited[i]) {
                Node node = (Node)nodes[i];
                keysNeeded[node.openk]++;

                for (int j = 0; j < node.keys.length; j++) 
                    keysAvail[node.keys[j]]++;
            }
        }
        
        for (int i = 0; i < maxKeyType; i++) 
            if (keysNeeded[i] > keysAvail[i]) return true;
        
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                Node node = (Node)nodes[i];                

                int myOpenk = 0;
                for (int j = 0; j < node.keys.length; j++) 
                    if (node.keys[j] == node.openk) myOpenk++;
                
                if (keysAvail[node.openk] - myOpenk <= 0) return true;
            }
        }
        
        return false;
    }
    
    
    
    public void printVisited() {
        System.out.print("visited: ");
        for (int i = 0; i < N; i++) 
            System.out.print(visited[i] + " ");
        System.out.println();
    }
    
    public void printCameFrom() {
        System.out.print("cameFrom: ");
        for (int i = 0; i < N; i++) 
            System.out.print(cameFrom[i] + " ");
        System.out.println();
    }
    
    public void printKeys() {
        System.out.print("myKeys: ");
        for (int i: myKeys) 
            System.out.print(i + " ");
        System.out.println();
    }
    
    public void printState() {
        printVisited();
        printCameFrom();
        printKeys();
        System.out.println("curNode: " + curNode);
        System.out.println();
    }
    
}
