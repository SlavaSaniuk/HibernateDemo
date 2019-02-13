package by.bsac.dao;

import by.bsac.models.User;

import java.util.List;

/**
 *
 */
public interface UserDao {

    /** Interface methods */
    List<User> findAll();
    User findByFname(String a_fname);
    List<User> findByLname(String a_lname);

    User findById(Long a_id);

    void create(User a_user);
    void update(User a_user);
    void delete(User a_user);
}
