package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.opencv.core.Core;

public class App extends Application {

	static Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		stage = primaryStage;
		AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("index.fxml"));
		Scene scene = new Scene(anchorPane);
		
		stage.setTitle("MuliT TracKing");
		stage.setScene(scene);
//			//***  set Icon Image For Desktop  ***//
		stage.getIcons().add(new Image("img/photos.png"));
		stage.getIcons().add(new Image("img/photos.png"));
		stage.getIcons().add(new Image("img/photos.png"));
		stage.getIcons().add(new Image("img/photofilters.png"));
		
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}
