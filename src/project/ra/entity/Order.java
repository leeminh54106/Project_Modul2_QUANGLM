package project.ra.entity;

import project.ra.constants.OrderStatus;
import project.ra.feature.impl.OrderImpl;
import project.ra.utils.Color;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

import static project.ra.constants.OrderStatus.*;

public class Order implements Serializable {
    private int orderId;
    private String serialNumber;
    private Users users;
    private double totalPrice;
    private OrderStatus status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date creatd;
    private Date received;

    public Order() {
    }

    public Order(Date creatd, String note, int orderId, String receiveAddress, Date received, String receiveName, String receivePhone, String serialNumber, OrderStatus status, double totalPrice, Users users) {
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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

    public void inputOrder(Scanner sc, Users users, ShoppingCart shoppingCart) {

        this.orderId = autoOrderId();
        this.serialNumber = autoSerialNumber();
        this.users = users;
        this.status = WAITING;
        this.note = inputNoteOrder(sc);
        this.receiveName = inputReceiveNameOrder(sc);
        this.receiveAddress = inputReceiveAddressOrder(sc);
        this.receivePhone = inputReceivePhone(sc);
        this.creatd = new Date();
    }



    private String inputReceivePhone(Scanner sc) {
        String regex = "(0)\\d{9}";
        System.out.println("Nhập số điện thoại người nhận:");
        do {
            String phone = sc.nextLine();
            if (Pattern.matches(regex, phone)) {
                return phone;
            } else {
                System.err.println("Số điện thoại phải có 10 ký tự, bắt đầu từ số 0.");
            }
        } while (true);
    }

    private String inputReceiveAddressOrder(Scanner sc) {
        System.out.println("Nhập địa chỉ người nhận:");
        do {
            String receiveAddress = sc.nextLine();
            if (receiveAddress.trim().isEmpty()) {
                System.err.println("Địa chỉ không được để trống, vui lòng nhập lại!");
            } else {
                return receiveAddress;
            }
        } while (true);
    }

    private String inputReceiveNameOrder(Scanner sc) {
        System.out.println("Nhập tên người nhận:");
        do {
            String receiveName = sc.nextLine();
            if (receiveName.trim().isEmpty()) {
                System.err.println("Tên không được để trống,vui lòng nhập lại!");
            } else {
                if (receiveName.length() > 6) {
                    return receiveName;
                } else {
                    System.err.println("Tên phải > 6 ký tự,vui lòng nhập lại!");
                }
            }
        } while (true);
    }

    private String inputNoteOrder(Scanner sc) {
        System.out.println("Nhập ghi chú:");
        return sc.nextLine();
    }


    public String autoSerialNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    public int autoOrderId() {
        int max = 0;
        for (Order or : OrderImpl.orderList) {
            if (or.getOrderId() > max) {
                max = or.getOrderId();
            }
        }
        return max + 1;
    }

    public void displayOrder() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(Color.CYAN +"+-------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("|                                                                        %-67s| \n", sdf.format(this.creatd));
        System.out.println("+----------------------------------------+---------------------------------------------+----------------------------------------------------+");
        System.out.printf("|Mã Order: %-30d| Số seri: %-35s| Người dùng: %-39s| \n",
                this.orderId, this.serialNumber, this.users.getUserName());
        System.out.println("+----------------------------------------+---------------------------------------------+----------------------------------------------------+");
        System.out.printf("| Mô tả: %-32s| Tổng giá tiền: %-29.0f| Trạng thái: %-39s| \n",
                this.note, this.totalPrice,
                this.status.equals(WAITING) ? "Đơn hàng mới chờ xác nhận" : this.status.equals(CONFIRM) ? "Đã xác nhận" :
                        this.status.equals(DELIVERY) ? "Đang giao hàng" : this.status.equals(CANCEL) ? "Đã hủy đơn" : "Bị từ chối");
        System.out.println("+----------------------------------------+---------------------------------------------+----------------------------------------------------+" +Color.RESET);
        System.out.println(Color.YELLOW +"                                       <------------------------------------------------->"+Color.RESET);
    }
}
