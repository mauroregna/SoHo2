package controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import model.User;
import model.Image;

@Stateless
public class UserController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(User user){
		entityManager.persist(user);
    }
	
	public void loadImageProfile(int user_id, Image image){
			String jpql = "Update User u SET u.image = :img where u.id= :user_id";
			TypedQuery<User> q = entityManager.createQuery(jpql, User.class);
			q.setParameter("user_id", user_id);
			q.setParameter("img", image);
			q.executeUpdate();

	}
	
	public User getAuthUser(String email,String password){
		try {
            User user = (User) entityManager
               .createQuery(
                           "SELECT u from User u where u.email = :email and u.password = :password")
               .setParameter("email", email)
               .setParameter("password", password).getSingleResult();
    			return user;
			} catch (NoResultException e) {
				return null;
		}
	}
	
	public List<User> all()
    { 
        CriteriaQuery<User> cq = entityManager.getCriteriaBuilder().createQuery(User.class);
        cq.select(cq.from(User.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
    public User byId(int id){
        return entityManager.find(User.class, id);
    }	
}