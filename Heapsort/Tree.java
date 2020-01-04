/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

/**
 *
 * @author Muhtasim
 */
public class Tree {

    public static void main(String[] args) {
        Heapsort h = new Heapsort();

        int[] tree = {0, 100, 67, 54, 89, 34, 76, 199, 2, 8, 1};
        h.BuildMaxHeap();
    
        System.out.println(" ");
        System.out.println(" ");
        int[] tree2 = {0, 100, 67, 54, 89, 34, 76, 199, 2, 8, 1};
        h.Insert( 200);
                System.out.println(" ");
        h.Delete(46);
        System.out.println(" ");
        System.out.println(Math.sin(90));
    }
    }

