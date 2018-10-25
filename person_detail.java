package com.matrix.morpheus.contactlist;

import java.util.HashMap;

public class person_detail {
    private int number;
    private String name;
    private int photo;
    static int count = 0;
    private int id;
    public HashMap<String, Integer> namePhone = new HashMap<>();
    public int[] relation;

    public person_detail(int count, String name, int number, int[] relation){
        this.id = count;
        this.name = name;
        this.number = number;
        this.relation = relation;
        namePhone.put(name, count);
        count++;
    }


    // Class methods
    public int getID(){
        return id;
    }
    public int getPhoto(){
        return photo;
    }
    public void setPhoto(int photo){
        this.photo = photo;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return number;
    }

    public int[] getRelation(){
        return relation;
    }

    public void setRelation(int[] relation){
        this.relation = relation;
    }
    public HashMap<String, Integer> getHash() { return namePhone; }
}
