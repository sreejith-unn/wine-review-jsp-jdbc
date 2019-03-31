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
import wineconnoisseur.dal.TasterRatingsDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.TasterRatings;
import wineconnoisseur.models.Wines;

@WebServlet("/createreview")
public class CreateReview extends HttpServlet {
	protected ReviewsDao reviewsDao;
	protected WinesDao winesDao;

	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
		winesDao = WinesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		String customerId = req.getParameter("id");
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
		req.setAttribute("customerId", customerId);
		req.getRequestDispatcher("/CreateReview.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String customerId = req.getParameter("customerid");

		String wineId = req.getParameter("wineid");
		String content = req.getParameter("content");
		String urlId = req.getParameter("id");
		
		Reviews r = new Reviews(Integer.parseInt(customerId),
				Integer.parseInt(wineId), content);
		try {
			reviewsDao.create(r);
			messages.put("success", "Successfully created rating");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("customerId", customerId);
		req.getRequestDispatcher("/CreateReview.jsp").forward(req, resp);
	}

}
