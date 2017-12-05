package view;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import controller.ImageController;
import controller.PostController;
import view.AuthMb;
import model.Image;
import model.Post;

@Named
@MultipartConfig(location="/tmp",
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5,
maxRequestSize=1024*1024*5*5)

public class PostMb {
	
	@Inject
	private PostController postCntr;
	@Inject
	private AuthMb auth;
	@Inject 
	private ImageController imgCntl;
	
	private int id;
	private String content;
	private int user_id;
	private Date create_at;
	private Part file;
	private Image image;
	
	public void create(){
		try{
			image = null;
			if(file != null && file.getSize() > 0 && file.getContentType().startsWith("image/")){
				image = imgCntl.upload(file);
			}
			Post post = new Post();
	    	post.setContent(content);
	    	post.setUser_id(auth.getCurrentUser().getId());
	    	post.setUsername(auth.getCurrentUser().getName());
	    	post.setCreate_at(new Date());
	    	post.setImage(image);
	    	postCntr.create(post);
	    	content = null;
		}catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public List<Post> getPost(){
			return postCntr.allPost();
	}
	
	public List<Post> getUserPost() {
		return postCntr.postByUser(auth.getCurrentUser().getId());
	}
	
	public PostController getPostCntr() {
		return postCntr;
	}

	public void setPostCntr(PostController postCntr) {
		this.postCntr = postCntr;
	}

	public AuthMb getAuth() {
		return auth;
	}

	public void setAuth(AuthMb auth) {
		this.auth = auth;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
}

}
