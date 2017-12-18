package view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import model.Image;
import model.User;
import controller.ImageController;
import controller.UserController;


@Named
@MultipartConfig(location="/tmp",
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5,
maxRequestSize=1024*1024*5*5)

public class RegisterMb {

	@Inject
	private UserController userCntr;
	@Inject 
	private ImageController imgCntl;

	private String name;
	private String lastname;
	private String email;
	private String password;
	private Part file;
	
	public String create(){
		try{
			Image img = null;
			if (file != null && file.getSize() > 0 && file.getContentType().startsWith("image/")) {
				img = imgCntl.upload(file);
				User user = new User();
				user.setImage(img);
				user.setName(name);
				user.setLastname(lastname);
				user.setEmail(email);
				user.setPassword(password);
				userCntr.create(user);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado correctamente",null);
			    FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			return "login?faces-redirect=true";	
		}catch(Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Faltan datos por completar",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		
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
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	
}