/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import DTO.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author GMT
 */
public class Manager extends Vector<Food> {

    Scanner sc = new Scanner(System.in);

    public Manager() {
        super();
    }

    public void addFood() {
        String newID, newName, newType, newPlace, newExpiredDate;
        double newWeight;
        int pos;
        System.out.println("--Enter the New Food--");
        do {
            System.out.print("Enter the ID of food: ");
            newID = sc.nextLine().trim().toUpperCase();
            pos = find(newID);
            if (pos >= 0) {
                System.err.println("The ID is duplicated");
            }
        } while (pos >= 0);
        System.out.print("Enter the name of food: ");
        newName = sc.nextLine().trim();
        System.out.print("Enter the type of food: ");
        newType = sc.nextLine().trim();
        System.out.print("Enter the place of food: ");
        newPlace = sc.nextLine().trim();
        System.out.print("Enter the expired date of food: ");
        newExpiredDate = sc.nextLine().trim();
        newWeight = Valdation.getDouble("Enter the weight of food: ", "Invalid. Please try again!", 0, Double.MAX_VALUE);
        this.add(new Food(newID, newName, newWeight, newType, newPlace, newExpiredDate));
        System.err.println("Successful! ");
    }

    public void findName() {
        int s = this.size();
        int count = 0;
        System.out.print("Enter the nameFood to find: ");
        String findName = sc.nextLine();
        for (int i = 0; i < s; i++) {
            String tempName = this.get(i).getName();
            if (tempName.contains(findName)) {
                System.out.println(this.get(i).toString());
                count++;
            };
        }
        if (count == 0) {
            System.err.println("No have that name");
        }
    }

    public void removeID() {
        String IDremove;
        System.out.print("Enter the ID to remove food: ");
        IDremove = sc.nextLine().toUpperCase();
        int pos = find(IDremove);
        if (pos < 0) {
            System.err.println("The ID does not exist");
        } else {
            this.remove(pos);
            System.err.println("The food have ID:  " + IDremove + "has been remove");
        }
    }

    public void printFood() {
        if (this.size() == 0) {
            System.err.println("Empty List");
            return;
        }
        Collections.sort(this);
        System.out.println("----FoodList----");
        for (Food x : this) {
            x.output();
        }
    }

    private int find(String aCode) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(aCode)) {
                return i;
            }
        }
        return -1;
    }

    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (ID == null ? this.get(i).getID() == null : ID.equals(this.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }

    public boolean savetoFile(String filename) throws FileNotFoundException {
        File f = new File(filename);
        if (!f.exists()) {
            return false;
        }
        try (PrintWriter pw = new PrintWriter(f)) {
            this.forEach(item -> pw.println(item.toString()));
        }
        return true;
    }

    public boolean loadFromFile(String fileName) throws FileNotFoundException, IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            return false;
        }
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String details;
        while ((details = br.readLine()) != null) {
            boolean check = true;
            StringTokenizer stk = new StringTokenizer(details);
            String id = stk.nextToken(",").trim();
            String name = stk.nextToken(",").trim();
            Double weight = Double.parseDouble(stk.nextToken(",").trim());
            String type = stk.nextToken(",").trim();
            String place = stk.nextToken(",").trim();
            String exp = stk.nextToken().trim();
            Food food = new Food(id, name, weight, type, place, exp);
            if (check) {
                this.add(food);
            }
        }
        return true;
    }

    public void print() {
        if (this.size() == 0) {
            System.out.println("Empty list.");
            return;
        }
        Collections.sort(this);
        System.out.println("\nFOOD LIST");
        System.out.println("--------------------------------");
        for (Food x : this) {
            x.toString();
        }
    }
}
