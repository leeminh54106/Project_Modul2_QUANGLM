package project.ra.feature.impl;

import project.ra.constants.RoleName;
import project.ra.entity.Users;
import project.ra.feature.IUserFeature;
import project.ra.utils.IOFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserImpl implements IUserFeature {

    public static List<Users> users = new ArrayList<>();

    //đọc (IOFile)
    public UserImpl() {
        users = IOFile.readFromFile(IOFile.PATH_USER);
    }

    @Override
    public void register(Users user) {
        user.setRoleName(RoleName.ROLE_USER);
        users.add(user);
        // cập nhât lại IOFile và chuyền vào users
        IOFile.writeToFile(IOFile.PATH_USER, users);
    }

    @Override
    public Users login(String userName, String password) {
        Optional<Users> optionalUsers = users.stream().filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password)).findFirst();
        return optionalUsers.orElse(null);
    }

    @Override
    public List<Users> findAll() {
        return users;
    }

    @Override
    public void addOrUpdate(Users users) {
        int index = findIndexById(users.getId());
        if (index >= 0) {
            this.users.set(index, users);
        }else {
            this.users.add(users);
        }

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public int findIndexById(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}

