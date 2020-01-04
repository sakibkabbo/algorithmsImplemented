/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Muhtasim
 */
public class KruskalTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File dimensions = new File("C:\\Users\\Muhtasim\\Documents\\BRAC docs\\Cse documents\\CSE221\\Lab\\Lab06\\Lab06soln\\Lab06\\src\\lab06\\Graph");
            Scanner post;
            post = new Scanner(dimensions);
            int Node = Integer.parseInt(post.nextLine());
            int Edge = Integer.parseInt(post.nextLine());
            int[][] pos = new int[Node][Node];
            String[] listelements;
            while (post.hasNext()) {
                String pair = post.nextLine();
                listelements = pair.split(" ");
                int i1 = Integer.parseInt(listelements[0]);
                int i2 = Integer.parseInt(listelements[1]);
                int i3 = Integer.parseInt(listelements[2]);
                pos[i1][i2] = i3;
            }
            for (int l = 0; l < Node; l++) {
                for (int m = 0; m < Node; m++) {
                    System.out.print(pos[l][m]+ " ");
                }
                System.out.println();
            }
            Kruskal k = new Kruskal();
            k.kruskal(pos, 0, Node, Edge);
            
        } catch (FileNotFoundException ex) {
            System.out.println("No file");
        }
    }
}
