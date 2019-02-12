package com.ourcuet.mehedi.halthyfoodmenu;

import java.io.Serializable;

public class FoodsItems implements Serializable {
    String fname,quantity,funit;

    public FoodsItems(String fname, String quantity, String funit) {
        this.fname = fname;
        this.quantity = quantity;
        this.funit = funit;
    }

    public String getFname() {
        return fname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getFunit() {
        return funit;
    }
}
