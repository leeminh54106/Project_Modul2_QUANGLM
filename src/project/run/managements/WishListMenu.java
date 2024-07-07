package project.run.managements;

import project.ra.entity.Product;
import project.ra.entity.Users;
import project.ra.entity.WishList;
import project.ra.feature.IWhishList;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.ProductImpl;
import project.ra.feature.impl.WhishListImpl;
import project.ra.utils.Color;
import project.run.Main;

import java.util.List;
import java.util.Scanner;

public class WishListMenu {
    public static final IWhishList wishlistFeature = new WhishListImpl();

    public static void wishListMenu(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔════════════════════════════ WISHLIST  ═══════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Lấy ra danh sách yêu thích                                " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Thêm mới 1 sản phẩm vào danh sách yêu thích               " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Xóa sản phẩm ra khỏi danh sách yêu thích                  " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Thoát                                                     " + rowColor);
            System.out.println(bottomColor);
            System.out.println(Color.PURPLE + "Lựa chọn của bạn : " + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    showWishList();
                    break;
                case 2:
                    addWishList(sc);
                    break;
                case 3:
                    deleteWishList(sc);
                    break;
                case 4:
                    quit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 4!");
            }
        } while (quit);

    }

    //dùng user đăng nhập để sét
    static Users user = Main.userLogin;

    // xóa 1 sản phẩm trong danh sách yêu thích
    private static void deleteWishList(Scanner sc) {
        System.out.println("Nhập mã trong danh sách yêu thích muốn xóa");
        int number = FeatureAll.inputNumber(sc);
        int indexDelete = -1;

        for (WishList w : WhishListImpl.wishLists) {
            if (w.getWhishListId() == number && w.getId().getId() == user.getId()) {
                indexDelete = w.getWhishListId();
            }
        }
        wishlistFeature.delete(indexDelete);
    }

    //Thêm sản phẩm vào danh sách yêu thích
    private static void addWishList(Scanner sc) {
        if (ProductImpl.productList.isEmpty()) {
            System.err.println("Danh sách sản phẩm trống!");
            return;
        }
        for (Product pro : ProductImpl.productList) {
            System.out.println(Color.CYAN + "+------------------------+----------------------------------+---------------------------+---------------------+");
            System.out.printf("| Mã sản phẩm: %-10d| Tên sản phẩm %-20s| Giá:  %-20.2f| Số lượng: %-10d|\n", pro.getProductId(), pro.getProductName(), pro.getPrice(), pro.getQuantity());
        }
        System.out.println("+------------------------+----------------------------------+---------------------------+---------------------+" + Color.RESET);
        Product adProduct = null;
        boolean isExist = false;
        System.out.println("Nhập mã sản phẩm:");
        int number = FeatureAll.inputNumber(sc);
        for (Product pro : ProductImpl.productList) {
            if (pro.getProductId() == number) {
                adProduct = pro;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.err.println("Mã sản phẩm không đúng!");
        } else {
            WishList wishList = null;
            for (WishList w : wishlistFeature.findAll()) {

                if (w.getProductId().getProductId() == adProduct.getProductId() && w.getId().getId() == user.getId()) {
                    wishList = w;
                    break;
                }
            }
            if (wishList == null) {
                wishList = new WishList();
                wishList.inputWishList(sc, adProduct, user);
                wishlistFeature.addOrUpdate(wishList);
                System.out.println(Color.GREEN + "Thêm mới thành công!" + Color.RESET);
            } else {
                wishlistFeature.addOrUpdate(wishList);
            }
        }
    }

    //hiển thị danh sách yêu thích
    private static void showWishList() {
        if (user == null) {
            System.err.println("Cần đăng nhập!");
            return;
        }
        List<WishList> wishLists = wishlistFeature.findAll().stream().filter(item -> item.getId().getId() == user.getId()).toList();
        if (wishLists.isEmpty()) {
            System.err.println("Danh sách yêu thích trống!");
            return;
        }
        for (WishList w : wishlistFeature.findAll()) {
            if (w.getId().getId() == user.getId()) {
                w.displayWishList();
            }
        }
        System.out.println("+--------------+--------------------------------------------+" + Color.RESET);
        System.out.println();
    }
}
