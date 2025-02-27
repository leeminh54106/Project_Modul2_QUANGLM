package project.run;

import project.ra.constants.RoleName;
import project.ra.entity.Users;
import project.ra.feature.IUserFeature;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.UserImpl;
import project.ra.utils.Color;
import project.ra.utils.IOFile;
import project.run.managements.GeneralProduct;
import project.run.menu.MenuAdmin;
import project.run.menu.MenuModerator;
import project.run.menu.MenuOrder;
import project.run.menu.MenuUsers;

import java.util.Scanner;

public class Main {
    public static GeneralProduct generalPro = new GeneralProduct();
    public static Users userLogin = null;

    static IUserFeature userFeature = new UserImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {

            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔══════════════════════════════ MENU ══════════════════════════════╗" + Color.RESET;
            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Đăng ký                                                   " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Đăng nhập                                                 " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Thông tin về sản phẩm                                     " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Quên mật khẩu                                             " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Order                                                     " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     6. Thoát                                                     " + rowColor);
            System.out.println(bottomColor);
            System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    handleRegister(sc);
                    break;
                case 2:
                    handleLogin(sc);
                    break;
                case 3:
                    generalPro.generalProductMenu(sc);
                    break;
                case 4:
                    forgetPass(sc);
                    break;
                case 5:
                    MenuOrder.orderMenu(sc);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 5, vui lòng nhập lại!");
            }
        } while (true);


    }

    //lấy lại mật khẩu
    public static String forgetPass(Scanner sc) {
        System.out.println("Nhập email để tìm lại mật khẩu:");
        String email = sc.nextLine();
        boolean isExit = false;
        for (Users u : UserImpl.users) {
            if (u.getEmail().equals(email) && !u.getRoleName().equals(RoleName.ROLE_ADMIN)) {
                u.setPassword(u.inputPass(sc));
                u.inputConfirmPass(sc, u.getPassword());
                userFeature.addOrUpdate(u);
                System.out.println(Color.GREEN + "Cập nhật mật khẩu thành công!" + Color.RESET);
                isExit = true;
                break;
            }
        }
        // cập nhât lại IOFile và chuyền vào users
        IOFile.writeToFile(IOFile.PATH_USER, UserImpl.users);
        if (!isExit) {
            System.err.println("email không đúng!");
        }
        return null;
    }

    //đăng ký
    public static void handleRegister(Scanner sc) {

        Users user = new Users();
        user.inputUser(sc);
        userFeature.register(user);
        System.out.println(Color.GREEN + "Đăng ký thành công!" + Color.RESET);
    }

    //đăng nhập
    public static void handleLogin(Scanner sc) {
        System.out.println("Tên đăng nhập:");
        String userName = sc.nextLine();
        System.out.println("Mật khẩu:");
        String password = sc.nextLine();
        Users user = userFeature.login(userName, password);
        if (user == null) {
            System.err.println("Email và password không đúng!");
            return;
        }
        System.out.println(Color.GREEN + "Đăng nhập thành công!" + Color.RESET);
        System.out.println();
        userLogin = user;

        //check quyền
        if (user.getRoleName().equals(RoleName.ROLE_ADMIN)) {
            MenuAdmin menuAdmin = new MenuAdmin();
            menuAdmin.menuAdmin(sc);
        } else if (user.getRoleName().equals(RoleName.ROLE_MODERATOR)) {
            MenuModerator menuModerator = new MenuModerator();
            menuModerator.menuModerator(sc);
        } else {
            MenuUsers menuUser = new MenuUsers();
            menuUser.menuUsers(sc);
        }

    }
}


