package assignment.jongmin.consumer.repository;

import assignment.jongmin.consumer.domain.ManagedUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ManagedUserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(ManagedUser user) {
        em.persist(user);
    }
}
