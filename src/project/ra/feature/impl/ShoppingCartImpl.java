package project.ra.feature.impl;

import project.ra.entity.ShoppingCart;
import project.ra.feature.IShoppingCart;
import project.ra.utils.Color;
import project.ra.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartImpl implements IShoppingCart {
    public static List<ShoppingCart> shoppingCartList = new ArrayList<>();

    //đọc (IOFile)
    public ShoppingCartImpl() {
        shoppingCartList = IOFile.readFromFile(IOFile.PATH_SHOPPINGCART);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartList;
    }

    @Override
    public void addOrUpdate(ShoppingCart shoppingCart) {
        int index = findIndexById(shoppingCart.getShoppingCartId());
        if (index >= 0) {
            shoppingCartList.set(index, shoppingCart);
        } else {
            shoppingCartList.add(shoppingCart);
        }

        IOFile.writeToFile(IOFile.PATH_SHOPPINGCART, shoppingCartList);
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public int findIndexById(Integer id) {
        for (int i = 0; i < shoppingCartList.size(); i++) {
            if (shoppingCartList.get(i).getShoppingCartId() == id) {
                return i;
            }
        }
        return -1;
    }
}
