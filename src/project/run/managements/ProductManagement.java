package project.run.managements;

import project.ra.entity.Product;
import project.ra.feature.IProduct;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.ProductImpl;
import project.ra.utils.Color;

import java.util.Date;
import java.util.Scanner;

public class ProductManagement {
    public static IProduct productFeature = new ProductImpl();

    public static void productMenu(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔════════════════════════════ PRODUCT ═════════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Hiển thị danh sách sản phẩm                               " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Thêm mới sản phẩm                                         " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Cập nhật sản phẩm                                         " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Xóa sản phẩm                                              " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Tìm sản phẩm theo mã sản phẩm                             " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     6. Thoát                                                     " + rowColor);
            System.out.println(bottomColor);

            System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    showAll();
                    break;
                case 2:
                    addProduct(sc);
                    break;
                case 3:
                    updateProduct(sc);
                    break;
                case 4:
                    deleteProduct(sc);
                    break;
                case 5:
                    searchProduct(sc);
                    break;
                case 6:
                    quit = false;
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 6, vui lòng chọn lại!");
            }
        } while (quit);
    }

    private static void searchProduct(Scanner sc) {
      if(productFeature.findAll().isEmpty()){
          System.err.println("Danh mục sản phẩm trống!");
          return;
      }
        System.out.println("Nhập mã sản phẩm:");
        int number = FeatureAll.inputNumber(sc);
        boolean isExist = false;
        for(Product p:productFeature.findAll()){
            if(p.getProductId() == number){
                p.displayProduct();
                isExist = true;
            }
        }
        System.out.println(Color.BLUE +"+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+" + Color.RESET);
        System.out.println();
        if(!isExist){
            System.err.println("Không tìm thấy sản phẩm có mã " +number);
        }
    }

    private static void deleteProduct(Scanner sc) {
        System.out.println("Nhập mã sản phẩm:");
        int number = FeatureAll.inputNumber(sc);
        productFeature.delete(number);
    }

    private static void updateProduct(Scanner sc) {
        System.out.println("Nhập mã sản phẩm:");
        int idUpdate = FeatureAll.inputNumber(sc);
        int indexUpdate = productFeature.findIndexById(idUpdate);
        if (indexUpdate >= 0) {
            Product productUpdate = ProductImpl.productList.get(indexUpdate);
            boolean isExist = true;
            do {
                String borderColor = Color.PURPLE;
                String bottomColor = borderColor + "╚═════════════════════════════════════════════════════════════════╝" + Color.RESET;
                String rowColor = borderColor + "║" + Color.RESET;
                String topColor = borderColor + "╔═════════════════════════════ UPDATE ════════════════════════════╗" + Color.RESET;
                System.out.println(topColor);
                System.out.println(rowColor + "" + borderColor + "     1. Cập nhật lại tên sản phẩm                                " + rowColor);
                System.out.println(rowColor + "" + borderColor + "     2. Cập nhật lại mô tả sản phẩm                              " + rowColor);
                System.out.println(rowColor + "" + borderColor + "     3. Cập nhật lại giá sản phẩm                                " + rowColor);
                System.out.println(rowColor + "" + borderColor + "     4. Thoát                                                    " + rowColor);
                System.out.println(bottomColor);
                System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
                int choice = FeatureAll.inputNumber(sc);
                switch (choice) {
                    case 1:
                        productUpdate.setProductName(productUpdate.inputProductName(sc));
                        break;
                    case 2:
                        productUpdate.setDescription(productUpdate.inputDescription(sc));
                        break;
                    case 3:
                        productUpdate.setPrice(productUpdate.inputPrice(sc));
                        break;
                    case 4:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Lựa chọn từ 1 -> 4, vui lòng nhập lại!");
                }
                productUpdate.setUpdated(new Date());
                productFeature.addOrUpdate(productUpdate);
            } while (isExist);
        } else {
            System.err.println("Không tim thấy mã sản phẩm " + indexUpdate);
        }

    }

    private static void addProduct(Scanner sc) {
        System.out.println("Nhập số lượng sản phẩm:");
        int number = FeatureAll.inputNumber(sc);
        for (int i = 0; i < number; i++) {
            Product newProduct = new Product();
            newProduct.inputProduct(sc);
            productFeature.addOrUpdate(newProduct);
        }
    }

    private static void showAll() {
        if (productFeature.findAll().isEmpty()) {
            System.err.println("Danh mục sản phẩm trống!");
            return;
        }
        for (Product p : productFeature.findAll()) {
            p.displayProduct();
        }
        System.out.println(Color.BLUE +"+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+" + Color.RESET);
        System.out.println();

    }
}
