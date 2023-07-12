package in.bitlogic.digipokket.loan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.model.User;
import in.bitlogic.digipokket.loan.app.service.UserService;

@RestController
@RequestMapping("/admin")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User u)
	{
		User user=userService.createUser(u);
		
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public User updateUser(@PathVariable int userId,@RequestBody User u)
	{
		
		u.setUserId(userId);
		System.out.println(u.getUserId());
		return userService.updateUser(u);
	}

}
