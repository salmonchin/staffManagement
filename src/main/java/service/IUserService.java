package service;

import pojo.User;

public interface IUserService {
    public boolean Reg(User user);
    public boolean Login(User user);
}
