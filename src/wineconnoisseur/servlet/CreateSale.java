package wineconnoisseur.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineconnoisseur.dal.ReviewsDao;
import wineconnoisseur.dal.SalesDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.Sales;
import wineconnoisseur.models.Wines;

@WebServlet("/createsale")
public class CreateSale extends HttpServlet{
	protected WinesDao winesDao;
	protected SalesDao salesDao;
	
	@Override
	public void init() throws ServletException {
		winesDao = WinesDao.getInstance();
		salesDao = SalesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		String storeId = req.getParameter("id");
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Wines> wines = new ArrayList<Wines>();
		try {
			wines = winesDao.getAllWines();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("wines", wines);
		req.setAttribute("storeId", storeId);
		req.getRequestDispatcher("/CreateSale.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String storeId = req.getParameter("storeid");

		String wineId = req.getParameter("wineid");
		String quantity = req.getParameter("quantity");
		System.out.println(storeId);
		System.out.println(wineId);
		System.out.println(quantity);
		Sales sale = new Sales(Integer.parseInt(wineId), Integer.parseInt(storeId),
				Integer.parseInt(quantity));
		
		try {
			salesDao.create(sale);
			messages.put("success", "Successfully created sale");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("storeId", storeId);
		req.getRequestDispatcher("/CreateSale.jsp").forward(req, resp);
	}
}
