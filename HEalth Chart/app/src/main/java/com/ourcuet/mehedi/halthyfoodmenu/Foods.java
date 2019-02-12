package com.ourcuet.mehedi.halthyfoodmenu;

class Foods {
        String name, unit;
        double Calorie, Price, Qty, rat, x;

    public Foods(String name, double calorie, double price, double qty, String unit) {
        this.name = name;
        this.unit = unit;
        Calorie = calorie;
        Price = price;
        Qty = qty;

        x = calorie / price;
    }

    public String getName() {
        return name;
    }
}
