package cu.edu.cujae.bd.visual;

import java.io.IOException;
import java.sql.SQLException;

import cu.edu.cujae.bd.visual.models.Model;
import cu.edu.cujae.bd.visual.views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private static Stage stage;
	
	public static void main(String[] args) throws SQLException {
		launch(args);
	}
	@Override
	/*public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root));	
		primaryStage.setResizable(false);
		primaryStage.show();
	
	}*/

	public void start(Stage stage) throws Exception {
		Model.getInstanse().getViewFactory().showLoginWindow();
	}

	public void changeScene(String fxml) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		
		stage.getScene().setRoot(pane);
	}
	
	public void changeSceneTam(String fxml,int width,int height) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stage.setScene(new Scene(pane,width,height));
	
	}
	
	public static Stage getStage() {
		return stage;
	}

}
