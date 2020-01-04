/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Muhtasim
 */
public class AstarSearch {

    int start = 0;
    int startchk = 0;
    int finish = 0;
    ArrayList<LinkedList<Object>> list = null;
    PriorityQueue<Integer> openlist = new PriorityQueue<Integer>();
    ArrayList<Integer> closedlist = new ArrayList<Integer>();
    LinkedList<Integer> path2 = null;
    double[] fcost;
    int node;
    double[] heuristic;
    int[] came_from = null;
    int shdist = 0;

    public AstarSearch(ArrayList<LinkedList<Object>> l, double[] h, int s, int f, int n) {
        list = l;
        start = s;
        startchk = s;
        finish = f;
        heuristic = h;
        fcost = new double[n];
        for (int i = 0; i < n; i++) {
            fcost[i] = 5000;
        }
        node = n;
        openlist.add(s);
        came_from = new int[n];
    }

    public void Astar(int s, int f) {
        int q = 90;
        while (!openlist.isEmpty()) {
            if (s == start) {
                q = s;
                start = 5000;
                openlist.remove(s);
                closedlist.add(q);
            } else {
                q = getMinIdx();
                if (openlist.contains(q)) {
                    openlist.remove(q);
                }
                if (!closedlist.contains(q)) {
                    closedlist.add(q);
                }
            }

            for (int i = 1; i < list.get(q).size(); i += 2) {
                if (!closedlist.contains((int) list.get(q).get(i))) {
                    openlist.add((int) list.get(q).get(i));
                }
                double d = (double) heuristic[(int) list.get(q).get(i)] + pathcost2((int) list.get(q).get(i), q);
                if (fcost[(int) list.get(q).get(i)] > d) {
                    fcost[(int) list.get(q).get(i)] = d;
                    came_from[(int) list.get(q).get(i)] = q;
                }
            }
            int idx = getMinIdx();
            if (idx == finish) {
                break;
            }
        }
        System.out.println("Heuristic Values of each Node :");
        System.out.println("   0    1   2    3   4   5   6  ");
        for (int i = 0; i < fcost.length; i++) {
            System.out.print(fcost[i] + " ");
        }
        System.out.println();
        System.out.println();
        path(startchk);
        pathcost();

    }

    public int getMinIdx() {
        double min = 5000;
        int idx = 80;
        for (int i = 0; i < fcost.length; i++) {
            if (fcost[i] < min && !closedlist.contains(i)) {
                min = fcost[i];
                idx = i;
            }
        }
        return idx;
    }

    public void path(int ter) {
        path2 = new LinkedList<Integer>();
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.add(finish);
        int f = finish;
        while (f != ter) {
            path.add(came_from[f]);
            f = came_from[f];
        }
        System.out.print("Shortest path sequence: ");
        while (path.isEmpty() != true) {
            path2.add(path.getLast());
            System.out.print(path.removeLast() + " ");
        }
        System.out.println();
    }

    public void pathcost() {
        double pathcst = 0;
        int s = path2.removeFirst();
        int f = path2.getLast();
        while (s != f) {
            int n = path2.getFirst();
            for (int i = 1; i < list.get(s).size(); i += 2) {
                if ((int) list.get(s).get(i) == n) {
                    pathcst += (double) list.get(s).get(i + 1);
                }
            }
            s = path2.removeFirst();
        }
        System.out.println("Cost of Path with Best Heuristic = " + pathcst);
    }

    public double pathcost2(int cur, int fin) {
        double pathcst = 0;
        int s = cur;
        int f = fin;
        while (s != f) {
            int n = f;
            for (int i = 1; i < list.get(f).size(); i += 2) {
                if ((int) list.get(f).get(i) == s) {
                    pathcst += (double) list.get(f).get(i + 1);
                }
            }
            f = came_from[n];
            s = n;
        }
        return pathcst;
    }
}
