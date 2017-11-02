package view;


import javax.inject.Inject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import controller.PostController;
import view.AuthMb;
import model.Post;

@Named
public class PostMb implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PostController postCntr;
	@Inject
	private AuthMb auth;
	
	private int id;
	private String content;
	private int user_id;
	private Date create_at;
	
	public String create(){
		Post post = new Post();
    	post.setContent(content);
    	post.setUser_id(auth.getCurrentUser().getId());
    	post.setUsername(auth.getCurrentUser().getName());
    	post.setCreate_at(new Date());
    	postCntr.create(post);
    	return "home";
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
	
	

}
