package wineconnoisseur.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Wines;

@WebServlet("/findwines")
public class FindWines extends HttpServlet{
	protected WinesDao winesDao;
	
	@Override
	public void init() throws ServletException{
		winesDao = WinesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Wines> wines = new ArrayList<Wines>();
        
        // Retrieve and validate name.
        String wineName = req.getParameter("winename");
        if (wineName == null || wineName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve wines, and store as a message.
        	try {
            	wines = winesDao.getWinesByWineName(wineName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + wineName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousWineName", wineName);
        }
        req.setAttribute("wines", wines);
        
        req.getRequestDispatcher("/FindWines.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Wines> wines = new ArrayList<Wines>();
        
        // Retrieve and validate name.
        
        String wineName = req.getParameter("winename");
        if (wineName == null || wineName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		wines = winesDao.getWinesByWineName(wineName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + wineName);
        }
        req.setAttribute("wines", wines);
        
        req.getRequestDispatcher("/FindWines.jsp").forward(req, resp);
    }
}
