package project.ra.feature.impl;

import project.ra.entity.Address;
import project.ra.feature.IAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressImpl implements IAddress {
    public static List<Address> addressList = new ArrayList<>();
    @Override
    public List<Address> findAll() {
        return addressList;
    }

    @Override
    public void addOrUpdate(Address address) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public int findIndexById(Integer id) {
        return 0;
    }
}
