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
public class Heapsort {

    int[] a = {3, 6, 78, 34, 23, 1, 3, 23, 4, 46};

    public Heapsort() {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public void MaxHeapify(int i) {
        int l = 2 * i;
        int r = (2 * i) + 1;
        int maxval;
        if (l <= a.length - 1 && a[l] > a[i]) {
            maxval = l;
        } else {
            maxval = i;
        }
        if (r <= a.length - 1 && a[r] > a[maxval]) {
            maxval = r;
        }
        if (maxval != i) {
            int temp = a[i];
            a[i] = a[maxval];
            a[maxval] = temp;
            MaxHeapify(maxval);
        }

    }

    public void BuildMaxHeap() {
        for (int i = a.length / 2; i > 0; i--) {
            MaxHeapify(i);
        }
    }

    public void HeapSort() {
        BuildMaxHeap();
        int size = a.length - 1;
        for (int i = a.length - 1; i > 0; i--) {
            int temp = a[1];
            a[1] = a[i];
            a[i] = temp;
            System.out.print(a[size] + " ");
            a[size] = 0;
            size--;
            MaxHeapify(1);

        }
    }

    public void HeapIncreaseKey(int i) {
        int p;
        int index;

        if (i <= 1) {

        } else {

            p = a[i / 2];
            index = i / 2;

            if (p > a[i]) {

            } else {
                int temp = p;
                p = a[i];
                a[i] = temp;
                MaxHeapify(0);
            }
        }
    }

    public void Insert(int k) {
        BuildMaxHeap();
        int[] b = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        a = b;
        int size = a.length - 1;
        a[size] = k;
        BuildMaxHeap();
        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }

    public void Delete(int y) {
        int size=a.length-1;
        if(size<=1){
            
        }else{
          for (int z=1; z<a.length; z++){
          if(a[z]==y){      
          
          int temp = a[z];
                a[z] = a[size];
                a[size] = temp;
                a[size]=0;
                size--;
                MaxHeapify(1);  
        }
          }
        }
                for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
