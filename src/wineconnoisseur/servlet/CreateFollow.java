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

import wineconnoisseur.dal.FollowsDao;
import wineconnoisseur.dal.ReviewsDao;
import wineconnoisseur.dal.TastersDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Follow;
import wineconnoisseur.models.Follows;
import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.Tasters;
import wineconnoisseur.models.Wines;

@WebServlet("/createfollow")
public class CreateFollow extends HttpServlet{
	protected FollowsDao followsDao;
	protected TastersDao tastersDao;
	@Override
	public void init() throws ServletException {
		followsDao = FollowsDao.getInstance();
		tastersDao = TastersDao.getInstance();
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		String customerId = req.getParameter("id");
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Tasters> tasters = new ArrayList<Tasters>();
		try {
			tasters=tastersDao.getAllTasters();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("tasters", tasters);
		req.setAttribute("customerId", customerId);
		req.getRequestDispatcher("/CreateFollow.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String customerId = req.getParameter("customerid");

		String tasterId = req.getParameter("tasterid");
		
		String urlId = req.getParameter("id");
		System.out.println(customerId);
		System.out.println(tasterId);
		
		Follows f = new Follows(Integer.parseInt(tasterId),Integer.parseInt(customerId));
		try {
			followsDao.createFollow(f);
			messages.put("success", "Successfully Followed");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("customerId", customerId);
		req.getRequestDispatcher("/CreateFollow.jsp").forward(req, resp);
	}
	
}
