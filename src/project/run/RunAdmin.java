package project.run;

import project.ra.constants.RoleName;
import project.ra.entity.Users;
import project.ra.feature.impl.UserImpl;
import project.ra.utils.IOFile;

public class RunAdmin {
    public static void main(String[] args) {
        Users users = new Users();
        users.setFullName("ADMIN");
        users.setEmail("admin");
        users.setPassword("admin");
        users.setRoleName(RoleName.ROLE_ADMIN);
        UserImpl.users.add(users);
        IOFile.writeToFile(IOFile.PATH_USER,UserImpl.users);
    }
}
