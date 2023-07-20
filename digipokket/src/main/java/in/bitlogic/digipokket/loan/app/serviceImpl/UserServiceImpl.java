package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.model.User;
import in.bitlogic.digipokket.loan.app.repositary.EnquiryRepositary;
import in.bitlogic.digipokket.loan.app.repositary.UserRepository;
import in.bitlogic.digipokket.loan.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EnquiryRepositary enquiryRepositary;
	
	@Override
	public User createUser(User u) 
	{
		int count=u.getDesignation().length();
		String username="digi"+u.getFirstName().charAt(0)+u.getLastName().charAt(0)+count;
		u.setUsername(username);
		
		String password="dp"+u.getFirstName().charAt(1)+u.getLastName().charAt(1)+"@"+u.getFirstName().length()+u.getLastName().length()+7532;
		u.setPassword(password);
		return userRepository.save(u);
	}

	@Override
	public List<User> getUser()
	{
		
		return userRepository.findAll();
		
	}
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}
	public User authenticateUser(String username, String password) {

		return userRepository.findByUsernameAndPassword(username,password);
	}

	@Override
	public User getSingleUser(Integer userId) {
		// TODO Auto-generated method stub
		Optional<User> ou=userRepository.findById(userId);
		User user=ou.get();
		return user;
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(int uid) {
		// TODO Auto-generated method stub
		userRepository.deleteById(uid);
	}
	
	@Override
	public List<Enquiry> getAllEnquiry() {
		// TODO Auto-generated method stub
		return enquiryRepositary.findAll();
	}

}
