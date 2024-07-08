package project.ra.feature.impl;

import project.ra.entity.WishList;
import project.ra.feature.IWhishList;
import project.ra.utils.Color;
import project.ra.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class WhishListImpl implements IWhishList {
    public static List<WishList> wishLists = new ArrayList<>();

    //đọc (IOFile)
    public WhishListImpl() {
        wishLists = IOFile.readFromFile(IOFile.PATH_WISHLIST);
    }

    @Override
    public List<WishList> findAll() {
        return wishLists;
    }

    @Override
    public void addOrUpdate(WishList wishList) {
        int index = findIndexById(wishList.getWhishListId());
        if (index >= 0) {
            System.err.println("Sản phẩm đã tồn tại!");
        } else {
            wishLists.add(wishList);
        }

        IOFile.writeToFile(IOFile.PATH_WISHLIST, wishLists);
    }

    @Override
    public void delete(Integer id) {
        int index = findIndexById(id);
        if (index >= 0) {
            wishLists.remove(index);
            System.out.println(Color.GREEN + "Xóa sản phẩm thành công!");
        } else {
            System.err.println("Mã sản phẩm không đúng!");
        }
        IOFile.writeToFile(IOFile.PATH_WISHLIST, wishLists);
    }

    @Override
    public int findIndexById(Integer id) {
        for (int i = 0; i < wishLists.size(); i++) {
            if (wishLists.get(i).getWhishListId() == id) {
                return i;
            }
        }
        return -1;
    }
}
