package assignment.jongmin.producer.repository;

import assignment.jongmin.producer.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserRepositoryMariaDB implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public List<User> findByStatus(String status) {
        return em.createQuery("select u from User u where u.status = :status", User.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<User> findUsers(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User findOne(Long id) {
        return em.find(User.class, id);
    }
}
