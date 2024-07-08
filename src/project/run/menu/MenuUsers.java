package project.run.menu;

import project.ra.constants.RoleName;
import project.ra.entity.Users;
import project.ra.feature.IUserFeature;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.UserImpl;
import project.ra.utils.Color;
import project.run.Main;
import project.run.managements.AddressManagement;
import project.run.managements.CartManagement;
import project.run.managements.WishListMenu;

import java.util.Date;
import java.util.Scanner;

public class MenuUsers {
    public static IUserFeature userFeature = new UserImpl();

    public void menuUsers(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔═══════════════════════════ MENU USER  ═══════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Cập nhật thông tin người dùng                             " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Thông tin tài khoản người dùng                            " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Menu address                                              " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Cart Menu                                                 " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. WishList                                                  " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     6. Đăng xuất                                                 " + rowColor);
            System.out.println(bottomColor);

            System.out.println(Color.PURPLE + "Lựa chọn của bạn : " + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    updateUser(sc);
                    break;
                case 2:
                    showInformation();
                    break;
                case 3:
                    AddressManagement.addressMenu(sc);
                    break;
                case 4:
                    CartManagement.cartMenu(sc);
                    break;
                case 5:
                    WishListMenu.wishListMenu(sc);
                    break;
                case 6:
                    System.out.println(Color.GREEN + "Đăng xuất thành công!" + Color.RESET);
                    quit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 6!");
            }
        } while (quit);

    }
    //dùng user đăng nhập để sét
   static Users user = Main.userLogin;

    //hiển thị thông tin người dùng
    private void showInformation() {

        user.displayUser();
        System.out.println(Color.CYAN + "+------------------------------------+---------------------------------------------+-------------------------------+-----------------------+" + Color.RESET);
    }

    //cập nhật thông tin người dùng
    private void updateUser(Scanner sc) {
        boolean isExit = true;
        Users userUpdate = Main.userLogin;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚═════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔═════════════════════════════ UPDATE ════════════════════════════╗" + Color.RESET;
            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Cập nhật Họ và tên                                       " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Cập nhật email                                           " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Cập nhật mật khẩu                                        " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Cập nhật địa chỉ                                         " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Thoát                                                    " + rowColor);
            System.out.println(bottomColor);
            System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    userUpdate.setFullName(userUpdate.inputFullName(sc));
                    System.out.println(Color.GREEN + "Cập nhật thành công!" + Color.RESET);
                    break;
                case 2:
                    userUpdate.setEmail(userUpdate.inputEmail(sc));
                    System.out.println(Color.GREEN + "Cập nhật thành công!" + Color.RESET);
                    break;
                case 3:
                    userUpdate.setPassword(userUpdate.inputPass(sc));
                    System.out.println(Color.GREEN + "Cập nhật thành công!" + Color.RESET);
                    break;
                case 4:
                    userUpdate.setAdress(userUpdate.inputAdress(sc));
                    System.out.println(Color.GREEN + "Cập nhật thành công!" + Color.RESET);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 5, vui lòng nhập lại!");
            }
            userFeature.addOrUpdate(userUpdate);
        } while (isExit);

    }
}
