package project.ra.entity;

import java.util.Scanner;

public class OrderDetail {
    private Order order;
    private Product product;
    private String name;
    private float unitPrice;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(String name, Order order, Product product, int quantity, float unitPrice) {
        this.name = name;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void inputOrderDetail(Scanner sc, Order order, ShoppingCart shoppingCart) {
        this.order = order;
        this.product = shoppingCart.getProduct();
        this.name = shoppingCart.getProduct().getProductName();
        this.unitPrice = shoppingCart.getProduct().getPrice();
        this.quantity = shoppingCart.getOrderQuantity();
    }
    public void displayOrderDetail() {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("Mã đơn hàng %-5d| mã sản phẩm %-5d| Tên sản phẩm: %-20s| Giá: %-10.0f| Số lượng: %-100d|\n",
                this.order.getOrderId(),this.product.getProductId(),this.name,this.unitPrice,this.quantity);
    }
}
