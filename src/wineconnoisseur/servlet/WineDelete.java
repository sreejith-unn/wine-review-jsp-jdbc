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

@WebServlet("/winedelete")
public class WineDelete extends HttpServlet {

	private static final long serialVersionUID = 4541691586206121715L;
	
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
        	messages.put("info", "Nothing to delete! Please go back.");
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
        req.getRequestDispatcher("/WineDelete.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
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
					Wines deletedWine = winesDao.delete(currentWine);
					if (deletedWine == null) {
						messages.put("action", "success");
			        	messages.put("info", "Successfully deleted the wine of id equal to " + wineId);
					} else {
						messages.put("action", "error");
			        	messages.put("info", "Failed to delete the wine");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        req.getRequestDispatcher("/WineDelete.jsp").forward(req, resp);
	}
}
