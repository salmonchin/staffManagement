package service;

import pojo.User;

import java.util.List;

public interface IAdminService {
    public List<User> Query();
    public boolean Update(User user);
    public boolean Remove(String username);
    public User Query(String id);
}
