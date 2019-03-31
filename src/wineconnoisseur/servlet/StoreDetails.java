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

import wineconnoisseur.dal.SalesDao;
import wineconnoisseur.dal.StoresDao;
import wineconnoisseur.dal.WinesDao;
import wineconnoisseur.models.Sales;
import wineconnoisseur.models.Stores;


@WebServlet("/storedetails")
public class StoreDetails extends HttpServlet{
	protected StoresDao storesDao;
	protected SalesDao salesDao;
	protected WinesDao winesDao;
	public void init() throws ServletException {
		storesDao = StoresDao.getInstance();
		salesDao = SalesDao.getInstance();
		winesDao = WinesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        String storeId = req.getParameter("id");
        Stores store = new Stores();
        List<Sales> sales = new ArrayList<Sales>();
        HashMap<Integer, String> wines = new HashMap<Integer, String>();
        
        try {
	        if (storeId != null && !storeId.trim().isEmpty()) {
	        	store = storesDao.getStoreById(Integer.parseInt(storeId));
	        	sales = salesDao.getSalesByStoreId(Integer.parseInt(storeId));
	        	
	        	for(Sales s:sales) {
	        		wines.put(s.getWineId(),winesDao.getWineById(s.getWineId()).getName());
	        	}
	        } else {
	        	messages.put("title", "Invalid Store ID.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        

        req.setAttribute("store", store);
        req.setAttribute("sales", sales);
        req.setAttribute("wines", wines);
        
        req.getRequestDispatcher("/StoreDetails.jsp").forward(req, resp);
	}
}
