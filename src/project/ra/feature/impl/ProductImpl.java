package project.ra.feature.impl;

import project.ra.entity.Product;
import project.ra.feature.IProduct;
import project.ra.utils.Color;
import project.ra.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProduct {
    public static List<Product> productList = new ArrayList<>();

    //đọc (IOFile)
    public ProductImpl() {
        productList = IOFile.readFromFile(IOFile.PATH_PRODUCT);
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void addOrUpdate(Product product) {
        int index = findIndexById(product.getProductId());
        if (index >= 0) {
            productList.set(index, product);
            System.out.println(Color.GREEN + "Cập nhật thành công!" + Color.RESET);
        } else {
            productList.add(product);
            System.out.println(Color.GREEN + "Thêm mới thành công!" + Color.RESET);
        }
        IOFile.writeToFile(IOFile.PATH_PRODUCT, productList);
    }

    @Override
    public void delete(Integer id) {
        int index = findIndexById(id);
        if (index >= 0) {
            productList.remove(index);
            System.out.println(Color.GREEN + "Xóa sản phẩm thành công!" + Color.RESET);
        } else {
            System.err.println("Mã sản phẩm không tồn tại!");
        }
        IOFile.writeToFile(IOFile.PATH_PRODUCT, productList);
    }

    @Override
    public int findIndexById(Integer id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == id) {
                return i;
            }
        }
        return -1;
    }
}
