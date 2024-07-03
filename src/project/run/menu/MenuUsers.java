package project.run.menu;

import project.ra.feature.impl.FeatureAll;
import project.ra.utils.Color;

import java.util.Scanner;

public class MenuUsers {
    public void menuUsers(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔═══════════════════════════ MENU USER  ═══════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Hiển thị danh sách sản phẩm                               " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Lấy địa chỉ theo mã                                      " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Quản lý giỏ hàng                                          " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Quản lý đơn hàng                                          " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Quản lý danh sách yêu thích                               " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     6. Đăng xuất                                                 " + rowColor);
            System.out.println(bottomColor);

            System.out.println(Color.PURPLE + "Lựa chọn của bạn : " + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    quit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 6!");
            }
        } while (quit);

    }
}
