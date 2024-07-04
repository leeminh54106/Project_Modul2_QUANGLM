package project.ra.entity;

import project.ra.feature.impl.AddressImpl;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Address implements Serializable {
    private int addressId;
    private Users users;
    private String fullAddress;
    private String phone;
    private String receiveName;

    public Address() {
    }

    public Address(int addressId, String fullAddress, String phone, String receiveName, Users users) {
        this.addressId = addressId;
        this.fullAddress = fullAddress;
        this.phone = phone;
        this.receiveName = receiveName;
        this.users = users;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void inputDataAddress(Scanner sc,Users users) {
        this.addressId = autoAddressId();
        this.users = users;
        this.fullAddress = inputFullAddress(sc);
        this.phone = inputPhoneAddress(sc);
        this.receiveName = inputReceiveName(sc);
    }

    private String inputReceiveName(Scanner sc) {
        System.out.println("Nhập tên người nhận:");
        return sc.nextLine();
    }

    private String inputPhoneAddress(Scanner sc) {
        String regex = "(0)\\d{9}";
        System.out.println("Nhập số điện thoại:");
       do {
           String input = sc.nextLine();
           if(Pattern.matches(regex,input)){
               return input;
           }else {
               System.err.println("Số điện thoại phải có 10 ký tự số, bắt đầu từ 0!");
           }
       }while (true);
    }

    public String inputFullAddress(Scanner sc) {
        System.out.println("Nhập địa chỉ đầy đủ:");
        return sc.nextLine();
    }


    public int autoAddressId() {
        int max = 0;
        for (Address ad : AddressImpl.addressList) {
            if (ad.getAddressId() > max) {
                max = ad.getAddressId();
            }
        }
        return max + 1;
    }
    public void displayAddress() {
        System.out.println("+------------------------+------------------------------+--------------------------------------+---------------------------------+");
        System.out.printf("| Mã địa chỉ: %-5d| Tài khoản: %-10s| Địa chỉ đầy đủ: %-10s|\n",
               this.addressId,this.users.getUserName(),this.fullAddress);
        System.out.printf("|                  | Tên người nhận: %-10s| Số điện thoại: %-5s|\n",
                this.receiveName,this.phone);
        System.out.println("+------------------------+------------------------------+--------------------------------------+---------------------------------+");
    }
}
