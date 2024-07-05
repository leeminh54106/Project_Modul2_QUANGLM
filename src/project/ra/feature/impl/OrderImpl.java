package project.ra.feature.impl;

import project.ra.entity.Order;
import project.ra.feature.IOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements IOrder {
    public static List<Order> orderList = new ArrayList<>();
    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public void addOrUpdate(Order order) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public int findIndexById(Integer id) {
        return 0;
    }
}
