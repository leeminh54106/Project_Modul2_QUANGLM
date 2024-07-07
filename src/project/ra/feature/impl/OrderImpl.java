package project.ra.feature.impl;

import project.ra.entity.Order;
import project.ra.entity.Users;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static project.ra.constants.OrderStatus.WAITING;

public class OrderImpl {
    public static List<Order> orderList = new ArrayList<>();

    static {
        Users user1 = null;
        int id = 2;
        for (int i = 0; i < UserImpl.users.size(); i++) {
            if (UserImpl.users.get(i).getId() == id) {
                user1 = UserImpl.users.get(i);
            }
        }
        Order order = new Order(new Date(), "ship nhanh nhé", 1, "ngõ 96 - hưng yên", new Date(), "transon", "0123456666",
                "abc-123", WAITING, 75000F, user1);
        Order order1 = new Order(new Date(), "ship chậm thôi", 2, "ngõ 69 - quất lâm", new Date(), "tranpinh", "06969696969",
                "abc-3333333", WAITING, 96000F, user1);

        orderList.add(order);
        orderList.add(order1);
    }
}
