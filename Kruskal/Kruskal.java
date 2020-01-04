/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Muhtasim
 */
public class Kruskal {

    int[] p;
    int n;
    int[] rank;
    int e;
    int[] w;
    ArrayList<LinkedList<Integer>> list;

    public void kruskal(int[][] G, int key, int node, int edge) {
        n = node;
        p = new int[n];
        e = edge;
        list = new ArrayList<LinkedList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            LinkedList<Integer> node1 = new LinkedList<Integer>();
            list.add(i, node1);
            list.get(i).add(i);
        }
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            makeSet(i);
        }
        w = new int[e * 2];
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (G[i][j] != 0) {
                    w[c] = G[i][j];
                    c++;
                }
            }
        }
        Arrays.sort(w);
        for (int k = 0; k < w.length; k += 2) {
            int u;
            int v;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (G[i][j] == w[k]) {
                        u = i;
                        v = j;
                        if (findSet(u) != findSet(v)) {
                            list.get(u).add(v);
                            list.get(u).add(w[k]);
                            union(u, v);
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("__________________________________________");
        System.out.println("MST in Adjacency List form");
        System.out.println("__________________________________________");
        for (int p = 0; p < n; p++) {
            System.out.println(list.get(p));
        }
    }

    public void makeSet(int x) {
        p[x] = x;
        rank[x] = x;
    }

    public void link(int x, int y) {
        if (rank[x] > rank[y]) {
            p[y] = x;
        } else {
            p[x] = y;
            if (rank[x] == rank[y]) {
                rank[y] = rank[y] + 1;
            }
        }
    }

    public void union(int x, int y) {
        link(findSet(x), findSet(y));
    }

    public int findSet(int x) {
        if (x != p[x]) {
            p[x] = findSet(p[x]);
        }
        return p[x];
    }
}
