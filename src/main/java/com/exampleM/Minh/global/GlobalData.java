package com.exampleM.Minh.global;


import java.util.ArrayList;
import java.util.List;

import com.exampleM.Minh.entity.Product;

public class GlobalData {
    //tao bien toan cuc
    public static List<Product> cart;

    static {
        cart = new ArrayList<>();
    }

}
