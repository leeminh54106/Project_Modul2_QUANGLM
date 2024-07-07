package project.run.managements;

import project.ra.entity.Category;
import project.ra.feature.ICategory;
import project.ra.feature.impl.CategoryImpl;
import project.ra.feature.impl.FeatureAll;
import project.ra.utils.Color;

import java.util.Scanner;

public class CategoryManagement {
    public static ICategory categoryFeature = new CategoryImpl();

    public static void categoryMenu(Scanner sc) {
        boolean quit = true;
        do {
            String borderColor = Color.PURPLE;
            String bottomColor = borderColor + "╚══════════════════════════════════════════════════════════════════╝" + Color.RESET;
            String rowColor = borderColor + "║" + Color.RESET;
            String topColor = borderColor + "╔════════════════════════════ CATEGORY ════════════════════════════╗" + Color.RESET;

            System.out.println(topColor);
            System.out.println(rowColor + "" + borderColor + "     1. Hiển thị danh sách danh mục                               " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     2. Thêm mới danh muc                                         " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     3. Cập nhật danh mục                                         " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     4. Xóa danh mục                                              " + rowColor);
            System.out.println(rowColor + "" + borderColor + "     5. Thoát                                                     " + rowColor);
            System.out.println(bottomColor);

            System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
            int choice = FeatureAll.inputNumber(sc);
            switch (choice) {
                case 1:
                    showAll();
                    break;
                case 2:
                    addCategory(sc);
                    break;
                case 3:
                    updateCategory(sc);
                    break;
                case 4:
                    deleteCategory(sc);
                    break;
                case 5:
                    quit = false;
                    break;
                default:
                    System.err.println("Lựa chọn từ 1 -> 5, vui lòng chọn lại!");
            }
        } while (quit);
    }



    private static void deleteCategory(Scanner sc) {
        System.out.println("Nhập mã danh mục muốn xóa:");
        int number = FeatureAll.inputNumber(sc);
        categoryFeature.delete(number);
    }

    private static void updateCategory(Scanner sc) {
        System.out.println("Nhập mã danh muc:");
        int idUpdate = FeatureAll.inputNumber(sc);
        int indexUpdate = categoryFeature.findIndexById(idUpdate);
        if (indexUpdate >= 0) {
            Category categoryUpdate = CategoryImpl.categoryList.get(indexUpdate);
            boolean isExit = true;
            do {
                String borderColor = Color.PURPLE;
                String bottomColor = borderColor + "╚═════════════════════════════════════════════════════════════════╝" + Color.RESET;
                String rowColor = borderColor + "║" + Color.RESET;
                String topColor = borderColor + "╔═════════════════════════════ UPDATE ════════════════════════════╗" + Color.RESET;
                System.out.println(topColor);
                System.out.println(rowColor + "" + borderColor + "     1. Cập nhật lại tên danh mục                                " + rowColor);
                System.out.println(rowColor + "" + borderColor + "     2. Cập nhật lại mô tả danh mục                              " + rowColor);
                System.out.println(rowColor + "" + borderColor + "     3. Cập nhật lại trạng thái danh mục                         " + rowColor);
                System.out.println(rowColor + "" + borderColor + "     4. Thoát                                                    " + rowColor);
                System.out.println(bottomColor);
                System.out.println(Color.PURPLE + "Lựa chọn của bạn:" + Color.RESET);
                int choice = FeatureAll.inputNumber(sc);
                switch (choice) {
                    case 1:
                        System.out.println("Tên danh mục:");
                        categoryUpdate.setCategoryName(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Mô tả danh mục:");
                        categoryUpdate.setDescription(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Trạng thái danh mục:");
                        categoryUpdate.setStatus(Boolean.parseBoolean(sc.nextLine()));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Lựa chọn từ 1 -> 4, vui lòng nhập lại!");
                }
                categoryFeature.addOrUpdate(categoryUpdate);
            } while (isExit);
        } else {
            System.err.println("Không tìm thấy mã danh mục " + indexUpdate);
        }
    }

    private static void addCategory(Scanner sc) {
        System.out.println("Nhập số lượng danh mục muốn thêm:");
        int number = FeatureAll.inputNumber(sc);
        for (int i = 0; i < number; i++) {
            Category newCategory = new Category();
            newCategory.inputCategory(sc);
            categoryFeature.addOrUpdate(newCategory);
        }
    }

    private static void showAll() {
        if (CategoryImpl.categoryList.isEmpty()) {
            System.err.println("Danh mục trống!");
            return;
        }
        for (Category c : categoryFeature.findAll()) {
            c.displayCategory();
        }
        System.out.println(Color.BLUE +"+------------------------+------------------------------+--------------------------------------+---------------------------------+" + Color.RESET);
        System.out.println();
    }
}
