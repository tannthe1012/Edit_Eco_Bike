import java.io.IOException;
import java.sql.SQLException;

import entity.Bike;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.BikeService;
import utils.Configs;
import views.screen.AddNewBikeHandler;
import views.screen.AdminBikeHandler;
import views.screen.BikeInfoScreenHandler;
import views.screen.HomeScreenHandler;
import views.screen.StationHandler;

public class App extends Application {

	@FXML
	ImageView logo;

	@Override
	public void start(Stage primaryStage) {
		try {

			// initialize the scene
			StackPane root = (StackPane) FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

			// Load splash screen with fade in effect
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);

			// Finish splash with fade out effect
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);

			// After fade in, start fade out
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			// After fade out, load actual content
			fadeOut.setOnFinished((e) -> {
				try {
					HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
					homeHandler.setScreenTitle("Home Screen");
					homeHandler.setImage();
					homeHandler.show();
//					BikeService bikeService = new BikeService();
//					Bike bike;
//					try {
//						bike = bikeService.getBikeById(1);
//						BikeInfoScreenHandler homeHandler = new BikeInfoScreenHandler(primaryStage, Configs.BIKE_INFO_PATH, bike);
//						homeHandler.setScreenTitle("Home Screen");
//						homeHandler.show();
////						homeHandler.setImage();
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
	
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
