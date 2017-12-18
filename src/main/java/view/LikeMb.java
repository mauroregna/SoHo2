package view;

import javax.inject.Inject;
import javax.inject.Named;

import controller.LikeController;
import model.LikePost;
import model.Post;
import model.User;

@Named
public class LikeMb {
	
	@Inject
	private LikeController likeCntrl;
	@Inject
	private AuthMb auth;
	
	public void create(Post post){
		User user = auth.getCurrentUser();
		likeCntrl.create(post, user);
	}
	
	public long countLike(Post post){
		return likeCntrl.countLike(post);
	}
	
	public LikePost existLike (Post post){
		User user = auth.getCurrentUser();
		LikePost like = likeCntrl.existLike(post, user);
		if(like == null){
			return null;
		}else{
			return like;
		}
	}
	
	public void deleteLike (Post post){
		User user = auth.getCurrentUser();
		likeCntrl.deleteLike(post, user);
	}

}
