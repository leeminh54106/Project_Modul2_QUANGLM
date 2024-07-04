package project.run.managements;

import project.ra.entity.Address;
import project.ra.entity.Users;
import project.ra.feature.IAddress;
import project.ra.feature.impl.AddressImpl;
import project.ra.feature.impl.FeatureAll;
import project.ra.utils.Color;
import project.ra.utils.IOFile;
import project.run.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressManagement {
    public static IAddress addressFeature = new AddressImpl();
    public static void addressMenu(Scanner sc){
        boolean quit = false;
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
                    quit = true;
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 5, vui lòng nhập lại!");
            }

        }while (quit);
    }

    private static void deleteAddress(Scanner sc) {
        //dùng user đăng nhập để sét
        Users user = Main.userLogin;
        List<Integer> numberId = new ArrayList<>();
        for(Address ad:addressFeature.findAll()){
            if(ad.getAddressId() == user.getId()){
                numberId.add(ad.getAddressId());
            }
        }
        System.out.println("Nhập mã địa chỉ muốn xóa:");
        int number = FeatureAll.inputNumber(sc);
       for(int i = 0; i<numberId.size(); i++){
           if(numberId.get(i) == number){
               AddressImpl.addressList.remove(numberId.get(i));
               System.out.println("Đã xóa thành công!");
           }
       }
        IOFile.writeToFile(IOFile.PATH_ADDRESS,AddressImpl.addressList);
    }

    private static void showAllAddress() {
        //dùng user đăng nhập để sét
        Users user = Main.userLogin;
        if(user == null){
            System.err.println("Không có địa chỉ nào cả!");
            return;
        }
        for(Address ad:addressFeature.findAll()){
            if(ad.getUsers().getId() == user.getId()){
                ad.displayAddress();
            }
        }
        System.out.println("+------------------------+------------------------------------------+---------------------------------------------------------+" +Color.RESET);
        System.out.println();
    }

    private static void showAddressById(Scanner sc) {
        System.out.println("Nhập mã địa chỉ:");
        int number = FeatureAll.inputNumber(sc);
        boolean isExist = false;
        for(Address ad:addressFeature.findAll()){
            if(ad.getAddressId() == number){
                ad.displayAddress();
                isExist = true;
            }
        }
        if(!isExist){
            System.err.println("Mã địa chỉ không đúng!");
        }
    }

    private static void addAddress(Scanner sc) {
        //dùng user đăng nhập để sét
        Users user = Main.userLogin;
        System.out.println("Nhập số lượng địa chỉ muốn thêm:");
        int number = FeatureAll.inputNumber(sc);
        for (int i = 0; i < number; i++) {
            Address newAddress = new Address();
            newAddress.inputDataAddress(sc,user);
            addressFeature.addOrUpdate(newAddress);
        }
    }



}
