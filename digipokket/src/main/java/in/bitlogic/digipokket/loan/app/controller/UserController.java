package in.bitlogic.digipokket.loan.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;


import in.bitlogic.digipokket.loan.app.model.User;
import in.bitlogic.digipokket.loan.app.service.UserService;

@RestController
@RequestMapping("/admin")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestParam(value="user")String json, @RequestParam (value="photo") MultipartFile file1,@RequestParam (value="signature") MultipartFile file2) throws IOException
	{
		
		ObjectMapper om=new ObjectMapper();
		User u=om.readValue(json, User.class);
		u.getUserDocs().setPhoto(file1.getBytes());
		u.getUserDocs().setSignature(file2.getBytes());
		User user=userService.createUser(u);
		
		
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<List<User>> getUser()
	{
		List<User> liuser=userService.getUser();
		return new ResponseEntity<List<User>>(liuser,HttpStatus.OK);
		

	}
	@PutMapping("/updateUser/{userId}")
	public User updateUser(@PathVariable int userId,@RequestBody User u)
	{
		
		u.setUserId(userId);
		System.out.println(u.getUserId());
		return userService.updateUser(u);
	}
	@GetMapping("/authenticateUser/{username}/{password}")
	public ResponseEntity<User> authenticateUser(@PathVariable("username") String username,@PathVariable("password") String password)
	{
		User user=userService.authenticateUser(username,password);
		if(user!=null)
		{
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		 return null;
	}

}
