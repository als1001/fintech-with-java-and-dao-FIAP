package br.com.fintech.dao.user;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.User;

public interface UserDAO {
	public boolean validate(User user);
	public void insert(User user) throws DBException;
}