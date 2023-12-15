package fr.sauvageb.chat.dao;

import fr.sauvageb.chat.dao.base.GenericDao;
import fr.sauvageb.chat.model.User;

public interface UserDao extends GenericDao<User, Long> {

    User findByUsername(String username);
}
