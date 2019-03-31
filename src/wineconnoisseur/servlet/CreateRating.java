package wineconnoisseur.servlet;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineconnoisseur.dal.TasterRatingsDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.TasterRatings;
import wineconnoisseur.models.Wines;

@WebServlet("/createrating")
public class CreateRating extends HttpServlet {
	protected TasterRatingsDao ratingsDao;
	protected WinesDao winesDao;

	@Override
	public void init() throws ServletException {
		ratingsDao = TasterRatingsDao.getInstance();
		winesDao = WinesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		String tasterId = req.getParameter("id");
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
		req.setAttribute("tasterId", tasterId);
		req.getRequestDispatcher("/CreateRating.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String tasterId = req.getParameter("tasterid");

		String wineId = req.getParameter("wineid");
		String rating = req.getParameter("rating");
		String urlId = req.getParameter("id");
		if (wineId == null || wineId.trim().isEmpty()) {
			messages.put("success", "Invalid Wine Name");
		} else if (rating == null || rating.trim().isEmpty()) {
			messages.put("success", "Invalid Rating");
		} else {

			TasterRatings t = new TasterRatings(Integer.parseInt(tasterId),
					Integer.parseInt(wineId), Integer.parseInt(rating));
			try {
				ratingsDao.create(t);
				messages.put("success", "Successfully created rating");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

		}
		req.setAttribute("tasterId", tasterId);
		req.getRequestDispatcher("/CreateRating.jsp").forward(req, resp);
	}

}
