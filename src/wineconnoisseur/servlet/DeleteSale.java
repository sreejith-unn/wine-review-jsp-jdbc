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

import wineconnoisseur.dal.ReviewsDao;
import wineconnoisseur.dal.SalesDao;
import wineconnoisseur.models.Reviews;
import wineconnoisseur.models.Sales;

@WebServlet("/deletesale")
public class DeleteSale extends HttpServlet{
	protected SalesDao salesDao;
	
	@Override
	public void init() throws ServletException {
		salesDao = SalesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        String saleId = req.getParameter("id");
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Sale");       
        req.setAttribute("saleId", saleId);
        req.getRequestDispatcher("/DeleteSale.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String saleId = req.getParameter("saleid");
        if (saleId == null || saleId.trim().isEmpty()) {
            messages.put("title", "Invalid saleId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Sales sale = new Sales(Integer.parseInt(saleId));
	        try {
	        	sale = salesDao.delete(sale);
	        	// Update the message.
		        if (sale == null) {
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
        
        req.getRequestDispatcher("/DeleteSale.jsp").forward(req, resp);
    }
}
