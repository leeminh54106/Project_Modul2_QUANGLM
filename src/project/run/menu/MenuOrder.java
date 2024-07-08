package project.run.menu;

import project.ra.constants.OrderStatus;
import project.ra.entity.Order;
import project.ra.entity.Users;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.OrderImpl;
import project.ra.feature.impl.UserImpl;
import project.ra.utils.Color;
import project.run.Main;
import project.run.managements.AddressManagement;
import project.run.managements.CartManagement;

import java.util.Scanner;

public class MenuOrder {


    public static void orderMenu(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔════════════════════════════  ORDER  ═════════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. lấy ra danh sách lịch sử mua hàng                         " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Lấy ra danh sách lịch sử đơn hàng theo trạng thái đơn hàng" + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Đăng xuất                                                 " + rowColor);
            System.out.println(bottomColor);

            System.out.println(Color.PURPLE + "Lựa chọn của bạn : " + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    showHistoryBought(sc);
                    break;
                case 2:
                    showStatusBought(sc);
                    break;
                case 3:
                    quit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 3!");
            }
        } while (quit);
    }

    //hiển thị theo trạng thái
    private static void showStatusBought(Scanner sc) {
        System.out.println("Nhập 1 trong các trạng thái sau: ");
        System.out.println("WAITING,CONFIRM,DELIVERY,SUCCESS,CANCEL,DENIED");
        OrderStatus orderStatus;
        do {
            try {
                orderStatus = OrderStatus.valueOf(sc.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Vui nòng nhập đúng định dạng!");
            }

        } while (true);
        boolean isExit = false;
        for (Order o : OrderImpl.orderList) {
            if (o.getStatus().equals(orderStatus)) {
                o.displayOrder();
                isExit = true;
            }
        }
        if (!isExit) {
            System.err.println("Không tìm thấy đơn hàng có trạng thái là " + orderStatus);
        }
    }

    //hiển thị tất cả order đã mua
    private static void showHistoryBought(Scanner sc) {
        for (Order o : OrderImpl.orderList) {
            o.displayOrder();
        }
    }

}

