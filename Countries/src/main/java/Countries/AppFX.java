package Countries;

import Scenes.AppScenes;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppFX extends Application {
	
	private Stage stage;
	Score score = new Score();

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		AppScenes appScene = new AppScenes(stage, score);
		Scene scene = appScene.startScene();
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
