package project.ra.feature;

import project.ra.entity.Users;

public interface IUserFeature extends IBase<Users,Integer> {
    void register(Users users);
    Users login(String email, String password);
}
