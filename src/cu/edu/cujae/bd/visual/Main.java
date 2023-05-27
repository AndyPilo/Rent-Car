package cu.edu.cujae.bd.visual;

import cu.edu.cujae.bd.visual.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Model.getInstanse().getViewFactory().showLoginWindow();
		
	}
}
