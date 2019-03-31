package wineconnoisseur.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineconnoisseur.dal.CustomersDao;

@WebServlet("/editcustomer")
public class EditCustomer extends HttpServlet{
	
	protected CustomersDao customersDao;
}
