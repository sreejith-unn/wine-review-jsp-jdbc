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
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Wines;

@WebServlet("/wineupdate")
public class WineUpdate extends HttpServlet  {

	private static final long serialVersionUID = -5233228529884548767L;
	
	private WinesDao winesDao;

	@Override
	public void init() throws ServletException {
		winesDao = WinesDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("action", "init");
        String id = req.getParameter("wineId");
        if (id == null || id.trim().isEmpty()) {
        	messages.put("action", "error");
        	messages.put("info", "Nothing to update! Please go back.");
        } else {
        	Integer wineId = Integer.parseInt(id);
        	try {
				Wines wine = winesDao.getWineById(wineId);
				if (wine != null) {
					req.setAttribute("wine", wine);
				} else {
					messages.put("action", "error");
			    	messages.put("info", "WineId does not exist.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        req.getRequestDispatcher("/WineUpdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String price = req.getParameter("priceFld");
        String id = req.getParameter("wineIdFld");
        if (id == null || id.trim().isEmpty()) {
        	messages.put("action", "error");
        	messages.put("info", "Nothing to update! Please go back.");
        } else {
        	Integer wineId = Integer.parseInt(id);
	        try {
				Wines currentWine = winesDao.getWineById(wineId);
				if (currentWine == null) {
					messages.put("action", "error");
		        	messages.put("info", "WineId does not exist.");
				} else {
					if (price == null || price.trim().isEmpty()) {
			        	messages.put("action", "error");
			     	   	messages.put("info", "Please input a valid price for the wine.");
			     	    req.setAttribute("wine", currentWine);
			        } else {
			        	Integer newPrice = Integer.parseInt(price);
			        	if (newPrice < 0) { // Check for invalid price which is below zero
			        		messages.put("action", "error");
				     	   	messages.put("info", "Please input a valid price for the wine.");
				     	    req.setAttribute("wine", currentWine);
			        	} else {
			        		Wines updatedWine = winesDao.updatePrice(currentWine, newPrice);
				        	if (updatedWine.getPrice() != newPrice) {
				        		messages.put("action", "error");
					     	   	messages.put("info", "Failed to update the price of the wine.");
					     	    req.setAttribute("wine", currentWine);
				        	} else {
				        		messages.put("action", "success");
					     	   	messages.put("info", "Successfully updated the price of the wine to " + newPrice);
					     	   	req.setAttribute("wine", updatedWine);
				        	}
			        	}
			        }
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        req.getRequestDispatcher("/WineUpdate.jsp").forward(req, resp);
	}
}
