/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Muhtasim
 */
public class DFS {

    int time;
    int n;
    int[][] info;
    int r = 0;
    Queue ps = new LinkedList();

    public void dfs(ArrayList<LinkedList<Integer>> G, int key, int node) {
        n = node;
        if (r == 0) {
            info = new int[node][4];
            r++;
        }
        time = 0;
        dfsvisit(G, key);
        for (int i = 0; i < n; i++) {
            if (info[i][0] == 0) {
                dfsvisit(G, i + 1);
            }
        }
    }

    public void dfsvisit(ArrayList<LinkedList<Integer>> G, int key) {
        info[key - 1][0] = 1;
        time = time + 1;
        info[key - 1][2] = time;
        LinkedList<Integer> l = G.get(key - 1);
        Object[] s = l.toArray();
        for (int i = 1; i < s.length; i++) {
            if (info[(int) s[i] - 1][0] == 0) {
                info[(int) s[i] - 1][1] = key;
                dfsvisit(G, (int) s[i]);
            }
        }
        info[key - 1][0] = 2;
        ps.add(key);
        info[key - 1][3] = time + 1;
    }

    public void printInfo() {
        System.out.println();
        System.out.println("Sequence of Completion");
        while (!ps.isEmpty()) {
            System.out.print(ps.remove() + " ");
        }
        System.out.println();
        System.out.println("__________________________");
        System.out.println();
        System.out.println("Predecessors");
        System.out.println("1 " + "2 " + "3 " + "4 " + "5 " + "6 ");
        for (int i = 0; i < n; i++) {
            System.out.print(info[i][1] + " ");
        }
    }

}
