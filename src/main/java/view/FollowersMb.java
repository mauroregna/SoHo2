package view;

import javax.inject.Inject;
import javax.inject.Named;

import controller.UserController;
import model.Followers;
import model.User;

@Named
public class FollowersMb {
	
	@Inject
	private AuthMb auth;
	@Inject
	private UserController usrCntl;
	
	public void followUser(User userFollow){
		User user = auth.getCurrentUser();
		usrCntl.followUser(user, userFollow);
	}
	
	public Followers existFollow(User userFollow){
		User user = auth.getCurrentUser();
		Followers f = usrCntl.existFollow(user, userFollow);
		if(f == null){
			return null;
		}else{
			return f;
		}
	}
	
	public void unfollowUser(User userFollow){
		User user = auth.getCurrentUser();
		usrCntl.unfollowUser(user, userFollow);
	}
}
