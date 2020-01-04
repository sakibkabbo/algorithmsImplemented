/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

import java.util.Stack;

/**
 *
 * @author BUCC MSP
 */
public class LCS {

    int[][] cost;
    int[][] direction;
    int[][] prev;
    Stack<Object> s = new Stack<Object>();

    public void lcs(String a, String b) {
        char[] r = a.toCharArray();
        char[] c = b.toCharArray();
        for (int q = 0; q < r.length; q++) {
            System.out.print(r[q]);
        }
        System.out.println();
        for (int q = 0; q < c.length; q++) {
            System.out.print(c[q]);
        }
        System.out.println();
        cost = new int[a.length() + 1][b.length() + 1];
        direction = new int[a.length() + 1][b.length() + 1];
        for (int k = 1; k <= c.length; k++) {
            for (int l = 1; l <= r.length; l++) {
                if (c[k-1] == r[l-1]) {
                    cost[l][k] = cost[l - 1][k - 1] + 1;
                    direction[l][k] = 100;
                    s.add(r[l-1]);
                } else {
                    if (cost[l - 1][k] >= cost[l][k - 1]) {
                        cost[l][k] = cost[l - 1][k];
                        prev[l][k] = prev[l - 1][k];
                        direction[l][k] = 200;
                    }
                    if (cost[l - 1][k] <= cost[l][k - 1]) {
                        cost[l][k] = cost[l][k - 1];
                        direction[l][k] = 300;
                    }
                }
            }
        }
            System.out.println(s);
            for (int i = 0; i < r.length + 1; i++) {
                for (int j = 0; j < c.length + 1; j++) {
                    System.out.print(cost[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            for (int i = 0; i < r.length + 1; i++) {
                for (int j = 0; j < c.length + 1; j++) {
                    System.out.print(direction[i][j] + " ");
                }
                System.out.println();
            }

        
    }
}
