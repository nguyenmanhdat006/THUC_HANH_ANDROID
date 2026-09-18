package com.thuc_hanh.quan_ly_sinh_vien;

import android.nfc.TagLostException;

public class Student {
    private String id;
    private String name;
    private int age;
    private double gpa;

    public Student(String id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public double getGpa() {
        return this.gpa;
    }

    public String getRank(){
        if (gpa >= 8.5) {
            return "Giỏi";
        } else if (gpa >=7.0) {
            return "Khá";
        } else if (gpa >=5.0) {
            return "Trung Bình";
        } else {
            return "Yếu";
        }
     }

    public String displayInfo() {
        return "Mã SV: " + id +
                "\nHọ tên: " + name +
                "\nTuổi: " + age +
                "\nGPA: " + gpa +
                "\nXếp loại: " + getRank();
    }
}
