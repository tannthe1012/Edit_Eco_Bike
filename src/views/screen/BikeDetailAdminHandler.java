package views.screen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import controller.StationInfoController;
import entity.Bike;
import entity.Station;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.HomeScreenHandler;
import views.screen.station.StationScreenHandler;

public class BikeDetailAdminHandler extends FXMLScreenHandler{
	
	public static Logger LOGGER = Utils.getLogger(BikeDetailAdminHandler.class.getName());
	@FXML
	private ImageView imageBike;
	
	@FXML
	private Label nameBike;
	
	@FXML
	private Label typeName;
	
	@FXML
	private Label Deposit;
	
	@FXML
	private Label licensePlate;
	
	@FXML
	private Label status;
	
	
	
	public BikeDetailAdminHandler(String screenPath, Bike bike, AdminBikeHandler adminbikehandler) throws SQLException, IOException {
		super(screenPath);
		nameBike.setText(bike.getName());
		typeName.setText(bike.getBikeType().getTypeName());
		Deposit.setText(String.valueOf(bike.getBikeType().getDeposit()) + " VNĐ");
		licensePlate.setText(bike.getLicencePlate());
		File file1 = new File(Configs.IMAGE_PATH + "/" + "anhxedap1.jpg");
		File file2 = new File(Configs.IMAGE_PATH + "/" + "anhxedap2.jpg");
        Image img1 = new Image(file1.toURI().toString());
        Image img2 = new Image(file2.toURI().toString());
        int a=new Random().nextInt();
        if(a%2==1){
        	imageBike.setImage(img1);
        }else {
        	imageBike.setImage(img2);
        }
        
		if (bike.getStatus() == 0) {
			status.setText("Không sẵn sàng");
		} else {
			status.setText("Sẵn sàng");
		}
	}
	

	@FXML
	void editBike(MouseEvent event) throws IOException {
		System.out.println("Edit Bike");
	}
	
	@FXML
	void rentBike(MouseEvent event) throws IOException {
		System.out.println("Thuê xe");
	}
	

}
