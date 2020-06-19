package com.example.healthwith.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private int id;

    private String name;
    private float height;
    private float weight;
    private int age;
    private boolean isMale;

    public User(String name, float height, float weight, int age, boolean isMale) {
        this.id = 1;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.isMale = isMale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public float getkCal() {
        if(this.isMale) {
            return (float) (66.4730 + 13.7516 * this.getWeight() + 5.0033 * this.getHeight() - 6.7550 * (getAge()-1));
        }
        else return (float) (655.0955 + 9.5634 * this.getWeight() + 1.8496 * this.getHeight() - 4.6756 * (getAge()-1));
    }

    public int getWaterAverage() {
        return (int) this.getWeight()*30;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", isMale=" + isMale +
                '}';
    }

    public static User populateData() {
        return new User("홍길동", 170f, 70f, 20, true);
    }
}
