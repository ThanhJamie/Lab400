/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author GMT
 */
public class Food implements Comparable<Food> {

    private String ID;
    private String name;
    private double weight;
    private String type;
    private String place;
    private String expiredDate;

    public Food() {
        ID = "";
        expiredDate = "";
        name = "";
        place = "";
        type = "";
        weight = 0.0;
    }

    public Food(String ID, String name, double weight, String type, String place, String expiredDate) {
        this.ID = ID;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return ID + ", " + name + ", " + weight + ", " + type + ", " + place + ", " + expiredDate;
    }

    @Override
    public int compareTo(Food t) {
        if (ID.compareTo(t.getID()) > 0) {
            return -1;
        } else if (ID.compareTo(t.ID) < 0) {
            return 1;
        }
        return 0;
    }

    public void output() {
        System.out.println(ID + ", " + name + ", " + weight + ", " + type + ", " + place + ", " + expiredDate);
    }
}
