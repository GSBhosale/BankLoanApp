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
	public User createUser(User u) {
		
		return userRepository.save(u);
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}

}
