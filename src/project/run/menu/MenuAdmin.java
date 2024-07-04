package project.run.menu;

import project.ra.entity.Users;
import project.ra.feature.IUserFeature;
import project.ra.feature.impl.CategoryImpl;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.UserImpl;
import project.ra.utils.Color;
import project.run.managements.CategoryManagement;
import project.run.managements.ProductManagement;

import java.util.Scanner;

public class MenuAdmin {
    public static final IUserFeature userFeature = new UserImpl();

    public void menuAdmin(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔═══════════════════════════ MENU ADMIN  ══════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Quản lý danh mục                                          " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Quản lý sản phẩm                                          " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Tìm kiếm người dùng theo tên                              " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Lấy về danh sách quyền                                    " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Lấy ra danh sách người dùng                               " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     7. Đăng xuất                                                 " + rowColor);
            System.out.println(bottomColor);
            System.out.println(Color.PURPLE + "Lựa chọn của bạn : " + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    CategoryManagement.categoryMenu(sc);
                    break;
                case 2:
                    ProductManagement.productMenu(sc);
                    break;
                case 3:
                    searchUserByName(sc);
                    break;
                case 4:
                    showRole();
                    break;
                case 5:
                    showUserList();
                    break;
                case 6:

                    break;
                case 7:
                    quit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 7!");
            }
        } while (quit);

    }



    private void showUserList() {
        if(UserImpl.users.isEmpty()){
            System.err.println("Danh sách người dùng trống");
            return;
        }
        for(Users u : UserImpl.users){
            u.displayUser();
        }
        System.out.println(Color.CYAN + "+------------------------------------+---------------------------------------------+-------------------------------+-----------------------+"+Color.RESET);
    }

    private void showRole() {
        System.out.println("ROLE_ADMIN, ROLE_MODERATOR, ROLE_USER");
    }


    private void searchUserByName(Scanner sc) {
        if (UserImpl.users.isEmpty()) {
            System.err.println("Danh mục không tồn tại người dùng!");
            return;
        }
        System.out.println("Nhập tên người dùng:");
        String userName = sc.nextLine();
        boolean found = false;
        for (Users u : UserImpl.users) {
            if (u.getFullName().toLowerCase().contains(userName.toLowerCase())) {
                u.displayUser();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy người dùng " + userName);
        }
    }
}

