package project.ra.entity;

import project.ra.constants.OderStatus;
import project.ra.feature.impl.OrderImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Order implements Serializable {
    private int orderId;
    private String serialNumber;
    private Users users;
    private double totalPrice;
    private OderStatus status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date creatd;
    private Date received;

    public Order() {
    }

    public Order(Date creatd, String note, int orderId, String receiveAddress, Date received, String receiveName, String receivePhone, String serialNumber, OderStatus status, double totalPrice, Users users) {
        this.creatd = creatd;
        this.note = note;
        this.orderId = orderId;
        this.receiveAddress = receiveAddress;
        this.received = received;
        this.receiveName = receiveName;
        this.receivePhone = receivePhone;
        this.serialNumber = serialNumber;
        this.status = status;
        this.totalPrice = totalPrice;
        this.users = users;
    }

    public Date getCreatd() {
        return creatd;
    }

    public void setCreatd(Date creatd) {
        this.creatd = creatd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public OderStatus getStatus() {
        return status;
    }

    public void setStatus(OderStatus status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void inputOrder(Scanner sc,Users users,ShoppingCart shoppingCart){
//        private int orderId;
//        private int serialNumber;
//        private Users users;
//        private double totalPrice;
//        private OderStatus status;
//        private String note;
//        private String receiveName;
//        private String receiveAddress;
//        private String receivePhone;
//        private Date creatd;
//        private Date received;
        this.orderId = autoOrderId();
        this.serialNumber = autoSerialNumber();
        this.users = users;
        this.totalPrice =
    }



    public String autoSerialNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    public int autoOrderId() {
        int max = 0;
        for(Order or: OrderImpl.orderList){
            if(or.getOrderId() > max){
                max = or.getOrderId();
            }
        }
        return max + 1;
    }
}
