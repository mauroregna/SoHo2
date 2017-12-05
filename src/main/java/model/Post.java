package model;

import java.util.Date;
import javax.validation.constraints.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Size(min=2,max=255)
	private String content;
	
	@NotNull
	private int user_id;
	
	 @ManyToOne(fetch=FetchType.EAGER)
	 private Image image;
	
	@NotNull
	private String username;
	
	@NotNull
	private Date create_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
}
	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", user_id=" + user_id + ", image=" + image + ", username="
				+ username + ", create_at=" + create_at + "]";
	}
	
}
