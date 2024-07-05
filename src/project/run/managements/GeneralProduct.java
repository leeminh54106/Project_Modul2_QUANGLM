package project.run.managements;

import project.ra.entity.Category;
import project.ra.entity.Product;
import project.ra.feature.ICategory;
import project.ra.feature.IProduct;
import project.ra.feature.impl.CategoryImpl;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.ProductImpl;
import project.ra.utils.Color;

import java.util.Comparator;
import java.util.Scanner;


public class GeneralProduct {
    public static final IProduct productFeatuer = new ProductImpl();
    public static final ICategory categoryFeatuer = new CategoryImpl();

    public  void generalProductMenu(Scanner sc) {
        boolean quit = true;
        do {

            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔═════════════════════════ GENERAL PRODUCT ════════════════════════╗" + Color.RESET;
            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Chi tiết thông tin sản phẩm theo id                       " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Danh sách sản phẩm theo danh mục                          " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Danh sách sản phẩm mới                                    " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Tìm kiếm sản phẩm theo tên hoặc mô tả                     " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Danh sách tất cả sản phẩm được bán                        " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     6. Thoát                                                     " + rowColor);
            System.out.println(bottomColor);
            System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    getProductById(sc);
                    break;
                case 2:
                    showProduct(sc);
                    break;
                case 3:
                    newProduct();
                    break;
                case 4:
                    searchProductByNameOrDes(sc);
                    break;
                case 5:
                    showAllProduct(sc);
                    break;
                case 6:
                    quit = false;
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 5, vui lòng nhập lại!");
            }
        } while (quit);
    }

    private void showAllProduct(Scanner sc) {
        if(productFeatuer.findAll().isEmpty()){
            System.err.println("Danh mục sản phẩm trống!");
            return;
        }
        for(Product p : productFeatuer.findAll()){
            p.displayProduct();
        }
        System.out.println("+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+"+Color.RESET);
        System.out.println();
    }

    private void searchProductByNameOrDes(Scanner sc) {
        System.out.println("Nhập tên sản phẩm hoặc mô tả sản phẩm:");
        String input = sc.nextLine();
        if(input.trim().isEmpty()){
            System.err.println("Không được để trống!");
            return;
        }
        boolean isExist = false;
        for(Product p: productFeatuer.findAll()) {
            if(p.getProductName().toLowerCase().contains(input.toLowerCase()) || p.getDescription().toLowerCase().contains(input.toLowerCase())) {
                p.displayProduct();
                isExist = true;
                System.out.println("+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+"+Color.RESET);
                System.out.println();
            }
        }
        if(!isExist) {
            System.err.println("Không tìm thấy sản phẩm " +input);
        }
    }

    private void newProduct() {
        ProductImpl.productList.sort(Comparator.comparing(Product::getCreated));
        for (Product p : ProductImpl.productList) {
            p.displayProduct();
        }
        System.out.println("+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+"+Color.RESET);
        System.out.println();
    }

    private void showProduct(Scanner sc) {
        if (categoryFeatuer.findAll().isEmpty()) {
            System.err.println("Danh mục trống!");
            return;
        }
        for (Category ca : categoryFeatuer.findAll()) {
            System.out.println(Color.CYAN + "+------------------------+---------------------------------------------+");
            System.out.printf("| Mã danh mục: %-10d| Tên danh mục: %-30s|\n",
                    ca.getCategoryId(), ca.getCategoryName());

        }
        System.out.println("+------------------------+---------------------------------------------+" + Color.RESET);
        System.out.println();
        System.out.println("Nhập mã danh mục:");
        int number = FeatureAll.inputNumber(sc);
        for (Product p : productFeatuer.findAll()) {
            if (p.getCategory().getCategoryId() == number) {
                p.displayProduct();
                System.out.println("+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+"+Color.RESET);
                System.out.println();
                return;
            }
        }
        System.err.println("Mã danh mục không đúng!");
    }

    private void getProductById(Scanner sc) {
        System.out.println("Nhập mã sản phẩm:");
        int number = FeatureAll.inputNumber(sc);
        boolean isExist = false;
        for (Product p : productFeatuer.findAll()) {
            if (p.getProductId() == number) {
                p.displayProduct();
                isExist = true;
                System.out.println("+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+"+Color.RESET);
                System.out.println();
                break;
            }
        }
        if (!isExist) {
            System.err.println("Mã sản phẩm không đúng!");
        }
    }

}
