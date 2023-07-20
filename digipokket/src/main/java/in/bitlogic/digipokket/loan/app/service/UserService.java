package in.bitlogic.digipokket.loan.app.service;

import java.util.List;

import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.model.User;

public interface UserService {

 public	User createUser(User u);

public List<User> getUser();

public User updateUser(User u);
 public User authenticateUser(String username, String password);


public User getSingleUser(Integer userId);

public List<User> getAllUsers();

public void deleteUser(int uid);

public List<Enquiry> getAllEnquiry();

}
