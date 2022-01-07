package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.BikeType;
import entity.Station;
import property.Properties;

public class BikeTypeService {
	public BikeType getBikeTypeById(int bikeTypeId) throws SQLException {
		String sql = " Select * from BikeType WHERE id=" + bikeTypeId;
		Statement stm = Properties.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		BikeType bikeType = new BikeType();
		
		if(res.next()) {
			  bikeType.setId(res.getInt("id"));
	          bikeType.setDeposit(res.getInt("deposit"));
	          bikeType.setTypeName(res.getString("typeName"));
	          bikeType.setRate(res.getDouble("rate"));
	          return bikeType;
       }
		return null;
	}
	
	
	public List getListAllBikeType() throws SQLException {
		Statement stm = Properties.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from biketype");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            BikeType biketype = new BikeType();
            biketype.setId(res.getInt("id"));
	        biketype.setDeposit(res.getInt("deposit"));
	        biketype.setTypeName(res.getString("typeName"));
	        biketype.setRate(res.getDouble("rate"));
                
            medium.add(biketype);
        }
        return medium;
	}
}
