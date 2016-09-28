package Scenes;

import Countries.Continents;
import Countries.Countries;
import Countries.Score;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameScene extends Scene{

	public GameScene(VBox root, double width, double height, Score score) {
		super(root, width, height);
		

		Countries game = new Countries(Continents.EUROPE);
		String correctCapital = game.correctCapital;
	
	
	Text question = new Text(game.getQuestion());
	root.getChildren().add(question);
	
	
	HBox hbox = new HBox();
	int i = 0;
	for(String x : game.opts ){
		final Button btn = new Button(x);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 
            public void handle(ActionEvent event) {
                if( correctCapital == btn.getText()){
                	score.setScore(5); ;
                	//win = true;
                	//result = ("Poprawna odpowiedü");
                	System.out.println(score.getScore());
                	
                	
                }
                else{
                	result = ("B≥Ídna odpowiedü");
                	score.setScore(-3);
                	System.out.println(score.getScore());
                }
            }
        });
		hbox.getChildren().add(btn);
		i++;
	}
	root.getChildren().add(hbox);
	System.out.println(score.getScore());
	Text resultText = new Text(result);
	System.out.println(win);
	System.out.println(score);
	
	
	Label showScore = new Label();
	showScore.textProperty().bind(score.scoreProperty().asString());
	root.getChildren().add(showScore);

	
	
	}
	
	

}
