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
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Category;
import br.com.fintech.model.Transaction;
import br.com.fintech.model.User;

/**
 * Servlet implementation class TrasactionCadastroServlet
 */
@WebServlet("/TrasactionCadastroServlet")
public class TrasactionCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TransactionDAO transactionDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrasactionCadastroServlet() {
        super();
        transactionDAO = DAOFactory.getTransactionDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		        String title = request.getParameter("title");
		        String description = request.getParameter("description");
		        Double value = Double.parseDouble(request.getParameter("value")); ;
		        int categoryId = Integer.parseInt(request.getParameter("category"));
		        Category category = new Category(categoryId);
		        
		        HttpSession session = request.getSession();
		        int userId = (int) session.getAttribute("id");
		        User user = new User(userId);
		        
		        Transaction transaction = new Transaction(title, value, description, user, category);

		        transactionDAO.insert(transaction);
		        
		        request.setAttribute("success", "Transação cadastrada com sucesso");
		        
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
