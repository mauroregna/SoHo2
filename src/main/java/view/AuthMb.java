package view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.User;

import controller.UserController;


@Named
@SessionScoped
public class AuthMb implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UserController userCntr;

	private String name;
	private String lastname;
	private String email;
	private String password;
	private User currentUser;
	
	public boolean isLogged(){
		return currentUser != null;
	}

	public String loggin(){
		currentUser = userCntr.getAuthUser(email, password);
		if (currentUser == null) {
		      currentUser = new User();
		      return null;
		} else {
			return "home?faces-redirect=true";
		}
	}
	
	public String logout(){
		currentUser = null;
		return "index";
	}
	
	public UserController getUserCntr() {
		return userCntr;
	}
	public void setUserCntr(UserController userCntr) {
		this.userCntr = userCntr;
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
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}