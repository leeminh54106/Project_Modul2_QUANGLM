import project.ra.entity.Category;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Category cat = new Category();
        cat.inputCategory(sc);
        cat.displayCategory();
    }
}