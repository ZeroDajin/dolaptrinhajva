package com.exampleM.Minh.global;


import java.util.ArrayList;
import java.util.List;

import com.exampleM.Minh.entity.Order;
public class GlobalData {
    //tao bien toan cuc
    public static List<Order> cart;

    static {
        cart = new ArrayList<>();
    }

}
