package view;

import javax.inject.Inject;
import javax.inject.Named;

import model.User;
import controller.UserController;


@Named
public class RegisterMb {

	@Inject
	private UserController userCntr;

	private String name;
	private String lastname;
	private String email;
	private String password;
	
	public String create(){
    	User user = new User();
    	user.setName(name);
    	user.setLastname(lastname);
    	user.setEmail(email);
    	user.setPassword(password);
    	userCntr.create(user);
    	return "index";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/*
	public String register(){
		User user = new User(email, username, password);
		userCntr.register(user);
		return "index";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	*/
}