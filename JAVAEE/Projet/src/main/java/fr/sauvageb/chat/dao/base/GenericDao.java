package fr.sauvageb.chat.dao.base;


import java.util.List;

public interface GenericDao<T, ID> {

    boolean create(T entity);

    List<T> findAll();

    T findById(ID id);

    void update(T entity);

    void delete(T entity);

}
