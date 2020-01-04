/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author 14101055
 */
public class BellmanFordTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File dimensions = new File("C:\\Users\\Muhtasim\\Documents\\BRAC docs\\Cse documents\\CSE221\\Lab\\Lab05\\Lab05Soln\\Lab05\\src\\lab05\\Graph");
            Scanner post;
            post = new Scanner(dimensions);
            int Node = Integer.parseInt(post.nextLine());
            int Edge = Integer.parseInt(post.nextLine());
            String[] listelements;
            final int Node1 = Node;
            ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(Node1);
            for (int i = 0; i < Node; i++) {
                LinkedList<Integer> node1 = new LinkedList<Integer>();
                list.add(i, node1);
                list.get(i).add(i);
            }
            while (post.hasNext()) {
                String pair = post.nextLine();
                listelements = pair.split(" ");
                int i1 = Integer.parseInt(listelements[0]);
                int i2 = Integer.parseInt(listelements[1]);
                int i3 = Integer.parseInt(listelements[2]);
                for (int i = 0; i < Node1; i++) {
                    if (i1 == i) {
                        list.get(i).addLast(i2);
                        list.get(i).addLast(i3);
                    }
                }
            }
            for (int x = 0; x < Node; x++) {
                System.out.println(list.get(x));
            }
            BellmanFord b = new BellmanFord();
            b.bellmanFord(list, 0, Node);
            b.print(0);
        } catch (FileNotFoundException ex) {
            System.out.println("No file");
        }
    }

}
