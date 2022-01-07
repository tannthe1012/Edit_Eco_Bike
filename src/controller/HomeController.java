package controller;

import java.sql.SQLException;
import java.util.List;

import service.BikeService;
import service.StationService;

public class HomeController extends BaseController {
	
	public List getListAllstation() throws SQLException {
		return new StationService().getListAllStation();
	}
	public List getListAllBike() throws SQLException {
		return new BikeService().getListAllBike();
	}
}
