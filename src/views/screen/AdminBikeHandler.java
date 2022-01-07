package views.screen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exception.ViewCartException;
import controller.AdminController;
import controller.BikeController;
import controller.HomeController;
import controller.PaymentController;
import controller.StationController;
import entity.Bike;
import entity.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

public class AdminBikeHandler extends BaseScreenHandler implements Initializable {
	
	public static Logger LOGGER = Utils.getLogger(AdminBikeHandler.class.getName());
	
	@FXML
	private ImageView aimsImage;
	
	@FXML
	private Button btnsearch;
	
	@FXML
	private Button btnAddNewBike;
	
	@FXML
	private HBox hboxMedia;
	
	@FXML
	private VBox vboxMedia1;
	
	@FXML
	private VBox vboxMedia2;
	
	@FXML
	private VBox vboxMedia3;
	
	private List homeItems;
	
	public AdminBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new HomeController());
		try {
			List medium = getBController().getListAllBike();
			this.homeItems = new ArrayList<>();
			for (Object object : medium) {
                Bike bike = (Bike)object;
                BikeDetailAdminHandler m1 = new BikeDetailAdminHandler(Configs.BIKE_DETAIL_ADMIN, bike, this);
                this.homeItems.add(m1);
			}
			addBikeHome(this.homeItems);
		}catch (SQLException | IOException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
		
		aimsImage.setOnMouseClicked(e -> {
            
        });
		
		btnAddNewBike.setOnMouseClicked(e -> {
			AddNewBikeHandler adminAddBike;
			try {
				adminAddBike = new AddNewBikeHandler(this.stage, Configs.ADD_NEW_BIKE_PATH);
				adminAddBike.setBController(new BikeController());
				adminAddBike.setPreviousScreen(this);
				adminAddBike.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        });
		
//		btnAddNewBike.setOnMouseClicked(e -> {
////				AddNewStationHandler addNewStation;
////				try {
////					LOGGER.info("User clicked to add new station page");
////					addNewStation = new AddNewStationHandler(this.stage, Configs.ADD_NEW_STATION_PATH);
////					addNewStation.setBController(new StationController());
////					addNewStation.requestToViewAddNewStation(this);
////				} catch (IOException | SQLException e1) {
////	                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
////	            }	
//			
//			A giveBackResultHandler = new GiveBackResultHandler(this.stage, Configs.GIVE_BACK_RESULT, "GIVE BACK SUCCESSFUL!", "The payment is equal to the previous deposit. Thank you" );
//			giveBackResultHandler.setPreviousScreen(this);
//			giveBackResultHandler.setHomeScreenHandler(homeScreenHandler);
//			giveBackResultHandler.setScreenTitle("Result Screen");
//			giveBackResultHandler.show();
//		});
	}
	
	public void setImage() {
		// fix image path caused by fxml
        File file1 = new File(Configs.IMAGE_PATH + "/" + "Logo.png");
        Image img1 = new Image(file1.toURI().toString());
        aimsImage.setImage(img1);
	}
	
	public void addBikeHome(List items) {
		ArrayList bikeItems = (ArrayList)((ArrayList) items).clone();
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while(!bikeItems.isEmpty()){
            hboxMedia.getChildren().forEach(node -> {
                int vid = hboxMedia.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while(vBox.getChildren().size()<3 && !bikeItems.isEmpty()){
                    BikeDetailAdminHandler media = (BikeDetailAdminHandler) bikeItems.get(0);
                    vBox.getChildren().add(media.getContent());
                    bikeItems.remove(media);
                }
            });
            return;
        }
	}
	
	public void requestToViewAdminStation(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Admin Station Screen");
//		addStationHome(this.homeItems);
		show();
	}


}
