package wineconnoisseur.servlet;

import wineconnoisseur.dal.CustomersDao;
import wineconnoisseur.dal.ReviewsDao;
import wineconnoisseur.dal.SalesDao;
import wineconnoisseur.dal.StoresDao;
import wineconnoisseur.dal.TasterRatingsDao;
import wineconnoisseur.dal.TastersDao;
import wineconnoisseur.dal.WinesDao;
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

import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.Sales;
import wineconnoisseur.models.TasterRatings;
import wineconnoisseur.models.Wines;

@WebServlet("/winedetails")
public class Wine extends HttpServlet{
	protected WinesDao winesDao;
	protected TasterRatingsDao tasterRatingsDao;
	protected TastersDao tastersDao;
	protected CustomersDao customersDao;
	protected ReviewsDao reviewsDao;
	protected SalesDao salesDao;
	protected StoresDao storesDao;
	@Override
	public void init() throws ServletException {
		winesDao = WinesDao.getInstance();
		tasterRatingsDao = TasterRatingsDao.getInstance();
		tastersDao = TastersDao.getInstance();
		reviewsDao = ReviewsDao.getInstance();
		customersDao = CustomersDao.getInstance();
		salesDao = SalesDao.getInstance();
		storesDao = StoresDao.getInstance();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        wineconnoisseur.models.Wines wine = new Wines();
        List<TasterRatings> tasterRatings = new ArrayList<TasterRatings>();
        List<Reviews> reviews = new ArrayList<Reviews>();
        List<Sales> sales = new ArrayList<Sales>();
        HashMap<Integer, String> tasters = new HashMap<Integer, String>();
        HashMap<Integer, String> customers = new HashMap<Integer, String>();
        HashMap<Integer, String> stores = new HashMap<Integer, String>();
        int sumRating=0;
		// Retrieve BlogComments depending on valid PostId or UserName.
        String wineId = req.getParameter("id");
        
        
        try {
	        if (wineId != null && !wineId.trim().isEmpty()) {
	        
	        	wine = winesDao.getWineById(Integer.parseInt(wineId));
	        	tasterRatings = tasterRatingsDao.getTasterRatingsByWineId(Integer.parseInt(wineId));
	        	reviews = reviewsDao.getReviewsByWineId(Integer.parseInt(wineId));
	        	sales = salesDao.getSalesByWineId(Integer.parseInt(wineId));
	        	
	        	for(TasterRatings t:tasterRatings) {
	        		tasters.put(t.getTasterId(),tastersDao.getTasterByTasterId(t.getTasterId()).getUserName());
	        		sumRating +=t.getRating();
	        	}
	        	for(Reviews r:reviews) {
	        		customers.put(r.getCustomerId(),customersDao.getCustomerById(r.getCustomerId()).getUserName());
	        	}
	        	for(Sales s:sales) {
	        		stores.put(s.getStoreId(),storesDao.getStoreById(s.getStoreId()).getName());
	        	}
	        } else {
	        	messages.put("title", "Invalid Wine ID.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("tasters",tasters);
        req.setAttribute("wine", wine);
        req.setAttribute("customers",customers);
        req.setAttribute("reviews", reviews);
        req.setAttribute("ratings", tasterRatings);
        req.setAttribute("stores", stores);
        req.setAttribute("sales", sales);
        if(sumRating==0) {
        	req.setAttribute("avgrating", 0);
        }else {
        req.setAttribute("avgrating", sumRating/tasterRatings.size());
        }
        req.getRequestDispatcher("/WineDetails.jsp").forward(req, resp);
	}
}
