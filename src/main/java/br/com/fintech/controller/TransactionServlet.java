package br.com.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.dao.transaction.TransactionDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Transaction;
import br.com.fintech.model.TransactionCategory;
import br.com.fintech.model.User;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TransactionDAO dao;

    public TransactionServlet() {
        dao = DAOFactory.getTransactionDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	        String title = request.getParameter("title");
	        String description = request.getParameter("description");
	        Double value = Double.parseDouble(request.getParameter("value")); ;
	        int categoryId = Integer.parseInt(request.getParameter("category"));
	        TransactionCategory transactionCategory = new TransactionCategory(categoryId);
	        
	        HttpSession session = request.getSession();
	        int userId = (int) session.getAttribute("id");
	        User user = new User(userId);
	        
	        Transaction transaction = new Transaction(title, value, description, user, transactionCategory);

	        dao.insert(transaction);
	        
	        request.setAttribute("success", "Transação cadastrada com sucesso");
	        request.getRequestDispatcher("transaction-register.jsp").forward(request, response);
	    } catch (DBException db) {
	        db.printStackTrace();
	        request.setAttribute("error", "Erro ao cadastrar");
	        request.getRequestDispatcher("transaction-register.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Por favor, valide os dados");
	        request.getRequestDispatcher("transaction-register.jsp").forward(request, response);
	    }
	}
}
