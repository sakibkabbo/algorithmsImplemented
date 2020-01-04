/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Muhtasim
 */
public class Dijkstra {

    Queue v = new LinkedList();
    int n;
    int source;
    int[] dist;
    int[] prev;
    int[] var;
    Stack<Integer> S = new Stack<Integer>();
    PriorityQueue<Integer> Q = new PriorityQueue<Integer>();
    int dcheck = 9000;
    int runstop = 1;

    public void dijkstra(ArrayList<LinkedList<Integer>> G, int key, int node) {
        n = node;
        source = key;
        intializeSingleSource(G, key);
        for (int i = 0; i < n; i++) {
            Q.add(i);
        }
        S.add(key);
        System.out.println(Q);
        while (!Q.isEmpty()) {
            System.out.println(dcheck);
            int u = extractMin();
            dcheck = 9000;
            System.out.println(u);
            LinkedList<Integer> l = G.get(u);
            Object[] s = l.toArray();
            int c = 1;
            int sadd = 8900;
            int wcom = 0;
            if (s.length >= 3) {
                for (int j = 0; j < s.length; j += 3) {
                    int v = (int) s[c];
                    c++;
                    int w = (int) s[c];
                    c++;
                    if (j == 0 && Q.contains(v)) {
                        wcom = w;
                        sadd = v;
                    } else if (wcom > w && Q.contains(v)) {
                        wcom = w;
                        sadd = v;
                    }
                    relax(u, v, w);
                }
                if (!S.contains(sadd) && Q.contains(sadd) && sadd != source && dcheck > 9000) {
                    S.add(sadd);
                }
            }
            if (s.length >= 3 && dcheck == 9000) {
                System.out.println("iuhh");
                wcom = 5000;
                int check = dist[0];
                int idx = 90;
                for (int i = 0; i < n; i++) {
                    check = dist[i];
                    if (check < wcom && Q.contains(i) && !S.contains(i)) {
                        idx = i;
                    }
                }
                System.out.println(idx);
                S.add(idx);

            }

            System.out.println(S);
            System.out.println(Q);
            runstop++;
            if (runstop > 10) {
                break;
            }
        }
    }

    public void intializeSingleSource(ArrayList<LinkedList<Integer>> G, int key) {
        dist = new int[n];
        prev = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = 5000;
        }
        dist[key] = 0;
    }

    public void relax(int u, int v, int w) {
        if (dist[v] > dist[u] + w) {
            dcheck++;
            dist[v] = dist[u] + w;
            prev[v] = u;
            System.out.println(u + " " + v + " " + w);
        }
    }

    public int extractMin() {
        int minindex = S.lastElement();
        Q.remove(minindex);
        System.out.print(minindex);
        return minindex;
    }

    public void path(int sourcefor) {
        System.out.print(sourcefor + " ");
        if (sourcefor != 0) {
            System.out.print(prev[sourcefor] + " ");
        }
        sourcefor = prev[sourcefor];
        if (sourcefor != source) {
            path(prev[sourcefor]);
        }

    }

    public void printInfo() {
        System.out.println();
        System.out.println("Distances from source");
        System.out.println("----------------------------");
        for (int i = 0; i < n; i++) {
            System.out.print(source + " --> " + i + "  ");
            System.out.println(dist[i]);
        }
        System.out.println();
        System.out.println("__________________________________________");
        System.out.println("Shortest paths from source");
        System.out.println("__________________________________________");
        for (int i = 0; i < prev.length; i++) {
            System.out.print(source + "->" + i + " : ");
            path(i);
            System.out.println();
        }
    }
}
