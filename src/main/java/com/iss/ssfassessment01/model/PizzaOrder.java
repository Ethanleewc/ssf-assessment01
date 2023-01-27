package com.iss.ssfassessment01.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PizzaOrder implements Serializable {

    @NotNull(message = "Please select a Pizza")
    private String pizza;

    @NotNull(message = "Please select the size of your Pizza")
    private String size;

    @Size(min = 1, max = 10, message = "Minimum order of 1 and maximum order of 10")
    private int quantity;

    public PizzaOrder() {
    }

    public PizzaOrder(String pizza, String size, int quantity) {
        this.pizza = pizza;
        this.size = size;
        this.quantity = quantity;
    }

    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
  
    public JsonObject toJSON(){
        return Json.createObjectBuilder()
        .add("pizza", this.getPizza())
        .add("size", this.getSize())
        .add("quantity", this.getQuantity())
        .build();
    }
    
}
