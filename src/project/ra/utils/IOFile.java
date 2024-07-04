package project.ra.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static final String PATH_USER = "src/project/ra/data/users.txt";
    public static final String PATH_CATEGORY = "src/project/ra/data/category.txt";
    public static final String PATH_PRODUCT = "src/project/ra/data/product.txt";
    public static final String PATH_ADDRESS = "src/project/ra/data/address.txt";


    public static <T> void writeToFile(String path, List<T> list) {
        // input - vào trương trình
        // output - ghi ra file, đẩy dữ liểu khỏi chương trình
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
        } catch (IOException e) {
//			e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
//				ex.printStackTrace();
            }
        }
    }

    public static <T> List<T> readFromFile(String path) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<T> list = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            list = (List<T>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
//				ex.printStackTrace();
            }
        }
        return list;
    }
}

