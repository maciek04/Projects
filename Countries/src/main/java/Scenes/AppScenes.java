package Scenes;

import java.util.ArrayList;
import java.util.List;

import Countries.AppFX;
import Countries.Continents;
import Countries.Countries;
import Countries.GameState;
import Countries.Score;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppScenes extends AppFX{

	private Stage stage;
	private Score score;
	public List<Integer> used;
	
	public AppScenes(Stage stage, Score score){
		this.stage = stage;
		this.score = score;
		this.used = new ArrayList<Integer>();
		
	}

	public Scene startScene(){
		
		VBox root = new VBox();
		Button startButton = new Button("Zacznij grê");
		startButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				score.setGameState(GameState.PLAY);
				stage.setScene(gameScene());
			}
		});
		root.getChildren().add(startButton);
		return new Scene(root,600,400);
	}
	
	public Scene gameScene(){
		
		
		final Countries game = new Countries(Continents.EUROPE, used, score );

		System.out.println(score.getGameState());
		if(score.getGameState().equals(GameState.END)){
			VBox root = new VBox();
			Text text = new Text();
			text.setText("Gra zakoñczona. Twój wynik: " + score.getScore());
			
			Button startButton = new Button("Zacznij now¹ grê");
			startButton.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event){
					score.setGameState(GameState.START);
					score.resetScore();
					stage.setScene(gameScene());
				}
			});
			root.getChildren().add(text);
			
			Button exit = new Button("Koniec gry");
			root.getChildren().add(exit);
			exit.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
				    System.exit(0);
				}
		    });
			//root.getChildren().add(startButton);
			
			
			return new Scene(root,600,400);
	
		}else{
		
		VBox root = new VBox();
		Text question = new Text(game.getQuestion());
		root.getChildren().add(question);
		root.setAlignment(Pos.CENTER);
		
		HBox hbox = new HBox();
		
		for(String x : game.opts ){
			final Button btn = new Button(x);
			btn.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	                if( game.correctCapital == btn.getText()){
	                	score.setScore(5);
	                	stage.setScene(gameScene());
	                }else{	             
	                	score.setScore(-10);
	                }
	            }
	        });
			hbox.getChildren().add(btn);
		}
		root.getChildren().add(hbox);
		
		System.out.println(score.getScore());
	
		Label showScore = new Label();
		showScore.textProperty().bind(score.scoreProperty().asString());
		root.getChildren().add(showScore);
	
		Button exit = new Button("Koniec gry");
		root.getChildren().add(exit);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
			    System.exit(0);
			}
	    });
		return new Scene(root,600,400);
		}
	}
	
	public Scene endScene(){
		VBox root = new VBox();
		Text text = new Text();
		text.setText("Gra zakoñczona. Twój wynik: " + score.getScore());
		
		Button startButton = new Button("Zacznij now¹ grê");
		startButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				score.resetScore();
				stage.setScene(gameScene());
			}
		});
		root.getChildren().add(startButton);
		return new Scene(root,600,400);
	}
}
