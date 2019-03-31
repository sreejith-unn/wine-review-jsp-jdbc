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

@WebServlet("/winecreate")
public class WineCreate extends HttpServlet {

	private static final long serialVersionUID = -1500714306147409838L;
	
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
        req.getRequestDispatcher("/WineCreate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
       Map<String, String> messages = new HashMap<String, String>();
       req.setAttribute("messages", messages);
       String wineName = req.getParameter("nameFld");
       String description = req.getParameter("descriptionFld");
       if (wineName == null || wineName.trim().isEmpty() || 
    	   description == null || description.trim().isEmpty()) {
    	   messages.put("action", "error");
    	   messages.put("info", "Please input a valid name and a description for the wine.");
       } else {
    	   Integer price = Integer.parseInt(req.getParameter("priceFld"));
           String variety = req.getParameter("varietyFld");
           String vineyard = req.getParameter("vineyardFld");
           String wineryName = req.getParameter("wineryNameFld");
    	   Wines wine = new Wines(wineName, description, price, variety, vineyard, wineryName);
    	   try {
    		   wine = winesDao.create(wine);
    		   if (wine.getWineId() == 0) {
    			   messages.put("action", "error");
    			   messages.put("info", "Cannot add the wine into the database.");
    		   } else {
    			   messages.put("action", "success");
    			   messages.put("info", "Successfully created a new wine " + wineName);
    		   }
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    	   }
       }
       req.getRequestDispatcher("/WineCreate.jsp").forward(req, resp);
	}
}
