/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab07;

/**
 *
 * @author BUCC MSP
 */
public class LCSTester {

    public static void main(String[] args) {
        String s1 = "AXBCE";
        String s2 = "ABEX";
        LCS l = new LCS();
        l.lcs(s2, s1);
    }
    
}
