package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.AbstractModel;
import br.edu.utfpr.alexandre.coelho.oo24s.util.EntityManagerUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class GenericDao<T extends AbstractModel, I extends Serializable> {

    @PersistenceContext(unitName = "oo24s-trabalhofPU")
    protected EntityManager em;

    private Class<T> persistedClass;

    public GenericDao() {
    }

    public GenericDao(Class<T> persistedClass) {
        this();
        this.persistedClass = persistedClass;
        this.em = EntityManagerUtil.getEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void save(T entity) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        if (entity.getId() != null) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
        em.flush();
        t.commit();
    }

    public void insert(T entity) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(entity);
        em.flush();
        t.commit();
    }

    public void update(T entity) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(entity);
        em.flush();
        t.commit();
    }

    public void delete(I id) throws Exception {
        T entity = getOne(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            T mergedEntity = em.merge(entity);
            em.remove(mergedEntity);
            em.flush();
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw new Exception("Não foi possível remover o registro!");
        }
    }

    public T getOne(I id) {
        return em.find(persistedClass, id);
    }
    
    public List<T> getSemReserva() {
        Query query = em.createNativeQuery("select * from " + persistedClass.getSimpleName() +
                                          " where id not in (select " + persistedClass.getSimpleName() + "_id from reserva where aberta = 'T')", persistedClass);
        return query.getResultList();
    }

    public List<T> getAll() {
        em.clear();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistedClass);
        query.from(persistedClass);
        return em.createQuery(query).getResultList();
    }

    public boolean isValid(T entity) {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        return (validator.validate(entity).isEmpty());
    }

    public String getErrors(T entity) {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set< ConstraintViolation<T>> violations = validator.validate(entity);
                
        String errors = "";
        if (!violations.isEmpty()) {
            errors = violations.stream().map((violation) -> violation.getMessage() + "\n").reduce(errors, String::concat);           
        }
        return errors;
    }
}
