package project.run.managements;

import project.ra.entity.Address;
import project.ra.entity.Users;
import project.ra.feature.IAddress;
import project.ra.feature.impl.AddressImpl;
import project.ra.feature.impl.FeatureAll;
import project.ra.utils.Color;
import project.ra.utils.IOFile;
import project.run.Main;

import java.util.*;

public class AddressManagement {
    public static IAddress addressFeature = new AddressImpl();

    public static void addressMenu(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔════════════════════════════ ADDRESS ═════════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Thêm mới địa chỉ                                          " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Lấy địa chỉ người dùng theo addressId                     " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Lấy ra danh sách địa chỉ của người dùng                   " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Xóa 1 địa chỉ theo mã địa chỉ                             " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Thoát                                                     " + rowColor);
            System.out.println(bottomColor);
            System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    addAddress(sc);
                    break;
                case 2:
                    showAddressById(sc);
                    break;
                case 3:
                    showAllAddress();
                    break;
                case 4:
                    deleteAddress(sc);
                    break;
                case 5:
                    quit = false;
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 5, vui lòng nhập lại!");
            }

        } while (quit);
    }
    //dùng user đăng nhập để sét
    static Users user = Main.userLogin;

    //xóa 1 địa chỉ
    private static void deleteAddress(Scanner sc) {

        System.out.println("Nhập mã địa chỉ muốn xóa:");
        int number = FeatureAll.inputNumber(sc);
        addressFeature.findAll().removeIf(item -> item.getAddressId() == number && item.getUsers().getId() == user.getId());
//        for (Address ad : addressFeature.findAll()) {
//            if (ad.getAddressId() == number && ad.getUsers().getId() == user.getId()) {
//                addressFeature.findAll().remove(ad);
//                break;
//            }
//        }
        System.out.println(Color.GREEN + "Xóa thành công!" + Color.RESET);
        IOFile.writeToFile(IOFile.PATH_ADDRESS, AddressImpl.addressList);
    }
    //lấy tất cả danh sách
    private static void showAllAddress() {
        if (user == null) {
            System.err.println("Cần đăng nhập!");
            return;
        }
        addressFeature.findAll().stream().filter(item -> item.getUsers().getId() == user.getId()).forEach(Address::displayAddress);
//        for (Address ad : addressFeature.findAll()) {
//            if (ad.getUsers().getId() == user.getId()) {
//                ad.displayAddress();
//            }
//        }
        System.out.println("+------------------------+------------------------------------------+---------------------------------------------------------+" + Color.RESET);
        System.out.println();
    }
    //hiển thị địa chỉ theo mã địa chỉ
    private static void showAddressById(Scanner sc) {
        System.out.println("Nhập mã địa chỉ:");
        int number = FeatureAll.inputNumber(sc);
        boolean isExist = false;
        for (Address ad : addressFeature.findAll()) {
            if (ad.getAddressId() == number && ad.getUsers().getId() == Main.userLogin.getId()) {
                ad.displayAddress();
                isExist = true;
            }
        }
        System.out.println("+------------------------+------------------------------------------+---------------------------------------------------------+" + Color.RESET);
        System.out.println();
        if (!isExist) {
            System.err.println("Mã địa chỉ không đúng!");
        }
    }
    //Thêm mới địa chỉ
    private static void addAddress(Scanner sc) {

        System.out.println("Nhập số lượng địa chỉ muốn thêm:");
        int number = FeatureAll.inputNumber(sc);
        for (int i = 0; i < number; i++) {
            Address newAddress = new Address();
            newAddress.inputDataAddress(sc, user);
            addressFeature.addOrUpdate(newAddress);
        }
        System.out.println(Color.GREEN + "Thêm mới địa chỉ thành công!" + Color.RESET);
    }


}
