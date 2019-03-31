package wineconnoisseur.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineconnoisseur.dal.CustomersDao;
import wineconnoisseur.dal.FollowsDao;
import wineconnoisseur.dal.ReviewsDao;
import wineconnoisseur.dal.SalesDao;
import wineconnoisseur.dal.StoresDao;
import wineconnoisseur.dal.TasterRatingsDao;
import wineconnoisseur.dal.TastersDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Customers;
import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.Sales;
import wineconnoisseur.models.TasterRatings;
import wineconnoisseur.models.Tasters;
import wineconnoisseur.models.Wines;

@WebServlet("/tasterdetails")
public class TasterDetails extends HttpServlet{
	protected TastersDao tastersDao;
	protected TasterRatingsDao tasterRatingsDao;
	protected WinesDao winesDao;
	protected FollowsDao followsDao;
	@Override
	public void init() throws ServletException {
		
		tastersDao = TastersDao.getInstance();
		tasterRatingsDao = TasterRatingsDao.getInstance();
		winesDao = WinesDao.getInstance();
		followsDao = FollowsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        
        
		
        String tasterId = req.getParameter("id");
        Tasters taster = new Tasters();
        List<TasterRatings> tasterRatings = new ArrayList<TasterRatings>();
        HashMap<Integer, String> wines = new HashMap<Integer, String>();
        List<Customers> followers = new ArrayList<Customers>();
        try {
	        if (tasterId != null && !tasterId.trim().isEmpty()) {
	        	taster = tastersDao.getTasterByTasterId(Integer.parseInt(tasterId));
	        	tasterRatings = tasterRatingsDao.getTasterRatingsByTasterId(Integer.parseInt(tasterId));
	        	followers = followsDao.getFollowersForTasterId(Integer.parseInt(tasterId));
	        	for(TasterRatings t:tasterRatings) {
	        		wines.put(t.getWineId(),winesDao.getWineById(t.getWineId()).getName());
	        	}
	        } else {
	        	messages.put("title", "Invalid Taster ID.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        

        req.setAttribute("taster", taster);
        req.setAttribute("tasterRatings", tasterRatings);
        req.setAttribute("wines", wines);
        req.setAttribute("followers", followers);
        req.setAttribute("followerCount", followers.size());
        req.getRequestDispatcher("/TasterDetails.jsp").forward(req, resp);
	}
	
}
