package br.com.fintech.dao.category;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.Category;
import java.util.List;

public interface CategoryDAO {

	public void insert(Category category);
	public List<Category> listar();
	
}
