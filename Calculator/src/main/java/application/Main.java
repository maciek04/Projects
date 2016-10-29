package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static Calculator calc = new Calculator();

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.getChildren().add(calcScene());
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public VBox calcScene() {
		VBox vbox = new VBox();

//		Label result = new Label();
//		result.setId("result");
//		result.setAlignment(Pos.CENTER_RIGHT);
//		result.setPadding(new Insets(0, 10, 0, 0));
//		result.setText(calc.getResult().toString());
//		result.textProperty().bind(calc.getDPResult().asString());

		Label next = new Label();
		next.setId("next");
		next.setAlignment(Pos.CENTER_RIGHT);
		next.setPadding(new Insets(0, 10, 0, 0));
		next.textProperty().bind(calc.getDPNum1().asString());
		
		Label signB = new Label();
		signB.setId("task");
		signB.setAlignment(Pos.CENTER_RIGHT);
		signB.textProperty().bind(calc.getSignS());
//		if(!(calc.getSign()==null)){
//		signB.setText(calc.getSign().toString());
//		}
		
		
		
		GridPane grid = new GridPane();
		grid.setMinSize(200, 200);

		Button btCE = new Button("CE");
		btCE.setOnAction(e -> calc.callCE());
		Button btC = new Button("C");
		btC.setOnAction(e -> calc.callC());
		Button btMultiply = new Button("x");
		btMultiply.setOnAction(e -> {
			calc.setSign(Sign.MULTIPLY);
		});
		Button btDivide = new Button("/");
		btDivide.setOnAction(e -> {
			calc.setSign(Sign.DIVIDE);
		});
		Button btAdd = new Button("+");
		btAdd.setOnAction(e -> {
			calc.setSign(Sign.ADD);
		});
		Button btSub = new Button("-");
		btSub.setOnAction(e -> {
			calc.setSign(Sign.DEDUCT);
		});
		Button equals = new Button("=");
		equals.setOnAction(e -> calc.equals());
		Button btMins = new Button("+-");
		btMins.setOnAction(e -> calc.multiplyByMinus());
		Button btDecimal = new Button(",");
		btDecimal.setOnAction(e -> calc.setDecimal(true));
		for(int i = 0; i<10;i++){
			final int j = i;
			String btName = new Integer(j).toString();
			Button bt0 = new Button(btName);
			bt0.setOnAction(e -> {
				calc.setNum1(j);
				System.out.println(calc.getNum1());
			});
			int x = 0;
			int y = 0;
			switch(i){
			case 0:
				x = 2;
				y = 4;
				break;
			case 1:
				x = 1;
				y = 3;
				break;	
			case 2:
				x = 2;
				y = 3;
				break;
			case 3:
				x = 3;
				y = 3;
				break;
			case 4:
				x = 1;
				y = 2;
				break;	
			case 5:
				x = 2;
				y = 2;
				break;
			case 6:
				x = 3;
				y = 2;
				break;
			case 7:
				x = 1;
				y = 1;
				break;	
			case 8:
				x = 2;
				y = 1;
				break;
			case 9:
				x = 3;
				y = 1;
				break;
			}
			grid.add(bt0, x, y);
			
		}

		grid.add(btCE, 1, 0);
		grid.add(btC, 2, 0);
		grid.add(btAdd, 4, 3);
		grid.add(btSub, 4, 2);
		grid.add(btDivide, 4, 1);
		grid.add(btMultiply, 4, 0);
		grid.add(btMins, 1, 4);
		grid.add(btDecimal, 3, 4);
		grid.add(equals, 4, 4);

		vbox.getChildren().addAll(signB, next, grid);
		return vbox;
	}
}
