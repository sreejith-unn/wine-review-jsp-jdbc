package wineconnoisseur.servlet;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineconnoisseur.dal.ReviewsDao;
import wineconnoisseur.models.Reviews;

@WebServlet("/deletereview")
public class DeleteReview extends HttpServlet{
	
	protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        String reviewId = req.getParameter("id");
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Review");       
        req.setAttribute("reviewId", reviewId);
        req.getRequestDispatcher("/DeleteReview.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("title", "Invalid reviewId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Reviews review = new Reviews(Integer.parseInt(reviewId));
	        try {
	        	review = reviewsDao.delete(review);
	        	// Update the message.
		        if (review == null) {
		            messages.put("title", "Successfully deleted");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Delete failed");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteReview.jsp").forward(req, resp);
    }
	
}
