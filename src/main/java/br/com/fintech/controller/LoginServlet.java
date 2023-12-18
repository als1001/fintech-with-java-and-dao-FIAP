package br.com.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.dao.transaction.TransactionDAO;
import br.com.fintech.dao.user.UserDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Transaction;
import br.com.fintech.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO dao;
    private TransactionDAO transactionDAO;

    public LoginServlet() {
    	super();
        dao = DAOFactory.getUserDAO();
        transactionDAO = DAOFactory.getTransactionDAO();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, password);
        

        if (dao.validate(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("id", user.getId());
            
    		List<Transaction> lista = null;
    		double saldoTotal = 0;
			try {
				
				lista = transactionDAO.getTransactionsByUser(user.getId());
				saldoTotal = transactionDAO.getTotalBalanceByUser(user.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
            request.setAttribute("transactions", lista);
            request.setAttribute("saldoTotal", saldoTotal);
            
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Campos inseridos incorretos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
	}
}
