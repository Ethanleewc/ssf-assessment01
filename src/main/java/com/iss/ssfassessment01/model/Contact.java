package com.iss.ssfassessment01.model;

import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Contact implements Serializable{

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, message = "minimun 3 characters")
    private String name;

    @NotNull(message = "Address cannot be null")
    private String address;

    @NotNull(message = "Phone number cannot be null")
    @Size(min = 8, max = 8, message = "Must be 8 digits")
    private String phone;

    private boolean rush;

    private String comments;

    private String orderid;

    private double total;

    private int pizzacost;

    public Contact() {
        this.orderid = generateId(8);
    }
    
    public Contact(String name, String address, String phone, boolean rush, String comments) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rush = rush;
        this.comments = comments;
        this.orderid = generateId(8);
    }
    
    public Contact(String name, String address, String phone, boolean rush, String comments, String orderid) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rush = rush;
        this.comments = comments;
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = generateId(8);
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = calculateOrderCost(30, 30, 30, 22, 25, "", false);
    }
    public int getPizzacost() {
        return pizzacost;
    }
    public void setPizzacost(int pizzacost) {
        this.pizzacost = pizzacost;
    }

    //Generate unique id for customer
    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numChars);
    }

        //Calculate Order Cost
        public double calculateOrderCost(int bella, int marinara, int spianatacalabrese, int margherita, int trioformaggio, String size, boolean isRushOrder) {
            double cost = 0;
            double sizeMultiplier = 1;
            if (size.equalsIgnoreCase("sm")) {
                sizeMultiplier = 1;
            } else if (size.equalsIgnoreCase("md")) {
                sizeMultiplier = 1.2;
            } else if (size.equalsIgnoreCase("lg")) {
                sizeMultiplier = 1.5;
            }
            cost = (bella + marinara + spianatacalabrese + margherita + trioformaggio) * sizeMultiplier;
            if(isRushOrder)
            cost += 2;
            return cost;
        }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
        .add("orderid", this.getOrderid())
        .add("name", this.getName())
        .add("address", this.getAddress())
        .add("phone", this.getPhone())
        .add("rush", this.isRush())
        .add("comments", this.getComments())
        .build();
    }
}
