package com.ourcuet.mehedi.halthyfoodmenu;

public class Item {
    String foodName;
    double quantity;
    String unitName;
    int day;

    public Item(String foodName, double quantity, String unitName, int day) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.unitName = unitName;
        this.day= day;
    }

    public int getDay() {
        return day;
}

    public String getFoodName() {
        return foodName;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnitName() {
        return unitName;
    }
}
