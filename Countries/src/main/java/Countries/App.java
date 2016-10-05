package Countries;

import Scenes.GameScene;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
	
	Score score = new Score();
	public boolean win = false;
	public String result ;
	public static boolean run = true;
	public final static boolean wait = false;
	
	@Override
    public void start(Stage primaryStage) {
		primaryStage.setTitle("Kraje i stolice");
		
		
		try {
			VBox root = new VBox();
				
			while(run){
				while(!wait){
					GameScene scene = new GameScene(root,400,400, score, wait);
				
					primaryStage.setScene(scene);
					
					primaryStage.show();
					System.out.println("DUPA");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
}