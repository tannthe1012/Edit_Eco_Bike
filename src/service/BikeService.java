package service;

import entity.Bike;
import entity.BikeType;
import entity.Station;
import property.Properties;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import service.StationService;
import service.BikeTypeService;

public class BikeService {
	private BikeTypeService bikeTypeService;

	public Bike getBikeByStationId(int stationId) throws SQLException {
		String sql = "SELECT * FROM bike WHERE typeId='1'";
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
//		if(res.next()) {
//            Bike bike = new Bike();
//            bike.setId(res.getInt("id"));
//            bike.setStationId(res.getInt("stationId"));
//            bike.setName(res.getString("name"));
//            bike.setTypeId(res.getInt("typeId"));
//            bike.setImage(res.getString("image"));
//            bike.setStatus(res.getInt("status"));
//        }
		return null;
	}

	public Bike getBikeById(int bikeId) throws SQLException {
		String sql = " Select * from bike WHERE id=" + bikeId;
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		Bike bike = new Bike();
		bikeTypeService = new BikeTypeService();

		Station station = new Station(1, "Station 1", "Location 1", 20, 20, 20);

		if (res.next()) {
			// get bikeType
			BikeType btype = bikeTypeService.getBikeTypeById(res.getInt("typeId"));
			bike.setBikeType(btype);

			// get station
			bike.setStation(station);

			bike.setId(res.getInt("id"));
			bike.setName(res.getString("name"));
			bike.setLicencePlate(res.getString("licencePlate"));
			bike.setImage(res.getString("image"));
			bike.setStatus(res.getInt("status"));
			bike.setBatterySpace(res.getInt("batterySpace"));
			bike.setBikeCode(res.getString("bikeCode"));
			bike.setCost(res.getInt("cost"));
			bike.setLoadCycles(res.getInt("loadCycles"));
			bike.setProducer(res.getString("producer"));
			bike.setManuafacturingDate(res.getDate("manuafacturingDate"));
			bike.setTimeRemaining(res.getInt("timeRemaining"));
			bike.setWeight(res.getInt("weight"));

			return bike;
		}
		return null;
	}

	public int updateBikeById(int bikeId, String table, String field, Object value) throws SQLException {
		Statement stm = Properties.getConnection().createStatement();

		return stm.executeUpdate(
				" update " + table + " set" + " " + field + "=" + value + " " + "where id=" + bikeId + ";");

	}

	
	
	public List getListAllBike() throws SQLException {
		Statement stm = Properties.getConnection().createStatement();
        ResultSet res = stm.executeQuery("SELECT * from bike JOIN biketype on bike.typeId = biketype.id LEFT JOIN station on station.id = bike.stationId");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Bike bike = new Bike();
            BikeType bikeType = new BikeType(res.getInt(16), res.getString(17), res.getInt(18), res.getDouble(19), null);
            Station station = new Station(res.getInt(20), res.getString(21), res.getString(22), res.getInt(24), res.getInt(25), res.getInt(26));
            bike.setId(res.getInt(1));
            bike.setBatterySpace(res.getInt(9));
            bike.setBikeCode(res.getString(8));
            bike.setBikeType(bikeType);
            bike.setCost(res.getInt(10));
            bike.setImage(res.getString(6));
            bike.setLicencePlate(res.getString(5));
            bike.setLoadCycles(res.getInt(11));
            bike.setManuafacturingDate(res.getDate(13));
            bike.setName(res.getString(2));
            bike.setProducer(res.getString(12));
            bike.setStation(station);
            bike.setStatus(res.getInt(7));
            bike.setTimeRemaining(res.getFloat(15));
            bike.setWeight(res.getInt(14));
            medium.add(bike);
  
        }
        
        return medium;
	}

	public void addNewStation(Bike bike) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("query");
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateStr = String.valueOf(bike.getManuafacturingDate().getYear()+1900) + "-"+ String.valueOf(bike.getManuafacturingDate().getMonth()+1<10?"0"+(bike.getManuafacturingDate().getMonth()+1):(bike.getManuafacturingDate().getMonth()+1)) + "-"+String.valueOf(bike.getManuafacturingDate().getDate()<10?"0"+bike.getManuafacturingDate().getDate():bike.getManuafacturingDate().getDate());
        
        
        
        String strDate = dateFormat.format(bike.getManuafacturingDate());  
        System.out.println(strDate);
//		String sql = String.format("INSERT INTO Bike (name,stationId,typeId,licencePlate,image, status,bikeCode,batterySpace,cost,loadCycles,producer, manuafacturingDate,weight,timeRemaining ) VALUES ('%s','%d', '%d','%s','%s', '%d', '%s','%d','%d', '%d', '%s', '%s','%d','%d' );",
//				bike.getName(),bike.getStation().getId(), bike.getBikeType().getId(), bike.getLicencePlate(), bike.getImage(), bike.getStatus(), bike.getBikeCode(),bike.getBatterySpace(), bike.getCost(), bike.getLoadCycles(), bike.getProducer(),
//				strDate,bike.getWeight(), bike.getTimeRemaining()
//				);
        
        String sql = "INSERT INTO Bike (name,stationId,typeId,licencePlate,image, status,bikeCode,batterySpace,cost,loadCycles,producer, manuafacturingDate,weight,timeRemaining ) VALUES ('"+
				bike.getName()+"', '"+bike.getStation().getId()+"', '"+ bike.getBikeType().getId()+"', '"+ bike.getLicencePlate()+"', '"+ bike.getImage()+"', '"+ bike.getStatus()+"', '"+ bike.getBikeCode()+"', '"+bike.getBatterySpace()+"', '"+ bike.getCost()+"', '"+ bike.getLoadCycles()+"', '"+ bike.getProducer()+"', '"+
				dateStr+"', '"+bike.getWeight()+"', '"+ bike.getTimeRemaining()+"');"
				;
		System.out.println(sql);
		
		Statement stm = Properties.getConnection().createStatement();
		stm.executeUpdate(sql);
		System.out.println("query2222");
	}

	
}
