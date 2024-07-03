package project.ra.feature.impl;

import java.util.Scanner;

public class FeatureAll {
    public static int inputNumber(Scanner sc) {
        do {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập lại số!");
            }
        } while (true);
    }
}
