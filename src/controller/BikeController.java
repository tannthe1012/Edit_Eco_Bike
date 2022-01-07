package controller;

import java.sql.SQLException;
import java.util.List;

import entity.Bike;
import service.BikeService;
import service.StationService;

public class BikeController extends BaseController {
	
	public void addNewBike(Bike bike) throws SQLException {
		BikeService bikeService = new BikeService();
		bikeService.addNewStation(bike);
	}
	
//	public List getListBike() throws SQLException {
//		return new StationService().getListAllStation();		
//	}

}
