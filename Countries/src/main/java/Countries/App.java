package Countries;



import Scenes.GameScene;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
	
	
	Score score = new Score();
	public boolean win = false;
	public String result ;
	
	
	@Override
    public void start(Stage primaryStage) {
		primaryStage.setTitle("Kraje i stolice");
		
		
		try {
			VBox root = new VBox();
			
						
			GameScene scene = new GameScene(root,400,400, score);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

//	public static void main(String[] args) {
//		launch(args);
//	}
}