package controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import model.User;

@Stateless
public class UserController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(User user){
		entityManager.persist(user);
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

	
/*	
 * 	public User getAuthUser(String email,String password){
		for(User user : db.users){
			if (user.getUsername().equals(username) 
			 && user.getPassword().equals(password)){
					return user;
			}
		}
		return null;
	}
	
	public boolean userNameExist(String username){
		for(User user : db.users){
			if (user.getUsername().equals(username)){
					return true;
			}
		}
		return false;
	}
	
	public void register(User user){
		if(userNameExist(user.getUsername())){
			throw new RuntimeException("Usuario ya existe");
		}
		user.setId(db.nextUserId());
		db.users.add(user);
	}
	*/
	
}