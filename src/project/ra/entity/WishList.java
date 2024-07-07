package project.ra.entity;

import project.ra.feature.impl.WhishListImpl;
import project.ra.utils.Color;

import java.io.Serializable;
import java.util.Scanner;

public class WishList implements Serializable {
    private int whishListId;
    private Users id;
    private Product productId;

    public WishList() {
    }

    public WishList(Users id, Product productId, int whishListId) {
        this.id = id;
        this.productId = productId;
        this.whishListId = whishListId;
    }

    public Users getId() {
        return id;
    }

    public void setId(Users id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getWhishListId() {
        return whishListId;
    }

    public void setWhishListId(int whishListId) {
        this.whishListId = whishListId;
    }
    //Nhập dữ liệu danh sách yêu thích
    public void inputWishList(Scanner sc, Product productId, Users userId) {
        this.whishListId = autowhishListId();
        this.productId = productId;
        this.id = userId;
    }
    // ID tự tăng
    private int autowhishListId() {
        int max = 0;
        for (WishList w : WhishListImpl.wishLists) {
            if (w.getWhishListId() > max) {
                max = w.getWhishListId();
            }
        }
        return max + 1;
    }
    //Hiển thị danh sách yêu thích
    public void displayWishList() {
        System.out.println(Color.BLUE + "+--------------+--------------------------------------------+");
        System.out.printf("|ID: %-10d| Tên sản phẩm:%-30s| \n",this.whishListId,this.productId.getProductName());
    }
}
