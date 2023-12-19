package hiber.DAO;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> listUsers();
    void delete(User user);
    User getUserById(Long id);
}
