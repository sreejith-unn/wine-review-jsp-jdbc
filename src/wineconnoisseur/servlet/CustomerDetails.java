package wineconnoisseur.servlet;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineconnoisseur.dal.CustomersDao;
import wineconnoisseur.dal.FollowsDao;
import wineconnoisseur.dal.ReviewsDao;
import wineconnoisseur.dal.TasterRatingsDao;
import wineconnoisseur.dal.TastersDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Customers;
import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.TasterRatings;
import wineconnoisseur.models.Tasters;
@WebServlet("/customerdetails")
public class CustomerDetails extends HttpServlet{
	protected FollowsDao followsDao;
	protected CustomersDao customersDao;
	protected ReviewsDao reviewsDao;
	protected WinesDao winesDao;
	@Override
	public void init() throws ServletException {
		
		customersDao = CustomersDao.getInstance();
		followsDao = FollowsDao.getInstance();
		reviewsDao = ReviewsDao.getInstance();
		winesDao = WinesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        
        
		
        String customerId = req.getParameter("id");
        Customers customer = new Customers();
        List<Reviews> reviews = new ArrayList<Reviews>();
        HashMap<Integer, String> wines = new HashMap<Integer, String>();
        List<Tasters> following = new ArrayList<Tasters>();
        
        try {
	        if (customerId != null && !customerId.trim().isEmpty()) {
	        	customer = customersDao.getCustomerById(Integer.parseInt(customerId));
	        	reviews = reviewsDao.getReviewsByCustomerId(Integer.parseInt(customerId));
	        	following = followsDao.getTastersFollowedByCustomerById(Integer.parseInt(customerId));
	        	for(Reviews r:reviews) {
	        		wines.put(r.getWineId(),winesDao.getWineById(r.getWineId()).getName());
	        	}
	        } else {
	        	messages.put("title", "Invalid Customer ID.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        

        req.setAttribute("customer", customer);
        req.setAttribute("reviews", reviews);
        req.setAttribute("wines", wines);
        req.setAttribute("following", following);
        req.setAttribute("followingCount", following.size());
        req.getRequestDispatcher("/CustomerDetails.jsp").forward(req, resp);
	}
}
