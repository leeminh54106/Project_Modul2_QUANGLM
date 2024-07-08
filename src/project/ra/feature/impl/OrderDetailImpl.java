package project.ra.feature.impl;

import project.ra.entity.Order;
import project.ra.entity.OrderDetail;
import project.ra.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailImpl {
    public static List<OrderDetail> orderDetailList = new ArrayList<>();

    static {
        int id = 2;
        Order order1 = null;
        for (int i = 0; i < OrderImpl.orderList.size(); i++) {
            if (OrderImpl.orderList.get(i).getOrderId() == id) {
                order1 = OrderImpl.orderList.get(i);
            }
        }
        int idProduct = 2;
        Product product1 = null;
        for (int i = 0; i < ProductImpl.productList.size(); i++) {
            if (ProductImpl.productList.get(i).getProductId() == idProduct) {
                product1 = ProductImpl.productList.get(i);
            }
        }
        int idProduct2 = 1;
        Product product2 = null;
        for (int i = 0; i < ProductImpl.productList.size(); i++) {
            if (ProductImpl.productList.get(i).getProductId() == idProduct2) {
                product2 = ProductImpl.productList.get(i);
            }
        }
        OrderDetail orderDetail = new OrderDetail("tran pinh", order1, product1, 10, 25000F);
        OrderDetail orderDetail2 = new OrderDetail("tran son", order1, product2, 13, 34000F);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);
    }
}
