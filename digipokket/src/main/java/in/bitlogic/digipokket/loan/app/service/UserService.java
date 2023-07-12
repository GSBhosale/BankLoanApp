package in.bitlogic.digipokket.loan.app.service;

import in.bitlogic.digipokket.loan.app.model.User;

public interface UserService {

 public	User createUser(User u);


public User updateUser(User u);
 public User authenticateUser(String username, String password);


public User getSingleUser(Integer userId);

}
