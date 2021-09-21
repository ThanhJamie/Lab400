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
import java.text.SimpleDateFormat;
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
        newName = Valdation.getString("Enter the name of food: ", "Invalid", false);
        newType = Valdation.getString("Enter the type of food: ", "Invalid", false);
        newPlace = Valdation.getString("Enter the place of food: ", "Invalid", false);
        newWeight = Valdation.getDouble("Enter the weight of food: ", "Invalid. Please try again!", 0, Double.MAX_VALUE);
        boolean t = true;
        do {            
            System.out.print("Enter the expired date of food: ");
            newExpiredDate = sc.nextLine().trim();
            if(checkValidDate(newExpiredDate)){
                String exp = newExpiredDate;
                this.add(new Food(newID, newName, newWeight, newType, newPlace, exp));
                t = false;
            }else{
                System.err.println("Invalid Date");
            }
        } while (t);
        System.err.println("Succesfull");
        String a = Valdation.getInputYN("Do you want to add another food(Y/N): ","Invalid", false);
        if(a.equalsIgnoreCase("Y")){
            addFood();
        }
    }

    public void findName() {
        int s = this.size();
        int count = 0;
        String findName = Valdation.getString("Enter the name to search: ", "Invalid", false);
        for (int i = 0; i < s; i++) {
            String tempName = this.get(i).getName();
            if (tempName.contains(findName)) {
                System.out.println(this.get(i).toString());
                count++;
            }
        }
        if (count == 0) {
            System.err.println("No have that name");
        }
        String a = Valdation.getInputYN("Do you want to search another food(Y/N): ","Invalid", false);
        if(a.equalsIgnoreCase("Y")){
            findName();
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
            String a = Valdation.getInputYN("Are you sure to remove this ID(Y/N): ","Invalid", false);
            if(a.equalsIgnoreCase("Y")){
            this.remove(pos);
            System.err.println("The food have ID:  " + IDremove + " has been remove");
        }
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

//
//    public void printFood() {
//        if (this.isEmpty()) {
//            System.err.println("Empty List");
//            return;
//        }
//        Collections.sort(this);
//        System.out.println("----FoodList----");
//        this.forEach((x) -> {
//            x.output();
//        });
//    }    
    
    public void listSortAndPrint() {
        Food e = null;
        int n = this.size();
        if(this.isEmpty()){
            System.err.println("Empty list");
            return;
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                if (this.get(j+1).getExpiredDate().compareTo(this.get(j).getExpiredDate()) > 0) {
                    e = this.get(j);
                    this.set(j, this.get(j+1));
                    this.set(j+1, e);
                }
            }
        }
        System.out.println("----FoodList----");
        this.forEach((item) -> {
            System.out.println(item.toString());
        });
    }
    public static boolean checkValidDate(String input) {
        String formatString = "dd/MM/yyyy";

        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(input);
        } catch(Exception e){
            return false;
        }

        return true;
    }
}
