/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Muhtasim
 */
public class AstarSearchTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File dimensions = new File("C:\\Users\\Muhtasim\\Documents\\BRAC docs\\Cse documents\\CSE422\\Lab\\Lab02\\Lab02\\src\\lab02\\pathcost");
            Scanner post;
            post = new Scanner(dimensions);
            int Node = Integer.parseInt(post.nextLine());
            int Start = Integer.parseInt(post.nextLine());
            int Finish = Integer.parseInt(post.nextLine());
            String[] listelements;
            double[] heuristic;
            final int Node1 = Node;
            ArrayList<LinkedList<Object>> list = new ArrayList<LinkedList<Object>>(Node1);
            for (int i = 0; i < Node; i++) {
                LinkedList<Object> node1 = new LinkedList<Object>();
                list.add(i, node1);
                list.get(i).add(i);
            }
            while (post.hasNext()) {
                String pair = post.nextLine();
                listelements = pair.split(" ");
                int i1 = Integer.parseInt(listelements[0]);
                int i2 = Integer.parseInt(listelements[1]);
                double i3 = Double.parseDouble(listelements[2]);
                for (int i = 0; i < Node1; i++) {
                    if (i1 == i) {
                        list.get(i).addLast(i2);
                        list.get(i).addLast(i3);
                    }
                }
            }
            dimensions = new File("C:\\Users\\Muhtasim\\Documents\\BRAC docs\\Cse documents\\CSE422\\Lab\\Lab02\\Lab02\\src\\lab02\\heuristiccost");
            post = new Scanner(dimensions);
            heuristic = new double[Node];
            while (post.hasNext()){
                String pair = post.nextLine();
                listelements = pair.split(" ");
                int i = Integer.parseInt(listelements[0]);
                double d = Double.parseDouble(listelements[1]);
                heuristic[i] = d;
            }  
            AstarSearch as = new AstarSearch(list, heuristic, Start, Finish, Node);
            as.Astar(Start, Finish);

        } catch (FileNotFoundException ex) {
            System.out.println("No file");
        }

    }

}
