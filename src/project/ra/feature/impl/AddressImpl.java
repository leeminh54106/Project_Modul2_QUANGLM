package project.ra.feature.impl;

import project.ra.entity.Address;
import project.ra.feature.IAddress;
import project.ra.utils.Color;
import project.ra.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class AddressImpl implements IAddress {
    public static List<Address> addressList = new ArrayList<>();
    static {
        addressList = IOFile.readFromFile(IOFile.PATH_ADDRESS);
    }

    //đọc (IOFile)
    public AddressImpl() {
        addressList = IOFile.readFromFile(IOFile.PATH_ADDRESS);
    }

    @Override
    public List<Address> findAll() {
        return addressList;
    }

    @Override
    public void addOrUpdate(Address address) {
        int index = findIndexById(address.getAddressId());
        if(index >= 0){
            addressList.set(index, address);
        }else {
            addressList.add(address);
        }
        IOFile.writeToFile(IOFile.PATH_ADDRESS,addressList);
    }

    @Override
    public void delete(Integer id) {
        int index = findIndexById(id);
        if(index >= 0){
            addressList.remove(index);
            System.out.println(Color.YELLOW + "Xóa địa chỉ thành công!" + Color.RESET);
        }else {
            System.err.println("Mã địa chỉ không tồn tại!");
        }
        IOFile.writeToFile(IOFile.PATH_ADDRESS,addressList);
    }

    @Override
    public int findIndexById(Integer id) {
       for(int i = 0; i < addressList.size(); i++){
           if(addressList.get(i).getAddressId() == id){
               return i;
           }
       }
       return -1;
    }
}
