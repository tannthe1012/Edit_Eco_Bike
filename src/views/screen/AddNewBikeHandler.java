package views.screen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import common.exception.ViewCartException;
import controller.BikeController;
import controller.HomeController;
import controller.PaymentController;
import controller.StationController;
import entity.Bike;
import entity.BikeType;
import entity.Station;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.BikeTypeService;
import service.StationService;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

public class AddNewBikeHandler extends BaseScreenHandler {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField image;
	
	@FXML
	private TextField weight;
	
	@FXML
	private TextField cost;
	
	@FXML
	private TextField timeRemainting;
	
	@FXML
	private TextField bikeCode;
	
	@FXML
	private TextField producer;
	
	@FXML
	private TextField loadCycles;
	
	@FXML
	private TextField batterySpace;
	
	@FXML
	private TextField licensePlate;
	
	
	@FXML
	private DatePicker date;
	
	@FXML
	private ComboBox<String> type;
	
	@FXML
	private ComboBox<String> stationCbb;

	@FXML
	private Button btnAddNew;
	
	@FXML
	private Button cancel;
	
	private Station station;
	
	private StationController stationController;
	private List<Station> stationList;
	private List<BikeType> BikeTypeList;

	public AddNewBikeHandler(Stage stage, String screenPath) throws IOException, SQLException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		// get Type
		StationService stationService = new StationService();
		BikeTypeService bikeTypeService = new BikeTypeService();
		List<String> typeStrings = new ArrayList<String>();
		BikeTypeList = bikeTypeService.getListAllBikeType();
		for (BikeType object : BikeTypeList) {
			typeStrings.add(object.getTypeName());
		}
		this.type.getItems().addAll(typeStrings);
		
		// get StationName
		List<String> stationStrings = new ArrayList<String>();

		stationList = stationService.getListAllStationExistEmpty();
		for (Station object : stationList) {
			stationStrings.add(object.getName());
		}
		this.stationCbb.getItems().addAll(stationStrings);
		
		
		btnAddNew.setOnMouseClicked(e -> {
			try {
				requestToAddNewStation();
			}catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
		
		cancel.setOnMouseClicked(e -> {
			try {
				AdminBikeHandler adminBike = new AdminBikeHandler(this.stage, Configs.ADMIN_BIKE_SCREEN_PATH);
				adminBike.setBController(new BikeController());
				adminBike.setPreviousScreen(this);
				adminBike.show();
			} catch (IOException e1) {
                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }	
		});
	}
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	
	public void requestToAddNewStation() throws IOException, SQLException {

		if (checkNull() == true) {
			Bike bike = new Bike();

			bike.setBatterySpace(Integer.parseInt(batterySpace.getText()));

			bike.setBikeCode(bikeCode.getText());
			bike.setCost(Integer.parseInt(cost.getText()));
			bike.setImage(image.getText());
			bike.setLicencePlate(licensePlate.getText());
			bike.setLoadCycles(Integer.parseInt(loadCycles.getText()));
			
			bike.setManuafacturingDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			bike.setName(name.getText());
			bike.setProducer(producer.getText());
			bike.setStatus(1);
			bike.setTimeRemaining(Integer.parseInt(timeRemainting.getText()));
			bike.setWeight(Integer.parseInt(weight.getText()));
			
			bike.setBikeType(findType(type.getValue()));
			bike.setStation(findStation(stationCbb.getValue()));
			
			BikeController bikecontroller = new BikeController();
			bikecontroller.addNewBike(bike);
			PopupScreen.success("Thành công");
			try {
				LOGGER.info("Station button clicked");
				AdminBikeHandler adminBike = new AdminBikeHandler(this.stage, Configs.ADMIN_BIKE_SCREEN_PATH);
				adminBike.setBController(new StationController());
				adminBike.show();
			} catch (IOException  e1) {
				e1.printStackTrace();
            }	
			// station id type id
			
			
		} else {
			
			PopupScreen.error("hãy điền đủ các trường");
		}
	
		System.out.println(date.getValue());
		System.out.println("addd");
	}
	private Station findStation(String stationName) {
		for (Station object : stationList) {
			if (object.getName() == stationName) {
				return object;
			}
		}
		return null;
	}
	private BikeType findType(String typeName) {
		for (BikeType object : BikeTypeList) {
			if (object.getTypeName() == typeName) {
				return object;
			}
		}
		return null;
	}
	
	
	
	
	private boolean checkNull() {
		if (name.getText() == null || name.getText() == "") {
			return false;
		}
		if (image.getText() == null || image.getText() == "") {
			return false;
		}
		if (weight.getText() == null || weight.getText() == "") {
			return false;
		}
		if (cost.getText() == null || cost.getText() == "") {
			return false;
		}
		if (timeRemainting.getText() == null || timeRemainting.getText() == "") {
			return false;
		}
		if (bikeCode.getText() == null || bikeCode.getText() == "") {
			return false;
		}
		if (producer.getText() == null || producer.getText() == "") {
			return false;
		}
		if (loadCycles.getText() == null || loadCycles.getText() == "") {
			return false;
		}
		if (batterySpace.getText() == null || batterySpace.getText() == "") {
			return false;
		}
		if (licensePlate.getText() == null || licensePlate.getText() == "") {
			return false;
		}
		if (type.getValue() == null || type.getValue() == "") {
			return false;
		}
		if (stationCbb.getValue() == null || stationCbb.getValue() == "") {
			return false;
		}
		return true;
	}
//	public void setImage() {
//		// fix image path caused by fxml
//        File file1 = new File(Configs.IMAGE_PATH + "/" + "Logo.png");
//        Image img1 = new Image(file1.toURI().toString());
//        aimsImage.setImage(img1);
//	}
	
}
