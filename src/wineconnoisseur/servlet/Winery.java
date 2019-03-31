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


import wineconnoisseur.dal.WineriesDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Wineries;
import wineconnoisseur.models.Wines;

@WebServlet("/winery")
public class Winery extends HttpServlet{

	protected WineriesDao wineriesDao;
	protected WinesDao winesDao;
	@Override
	public void init() throws ServletException {
		wineriesDao = WineriesDao.getInstance();
		winesDao = WinesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String wineryName = req.getParameter("wineryname");
        
        // Retrieve BlogUsers, and store in the request.
        
        
        Wineries winery = new Wineries();
        List<Wines> wines = new ArrayList<Wines>();
		try {
			winery = wineriesDao.getWineryByWineryName(wineryName);
			wines = winesDao.getWinesByWineryName(wineryName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        req.setAttribute("winery", winery);
        req.setAttribute("wines",wines);
        req.getRequestDispatcher("/Winery.jsp").forward(req, resp);
	}
}
