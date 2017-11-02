package controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Post;

@Stateless
public class PostController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(Post post){
		entityManager.persist(post);
    }
	
	public List<Post> allPost()
    { 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> cq = entityManager.getCriteriaBuilder().createQuery(Post.class);
        Root<Post> p = cq.from(Post.class);
        cq.select(p);
        cq.orderBy(cb.desc(p.get("create_at")));
        return entityManager.createQuery(cq).getResultList();
    }
    
    public Post byId(int id){
        return entityManager.find(Post.class, id);
    }
    
    public List<Post> postByUser(int id){
    	String hql = "Select p from Post p where p.user_id = :id ORDER BY p.create_at DESC";
    	TypedQuery<Post> q = entityManager.createQuery(hql,Post.class);
    	q.setParameter("id", id);
    	return q.getResultList();   	
    }
    
}