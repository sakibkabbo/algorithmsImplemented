/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author 14101055
 */
public class BellmanFord {

    int n;
    int[] d;
    int[] p;
    int source;
    Stack link;

    public boolean bellmanFord(ArrayList<LinkedList<Integer>> G, int key, int node) {
        n = node;
        source = key;
        intializeSingleSource(G, key);
        for (int f = 0; f <= n; f++) {
            for (int i = 0; i <= n - 1; i++) {
                LinkedList<Integer> l = G.get(i);
                Object[] s = l.toArray();
                int u = (int) s[0];
                int c = 1;
                if (s.length >= 3) {
                    for (int j = 0; j < s.length; j += 3) {
                        int v = (int) s[c];
                        c++;
                        int w = (int) s[c];
                        c++;
                        relax(u, v, w);

                    }

                }
            }
            for (int i = 0; i < n - 1; i++) {
                LinkedList<Integer> l = G.get(i);
                Object[] s = l.toArray();
                int u = (int) s[0];
                int c = 1;
                if (s.length >= 3) {
                    for (int j = 0; j < s.length; j += 3) {
                        int v = (int) s[c];
                        c++;
                        int w = (int) s[c];
                        c++;
                        if (d[v] > d[u] + w && f == n) {
                            System.out.println("Negative Cycle Present");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void intializeSingleSource(ArrayList<LinkedList<Integer>> G, int key) {
        d = new int[n];
        p = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = 5000;
        }
        d[key] = 0;
    }

    public void relax(int u, int v, int w) {
        if (d[v] > d[u] + w) {
            d[v] = d[u] + w;
            p[v] = u;
        }
    }

    public void print(int key) {
        System.out.println();
        System.out.println("__________________________________________");
        System.out.println("Weight of Paths from source");
        System.out.println("__________________________________________");
        for (int i = 0; i < d.length; i++) {
            System.out.print(key + "->" + i + " ");
            System.out.println(d[i]);
        }
        System.out.println();
        System.out.println("__________________________________________");
        System.out.println("Shortest paths from source");
        System.out.println("__________________________________________");
        for (int i = 0; i < p.length; i++) {
            System.out.print(source + "->" + i + " : ");
            path(i);
            System.out.println();
        }
    }

    public void path(int sourcefor) {
        System.out.print(sourcefor + " ");
        if (sourcefor != 0) {
            System.out.print(p[sourcefor] + " ");
        }
        sourcefor = p[sourcefor];
        if (sourcefor != source) {
            path(p[sourcefor]);
        }

    }
}
