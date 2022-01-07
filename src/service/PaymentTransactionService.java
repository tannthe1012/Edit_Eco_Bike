package service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import entity.PaymentTransaction;
import property.Properties;
import utils.Utils;

public class PaymentTransactionService {
	private static Logger LOGGER = Utils.getLogger(RentalDealService.class.getName());
	public boolean create(PaymentTransaction paymentTransaction) throws SQLException {
		String sql = "insert into PaymentTransaction(errorCode, content, amount, createdAt, rentalDealId) values (\""
				+ paymentTransaction.getErrorCode() + "\", \"" 
				+ paymentTransaction.getTransactionContent() + "\" , " 
				+ paymentTransaction.getAmount() + " , \""
				+ paymentTransaction.getCreatedAt()+ "\" ,"
				+ paymentTransaction.getRentalDeal().getId()
				+ ")" ;
		LOGGER.info(sql);
		Statement stm = Properties.getConnection().createStatement();
		return stm.execute(sql);
    }	
}
