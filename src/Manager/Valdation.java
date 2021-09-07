/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author GMT
 */
public class Valdation {
    private final static Scanner in = new Scanner(System.in);

    public static String getString(String input, String error, boolean nothing) {
        System.out.print(input);
        String output = new Scanner(System.in).nextLine().trim();
        if (!output.isEmpty()) {
            return output;
        }
        if (nothing) {
            return null;
        }
        System.out.println(error);
        return getString(input, error, nothing);
    }

    public static int getInteger(String input, String error) {
        try {
            return Integer.parseInt(getString(input, error, false));
        } catch (Exception e) {
            System.out.println(error);
            return getInteger(input, error);
        }
    }
    public static double getDouble(String input, String error) {
        try {
            return Double.parseDouble(getString(input, error, false));
        } catch (Exception e) {
            System.err.println(error);
            return getDouble(input, error);
        }
    }

    public static double getDouble(String input, String error, double min) {
        double output = getDouble(input, error);
        if (output >= min) {
            return output;
        }
        System.out.println("Just input a number from " + min);
        return getDouble(input, error, min);
    }

    public static double getDouble(String input, String error, double min, double max) {
        double output = getDouble(input, error, min);
        if (output <= max) {
            return output;
        }
        System.out.println("Just input a number from " + min + " to " + max);
        return getDouble(input, error, min, max);
    }
    public static boolean checkInputYN() {
        System.out.print("Do you want to replace(Y/N)? ");

        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
    public static String checkInputString() {

        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }
    public static boolean addFromFile(HashMap<String, String> hm){
        String path = "dic.txt";
        File f = new File(path);
        if(!f.exists())
            return false;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            hm.clear();
            while((details = bf.readLine()) != null){
                StringTokenizer stk = new StringTokenizer(details, ",");
                String key = stk.nextToken();
                String mean = stk.nextToken();
                hm.put(key, mean);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
    public static boolean saveToFile(HashMap<String, String> hm){
        String path = "dic.txt";
        if(hm.size() == 0)
            return false;
        File f = new File(path);
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            
            Iterator it = hm.keySet().iterator();
            while(it.hasNext()){
                String key = (String)(it.next());
                String val = (String)(hm.get(key));
                pw.println(key + ',' + val);
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
    public static String getCode(String input, String error, String format) {
        String output = getString(input, error, false);
        output = output.toUpperCase();
        if (output.matches(format)) {
            return output;
        }
        System.out.println(error);
        return getCode(input, error, format);
    }
//    public void AddFromFile(String fname){
//        try{
//            File f = new File(fname);
//            if (!f.exists()) return;
//            FileReader fr = new FileReader(f);
//            BufferedReader bf = new BufferedReader(fr);
//            String details;
//            while ((details = bf.readLine())!=null){
//                StringTokenizer stk = new StringTokenizer(details,",");
//                String code  = stk.nextToken().toUpperCase();
//                String name = stk.nextToken().toUpperCase();
//                int salary = Integer.parseInt(stk.nextToken());
//                // Create an employee
//                Employee emp  = new Employee(code, name, salary);
//                this.add(emp);
//            }
//            bf.close();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
//    }
//    public void savetoFile(String fName){
//        if(this.size()==0){
//            System.out.println("Empty list");
//             return;
//        }
//        try{
//            File f = new File(fName);
//            FileWriter fw = new FileWriter(f);
//            PrintWriter pw = new PrintWriter(fw);
//            for (Employee x:this){
//                pw.println(x.getCode()+ "," + x.getName() + "," + x.getSalary());
//            }
//            pw.close();fw.close();
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//    }
    
}
