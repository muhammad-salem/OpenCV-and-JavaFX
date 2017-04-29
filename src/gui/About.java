package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class About implements Initializable {

	@FXML private WebView web;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		web.getEngine().loadContent(
				  "<br><h1 > Mohammed Salem</h1>"
				+ "<br><h2> Aya-Allah Ramzy</h2>"
				+ "<br><h3> Ahmed Alla</h3>"
				+ "<br><h3> Mohammed Abd El 3leem</h3>"
				+ "<br><h3> Islam 3essa</h3>");

	}

}
