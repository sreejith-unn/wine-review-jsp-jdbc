package wineconnoisseur.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineconnoisseur.dal.CustomersDao;
import wineconnoisseur.dal.TastersDao;
import wineconnoisseur.models.Customers;
import wineconnoisseur.models.Tasters;

@WebServlet("/editcustomer")
public class EditCustomer extends HttpServlet{
	
	protected CustomersDao customersDao;
	@Override
	public void init() throws ServletException {
		customersDao = CustomersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		String customerId = req.getParameter("id");
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		Customers customer = new Customers();
		try {
			customer = customersDao.getCustomerById(Integer.parseInt(customerId));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("customer", customer);
		req.getRequestDispatcher("/EditCustomer.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String customerId = req.getParameter("customerid");
		String userName = req.getParameter("customername");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String about = req.getParameter("about");

		
		
		Customers c = new Customers(Integer.parseInt(customerId), userName, password,
				firstName, lastName, about);
				
		try {
			customersDao.update(c);
			c = customersDao.getCustomerById(c.getUserId());
			messages.put("success", "Successfully updated Customer");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("customer", c);
		req.getRequestDispatcher("/EditCustomer.jsp").forward(req, resp);
	}
}
