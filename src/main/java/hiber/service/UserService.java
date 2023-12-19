package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> listUsers();
    void delete(User user);
    User getUserById(Long id);
}
