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
import wineconnoisseur.dal.StoresDao;
import wineconnoisseur.models.Customers;
import wineconnoisseur.models.Stores;

@WebServlet("/editstore")
public class EditStore extends HttpServlet{
	protected StoresDao storesDao;
	
	@Override
	public void init() throws ServletException {
		storesDao = StoresDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		String storeId = req.getParameter("id");
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		Stores store = new Stores();
		try {
			store = storesDao.getStoreById(Integer.parseInt(storeId));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("store", store);
		req.getRequestDispatcher("/EditStore.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String storeId = req.getParameter("storeid");
		String name= req.getParameter("storename");
		String street1 = req.getParameter("street1");
		String street2 = req.getParameter("street2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zip = req.getParameter("zip");

		
		Stores s = new Stores(Integer.parseInt(storeId),name,street1,street2,city,state,Integer.parseInt(zip));
				
		try {
			storesDao.update(s);
			s = storesDao.getStoreById(Integer.parseInt(storeId));
			messages.put("success", "Successfully updated Store");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("store", s);
		req.getRequestDispatcher("/EditStore.jsp").forward(req, resp);
	}
	
}
