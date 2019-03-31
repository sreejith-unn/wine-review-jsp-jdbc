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

import wineconnoisseur.dal.TasterRatingsDao;
import wineconnoisseur.models.TasterRatings;

@WebServlet("/deleterating")
public class DeleteRating extends HttpServlet{
	protected TasterRatingsDao ratingsDao;
	
	@Override
	public void init() throws ServletException {
		ratingsDao = TasterRatingsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        String ratingId = req.getParameter("id");
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Rating");       
        req.setAttribute("ratingId", ratingId);
        req.getRequestDispatcher("/DeleteRating.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String ratingId = req.getParameter("ratingid");
        if (ratingId == null || ratingId.trim().isEmpty()) {
            messages.put("title", "Invalid ratingId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        TasterRatings rating = new TasterRatings(Integer.parseInt(ratingId));
	        try {
	        	rating = ratingsDao.delete(rating);
	        	// Update the message.
		        if (rating == null) {
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
        
        req.getRequestDispatcher("/DeleteRating.jsp").forward(req, resp);
    }
}
