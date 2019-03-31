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
import wineconnoisseur.dal.TastersDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.Tasters;
import wineconnoisseur.models.Wines;

@WebServlet("/edittaster")
public class EditTaster extends HttpServlet{
	protected TastersDao tastersDao;
	
	@Override
	public void init() throws ServletException {
		tastersDao = TastersDao.getInstance();
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		String tasterId = req.getParameter("id");
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		Tasters taster = new Tasters();
		try {
			taster = tastersDao.getTasterByTasterId(Integer.parseInt(tasterId));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("taster", taster);
		req.getRequestDispatcher("/EditTaster.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String tasterId = req.getParameter("tasterid");
		String userName = req.getParameter("tastername");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String twitter = req.getParameter("twitter");

		
		
		Tasters t = new Tasters(Integer.parseInt(tasterId), userName, password,
				firstName, lastName, twitter);
				
		try {
			tastersDao.update(t);
			t = tastersDao.getTasterByTasterId(t.getUserId());
			messages.put("success", "Successfully updated Taster");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("taster", t);
		req.getRequestDispatcher("/EditTaster.jsp").forward(req, resp);
	}
	
	
}
