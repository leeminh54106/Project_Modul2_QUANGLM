package project.ra.entity;

import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.ShoppingCartImpl;

import java.io.Serializable;
import java.util.Scanner;

public class ShoppingCart implements Serializable {
    private int shoppingCartId;
    private Product product;
    private Users users;
    private int orderQuantity;

    public ShoppingCart() {
    }

    public ShoppingCart(int orderQuantity, Product product, int shoppingCartId, Users users) {
        this.orderQuantity = orderQuantity;
        this.product = product;
        this.shoppingCartId = shoppingCartId;
        this.users = users;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void inputShoppingCart(Scanner sc, Users users, Product product) {
//        private int ShoppingCartId;
//        private Product product;
//        private Users users;
//        private int orderQuantity;
        this.shoppingCartId = autoShoppingCartId();
        this.product = product;
        this.users = users;
        this.orderQuantity = inputOrderQuantity(sc,product);

    }

    public int inputOrderQuantity(Scanner sc, Product product) {
        System.out.println("Nhập số lượng:");
        do {
            int number = FeatureAll.inputNumber(sc);
            if (number <= 0) {
                System.err.println("Số lượng phải > 0,vui lòng nhâp lại!");
            } else {
                if(number <= product.getQuantity()){
                    return number;
                }
            }
        } while (true);
    }

    public int autoShoppingCartId() {
        int max = 0;
        for (ShoppingCart shop : ShoppingCartImpl.shoppingCartList) {
            if (shop.shoppingCartId > max) {
                max = shop.shoppingCartId;
            }
        }
        return max + 1;
    }
    public void displayShoppingCart() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("|mã giỏ hàng: %-10d, sản phẩm: %-20s, số lượng: %-10d|\n",
                this.shoppingCartId,this.product.getProductName(),this.orderQuantity);
        System.out.println("---------------------------------------------------------------------------------");
    }
}
