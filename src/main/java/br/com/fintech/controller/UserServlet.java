package br.com.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.user.UserDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDAO dao;

    public UserServlet() {
    	super();
        dao = DAOFactory.getUserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        User user = new User(name, email, password, 0);

	        dao.insert(user);

	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    } catch (DBException db) {
	        db.printStackTrace();
	        request.setAttribute("error", "Erro ao cadastrar");
	        request.getRequestDispatcher("user-register.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Por favor, valide os dados");
	        request.getRequestDispatcher("user-register.jsp").forward(request, response);
	    }
	}
}