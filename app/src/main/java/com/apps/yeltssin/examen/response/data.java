package com.apps.yeltssin.examen.response;

import com.apps.yeltssin.examen.employee;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class data {
    @SerializedName("employees")
    ArrayList <employee> employees = new ArrayList<>();

    public data() {
    }

    public data(ArrayList<employee> employees) {
        this.employees = employees;
    }

    public ArrayList<employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<employee> employees) {
        this.employees = employees;
    }
}
