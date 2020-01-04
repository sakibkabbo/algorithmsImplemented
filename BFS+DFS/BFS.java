/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Muhtasim
 */
public class BFS {

    Queue Q = new LinkedList();
    int n;
    int[][] info;
    int r = 0;
    Queue ps = new LinkedList();

    public void bfs(ArrayList<LinkedList<Integer>> G, int key, int node) {
        n = node;
        if (r == 0) {
            info = new int[node][3];
            for (int i = 0; i < n; i++) {
                info[i][1] = 8000;
                info[i][0] = 0;
            }
            r++;
        }
        Q.add(key);
        info[key - 1][0] = 1;
        info[key - 1][1] = 0;
        while (!Q.isEmpty()) {

            int u = (int) Q.remove() - 1;
            LinkedList<Integer> l = G.get(u);
            Object[] s = l.toArray();
            info[(int) s[0] - 1][0] = 2;
            if (!ps.contains(u + 1)) {
                ps.add(u + 1);
            }
            for (int i = 1; i < s.length; i++) {
                if (info[(int) s[i] - 1][1] == 8000) {
                    info[(int) s[i] - 1][0] = 1;
                    info[(int) s[i] - 1][2] = u + 1;;
                    info[(int) s[i] - 1][1] = info[u][1] + 1;
                    Q.add(s[i]);
                }
            }
        }
        for (int j = 1; j < n + 1; j++) {
            if (info[j - 1][0] == 0) {
                bfs(G, j, n);
            }
        }
    }

    public void printInfo() {
        System.out.println();
        System.out.println("Distance Values");
        System.out.println("1 "+"2 "+"3 "+"4 "+"5 "+"6 "+"7 "+"8");
        for (int i = 0; i < n; i++) {
            System.out.print(info[i][1]+" ");
        }
        System.out.println();
        System.out.println("__________________________"); 
        System.out.println();
        System.out.println("Sequence of Traversal");
        while (!ps.isEmpty()) {
            System.out.print(ps.remove() + " ");
        }

    }

}
