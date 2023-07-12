package in.bitlogic.digipokket.loan.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.User;
import in.bitlogic.digipokket.loan.app.repositary.UserRepository;
import in.bitlogic.digipokket.loan.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
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
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}
	public User authenticateUser(String username, String password) {

		return userRepository.findByUsernameAndPassword(username,password);
	}

}
