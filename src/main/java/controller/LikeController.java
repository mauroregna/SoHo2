package controller;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.LikePost;
import model.Post;
import model.User;

@Stateless
public class LikeController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(Post post, User user){
		post = entityManager.merge(post);
		LikePost c = new LikePost();
		c.setPost(post);
		c.setUser(user);
		entityManager.persist(c);
	}
	
	public long countLike(Post post){
		String hql = "Select count(l) from LikePost l where l.post = :post";       	
    	Long count = (Long) entityManager.createQuery(hql)
    		       .setParameter("post", post).getSingleResult();
    	return count;
	}
	
	public LikePost existLike (Post post, User user){
		try{
			LikePost like = (LikePost) entityManager
	               .createQuery(
	                           "SELECT l from LikePost l where l.post = :post and l.user = :user")
	               .setParameter("post", post)
	               .setParameter("user", user).getSingleResult();
	    			return like;
			} catch (NoResultException e) {
				return null;
		}
	}
	
	public void deleteLike(Post post, User user){
		String jpql = "DELETE FROM LikePost l WHERE l.post = :post and l.user = :user";
		TypedQuery<LikePost> q = entityManager.createQuery(jpql, LikePost.class);
		q.setParameter("post", post);
		q.setParameter("user", user);
		q.executeUpdate();
	}

}
