package project.ra.entity;

import project.ra.feature.impl.CategoryImpl;
import project.ra.utils.Color;

import java.io.Serializable;
import java.util.Scanner;

public class Category implements Serializable {
    private int categoryId;
    private String categoryName;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputCategory(Scanner sc) {

        this.categoryId = autoCategoryId();
        this.categoryName = inputCategoryName(sc);
        this.description = inputDescription(sc);
        this.status = inputStatus(sc);
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Nhập mô tả:");
        return sc.nextLine();
    }

    public boolean inputStatus(Scanner sc) {
        System.out.println("Nhập trạng thái danh mục:");
        do {
            String status = sc.nextLine().toLowerCase();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Chỉ nhận true or false");
            }
        } while (true);
    }


    public String inputCategoryName(Scanner sc) {
        System.out.println("Nhập tên danh mục:");
        do {
            String cateName = sc.nextLine();
            if (cateName.trim().isEmpty()) {
                System.err.println("Không được để trống!");
            } else {
                boolean isExist = false;
                for (Category ca : CategoryImpl.categoryList) {
                    if (ca.getCategoryName().equals(cateName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại,vui lòng nhập lại!");
                } else {
                    return cateName;
                }
            }
        } while (true);
    }

    public int autoCategoryId() {
        int max = 0;
        for (Category c : CategoryImpl.categoryList) {
            if (c.getCategoryId() > max) {
                max = c.getCategoryId();
            }
        }
        return max + 1;
    }

    public void displayCategory() {
        String borderColor = Color.BLUE;
        String rowColor = borderColor + "|";
        String colColor = borderColor + "+------------------------+------------------------------+--------------------------------------+---------------------------------+";
        System.out.println(colColor);
        System.out.printf(rowColor + " Mã danh mục: %-10d| Tên danh mục: %-15s| Mô tả: %-30s| Trạng thái: %-20s|\n",
                this.categoryId, this.categoryName, this.description, this.status ? "Active" : "Inactive");

    }
}
