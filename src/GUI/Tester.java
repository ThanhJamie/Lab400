/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.*;
import java.util.Scanner;
import Manager.*;
import java.io.IOException;

/**
 *
 * @author GMT
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        int choice = 0;
        String fileName = "food.dat";
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        menu.add("Welcome to Food Management - @ 2021 by <SE150380 - Dang Chi Thanh>");
        menu.add("Select the options below:");
        menu.add("1. Add a new food");
        menu.add("2. Search a food by name");
        menu.add("3. Remove the food by ID");
        menu.add("4. Print the food list in the descending order of expired date");
        menu.add("5. Save file");
        menu.add("6. Quit");
        Manager list = new Manager();
        if (!list.loadFromFile(fileName)) {
            System.err.println("Load food.dat failed");
        }else{
            System.err.println("Load food.dat succesful");
                    
        }
        do {
            System.out.println("-------Food management in a refrigerator-------");
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.addFood();
                    break;
                case 2:
                    list.findName();
                    break;
                case 3:
                    list.removeID();

                    break;
                case 4:
                    list.listSortAndPrint();
                    break;
                case 5:
                    String t = Valdation.getInputYN("Save changes Y/N?:", "Invalid", false);
                    if(t.equalsIgnoreCase("Y")){
                        list.savetoFile(fileName);
                        System.err.println("Save succesful");
                    }else if (t.equalsIgnoreCase("N")){
                        break;
                    }
                    break;
                case 6:
                    System.exit(0);
            }
        } while (choice >= 1 || choice <= 6);
    }

}
