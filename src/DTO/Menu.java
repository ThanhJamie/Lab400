/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Vector;
import java.util.Scanner;

public class Menu extends Vector<String> {

    Scanner sc = new Scanner(System.in);
    private String[] choices;
    int n = 0;

    public Menu() {
        super();
    }

    void addMenuItem(String S) {
        this.add(S);
    }

    public int getChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean check;

        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
        System.out.println("_______________________________________________");
        do {
            System.out.print("Select 1..5: ");
            check = false;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                check = true;
            }
        } while (check);
        return choice;
    }

}
