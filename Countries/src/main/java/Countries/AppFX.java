package Countries;

import Scenes.AppScenes;
import javafx.application.Application;
import javafx.scene.Scene;
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
		System.out.println(score.toString());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
