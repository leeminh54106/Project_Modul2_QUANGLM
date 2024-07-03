package project.ra.entity;

import project.ra.constants.RoleName;
import project.ra.feature.impl.UserImpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Users implements Serializable {
    private int id;
    private String userName;
    private String email;
    private String fullName;
    private boolean status;
    private String password;
    private String confirmPassword;
    private String avatar;
    private String phone;
    private String adress;
    private Date created;
    private Date updated;
    private byte delete;
    private RoleName roleName;


    public Users() {
    }

    public Users(String adress, String avatar, String confirmPassword, Date created, byte delete, String email, String fullName, int id, String password, String phone, RoleName roleName, boolean status, Date updated, String userName) {
        this.adress = adress;
        this.avatar = avatar;
        this.confirmPassword = confirmPassword;
        this.created = created;
        this.delete = delete;
        this.email = email;
        this.fullName = fullName;
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.roleName = roleName;
        this.status = status;
        this.updated = updated;
        this.userName = userName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public byte getDelete() {
        return delete;
    }

    public void setDelete(byte delete) {
        this.delete = delete;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    private int id;
//    private String userName;
//    private String email;
//    private String fullName;
//    private byte status;
//    private String password;
//    private String confirmPassword;
//    private String avatar;
//    private long phone;
//    private String adress;
//    private Date created;
//    private Date updated;
//    private byte delete;
//    private RoleName roleName;

    public void inputUser(Scanner sc) {
        this.id = autoId();
        this.userName = inputUserName(sc);
        this.email = inputEmail(sc);
        this.fullName = inputFullName(sc);
        this.status = true;
        this.password = inputPass(sc);
        this.confirmPassword = inputConfirmPass(sc, getPassword());
        this.phone = inputPhone(sc);
        this.adress = inputAdress(sc);
        this.created = new Date();
        this.delete = 1;

    }

    public String inputAdress(Scanner sc) {
        System.out.println("Nhập địa chỉ:");
        do {
            String adress = sc.nextLine();
            if (adress.trim().isEmpty()) {
                System.err.println("Không được để trống!");
            } else {
                return adress;
            }
        } while (true);
    }

    public String inputPhone(Scanner sc) {
        String regex = "(0)\\d{9}";
        System.out.println("Nhập số điện thoại theo dạng VN:");
        do {
            String phone = sc.nextLine().trim();
            if (Pattern.matches(regex, phone)) {
                boolean isExit = false;
                for (Users u : UserImpl.users) {
                    if (u.getPhone().equals(phone)) {
                        isExit = true;
                        break;
                    }
                }
                if (isExit) {
                    System.err.println("Số điện thoại đã tồn tại!");
                } else {
                    return phone;
                }
            } else {
                System.err.println("Số điện thoại không đúng định dạng!");
            }
        } while (true);

    }

    public String inputConfirmPass(Scanner sc, String password) {
        System.out.println("Nhập lại mật khẩu:");
        do {
            String confirmPass = sc.nextLine();

            if (password.equals(confirmPass)) {
                return confirmPass;
            } else {
                System.err.println("Mật khẩu không khớp!");
            }
        } while (true);
    }

    public String inputPass(Scanner sc) {
        System.out.println("Nhập mật khẩu:");
        do {
            String password = sc.nextLine().trim();
            if (password.isEmpty()) {
                System.err.println("Mật khẩu không được để trống!");
            } else if (password.length() < 6) {
                System.err.println("Mật khẩu phải có ít nhất 6 ký tự!");
            } else {
                return password;
            }
        } while (true);
    }

    public String inputFullName(Scanner sc) {
        System.out.println("Nhập họ và tên:");
        return sc.nextLine();
    }

    public String inputEmail(Scanner sc) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        System.out.println("Nhập email:");
        while (true) {
            String email = sc.nextLine().trim();
            if (Pattern.matches(regex, email)) {
                boolean quit = false;
                for (Users u : UserImpl.users) {
                    if (u.getEmail().equals(email)) {
                        quit = true;
                        break;
                    }
                }
                if (quit) {
                    System.err.println("Email đã tồn tại,vui lòng nhập lại!");
                } else {
                    return email;
                }
            } else {
                System.err.println("Email không hợp lệ, vui lòng nhập lại!");
            }
        }
    }

    public String inputUserName(Scanner sc) {
        String regex = "^[a-zA-Z0-9 ]+$";
        System.out.println("Nhập tên tài khoản:");
        do {
            String userName = sc.nextLine();
            if (userName.trim().length() >= 6 && userName.trim().length() <= 100) {
                if (Pattern.matches(regex, userName)) {
                    boolean isExit = false;
                    for (Users u : UserImpl.users) {
                        if (u.getUserName().equals(userName)) {
                            isExit = true;
                            break;
                        }
                    }
                    if (isExit) {
                        System.err.println("Tài khoản đã tồn tại!");
                    } else {
                        return userName;
                    }
                } else {
                    System.err.println("Không được chứa có ký tự đặc biệt!");
                }
            } else {
                System.err.println("Tài khoản tối thiểu 6 ký tự,nhỏ hơn 100 ký tự,vui lòng nhập lại!");
            }
        } while (true);

    }

    public int autoId() {
        int max = 0;
        if (!UserImpl.users.isEmpty()) {
            for (Users u : UserImpl.users) {
                if (u.getId() > max) {
                    max = u.getId();
                }
            }
        }
        return max + 1;
    }

    public void displayUser() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("║ Tên đăng nhập: %-10s║ Email: %-10s║ Tên: %-10s║ trạng thái: %-10s \n",
                this.userName, this.email, this.fullName, this.status ? "Hoạt động" : "Khóa");
        System.out.printf("║ Số điện thoại: %-10d║ Địa chỉ: %-10s║ Date: %-10s\n",
                this.phone,this.adress,sdf.format(this.created));
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }
}

