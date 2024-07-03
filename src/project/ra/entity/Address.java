package project.ra.entity;

import project.ra.feature.impl.AddressImpl;

import java.util.Scanner;

public class Address {
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
//        private int adressId;
//        private Users users;
//        private String fullAddress;
//        private String phone;
//        private String receiveName;
        this.addressId = autoAddressId();
        this.users = users;
        this.fullAddress = inputFullAddress(sc);
    }

    public String inputFullAddress(Scanner sc) {

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
}
