package project.run.managements;

import project.ra.entity.Product;
import project.ra.entity.ShoppingCart;
import project.ra.entity.Users;
import project.ra.feature.IShoppingCart;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.ProductImpl;
import project.ra.feature.impl.ShoppingCartImpl;
import project.ra.utils.Color;
import project.ra.utils.IOFile;
import project.run.Main;

import java.util.Scanner;

public class CartManagement {
    public static final IShoppingCart cartFeature = new ShoppingCartImpl();

    public static void cartMenu(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔═════════════════════════ SHOPPING CART ══════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Danh sách sản phẩm trong giỏ hàng                         " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Thêm mới sản phẩm vào giỏ hàng                            " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Xóa 1 sản phẩm trong giỏ hàng                             " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Xóa toàn bộ sản phẩm trong giỏ hàng                       " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Đặt hàng                                                  " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     6. Thoát                                                     " + rowColor);
            System.out.println(bottomColor);
            System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    showShoppingCart();
                    break;
                case 2:
                    addProductToCart(sc);
                    break;
                case 3:
                    deleteOneProduct(sc);
                    break;
                case 4:
                    deleteCart(sc);
                    break;
                case 5:

                    break;
                case 6:
                    quit = false;
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 6, vui lòng nhập lại!");
            }

        } while (quit);
    }


    private static void deleteCart(Scanner sc) {
        //dùng user đăng nhập để sét
        Users user = Main.userLogin;

        ShoppingCartImpl.shoppingCartList = cartFeature.findAll().stream().filter(item -> item.getUsers().getId() != user.getId()).toList();
        IOFile.writeToFile(IOFile.PATH_SHOPPINGCART, ShoppingCartImpl.shoppingCartList);
    }

    private static void deleteOneProduct(Scanner sc) {
        //dùng user đăng nhập để sét
        Users user = Main.userLogin;
        System.out.println("Nhập vào mã sản phẩm muốn xóa:");
        int number = FeatureAll.inputNumber(sc);
        boolean isExist = false;
        for (ShoppingCart shop : cartFeature.findAll()) {
            if (shop.getShoppingCartId() == number && shop.getUsers().getId() == user.getId()) {
                cartFeature.findAll().remove(shop);
                isExist = true;
                break;
            }
        }
        if(isExist){
            System.out.println("xóa sản phẩm thành công!");
        }else {
            System.err.println("Mã sản phẩm không có trong giỏ hàng!");
        }
        IOFile.writeToFile(IOFile.PATH_SHOPPINGCART, ShoppingCartImpl.shoppingCartList);
    }

    private static void addProductToCart(Scanner sc) {
        for (Product pro : ProductImpl.productList) {
            System.out.println(Color.CYAN + "+------------------------+----------------------------------+---------------------------+---------------------+");
            System.out.printf("| Mã sản phẩm: %-10d| Tên sản phẩm %-20s| Giá:  %-20.2f| Số lượng: %-10d|\n", pro.getProductId(), pro.getProductName(), pro.getPrice(), pro.getQuantity());
        }
        System.out.println("+------------------------+----------------------------------+---------------------------+---------------------+" + Color.RESET);
        //dùng user đăng nhập để sét
        Users user = Main.userLogin;
        Product adProduct = null;
        System.out.println("Nhập mã sản phẩm:");
        int number = FeatureAll.inputNumber(sc);

        boolean isExist = false;
        for (Product pro : ProductImpl.productList) {
            if (pro.getProductId() == number) {
                adProduct = pro;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.err.println("Mã sản phẩm không tồn tại!");
        } else {
            ShoppingCart cartItem = null;
            for (ShoppingCart shop : cartFeature.findAll()) {
                if (shop.getProduct().getProductId() == adProduct.getProductId() && shop.getUsers().getId() == user.getId()) {
                    cartItem = shop;
                }
            }
            if (cartItem == null) {
                cartItem = new ShoppingCart();
                cartItem.inputShoppingCart(sc, user, adProduct);
                ShoppingCartImpl.shoppingCartList.add(cartItem);
            } else {
                System.out.println("Nhập vào số lượng bạn muốn thêm:");
                int number2 = FeatureAll.inputNumber(sc);
                if (cartItem.getOrderQuantity() + number2 <= adProduct.getQuantity()) {
                    cartItem.setOrderQuantity(cartItem.getOrderQuantity() + number2);
                    System.out.println(Color.GREEN+"Thêm số lượng thành công!"+Color.RESET);
                } else {
                    System.err.println("Vượt quá số lượng trong kho!");
                }
            }
        }
        IOFile.writeToFile(IOFile.PATH_SHOPPINGCART, ShoppingCartImpl.shoppingCartList);
    }

    private static void showShoppingCart() {

        Users user = Main.userLogin;
        if (user == null) {
            System.err.println("Vui lòng đăng nhập để xem giỏ hàng!");
            return;
        }
        if (cartFeature.findAll().stream().filter(item -> item.getUsers().getId() == user.getId()).count() <= 0) {
            System.err.println("Giỏ hàng trống!");
            return;
        }
        for (ShoppingCart shop : cartFeature.findAll()) {
            if (shop.getUsers().getId() == user.getId()) {
                shop.displayShoppingCart();
            }

        }
        System.out.println("+-----------------------+-------------------------------+---------------------+"+Color.RESET);
        System.out.println();

    }

}

