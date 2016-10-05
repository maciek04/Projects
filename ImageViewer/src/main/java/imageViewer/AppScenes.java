package imageViewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppScenes {

	Stage stage;
	ImageView imv;
	final ImagePos imagePos = new ImagePos();
	final FileChooser fileChooser = new FileChooser();

	AppScenes(Stage stage) {
		this.stage = stage;
		imagePos.setPos(0);
	}

	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	double width = (screenSize.getWidth() * 0.8);
	double height = (screenSize.getHeight() * 0.8);

	public Scene showFile(final List<File> list) {

		stage.setTitle("ImageViewer");
		VBox root = new VBox();
		StackPane box = new StackPane();
		box.setMinHeight(height);
		box.setMinWidth(width);
		root.getChildren().add(box);

		Button chooseFile = new Button();
		Image chooseFileImg = new Image(getClass().getResourceAsStream("/images/Open.png"));
		imv = buttonSize(chooseFileImg);
		chooseFile.setGraphic(imv);
		chooseFile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				List<File> list = fileChooser.showOpenMultipleDialog(stage);
				System.out.println(list.get(imagePos.getPos()));
				imagePos.setMaxpos(list.size() - 1);
				stage.setScene(showFile(list));
			}

		});

		if (list == null) {
			VBox start = new VBox();
			Text text = new Text();
			text.setFont(new Font(25));
			text.setFill(Color.BLUE);
			text.setTextAlignment(TextAlignment.CENTER);
			text.setText("Wybierz pliki do wyœwietlenia");

			start.getChildren().add(text);
			start.getChildren().add(chooseFile);
			start.setAlignment(Pos.CENTER);
			box.getChildren().add(start);

		} else {
			Image im = new Image("file:" + list.get(imagePos.getPos()).toString());
			imv = new ImageView();
			imv.setImage(im);
			imv.setFitWidth(width);
			imv.setFitHeight(height);
			imv.setPreserveRatio(true);
			imv.setSmooth(true);
			imv.setCache(true);
			box.getChildren().add(imv);
		}

		// root.getChildren().add(chooseFile);

		Button prev = new Button();
		Image prevImg = new Image(getClass().getResourceAsStream("/images/Previous.png"));
		imv = buttonSize(prevImg);
		prev.setGraphic(imv);
		prev.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (imagePos.getPos() == 0) {
					imagePos.setPos(imagePos.getMaxpos());
				} else {
					imagePos.setPos(imagePos.getPos() - 1);
				}
				stage.setScene(showFile(list));

			}
		});
		Button next = new Button();
		Image nextImg = new Image(getClass().getResourceAsStream("/images/Next.png"));
		imv = buttonSize(nextImg);
		next.setGraphic(imv);
		next.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (imagePos.getPos() == imagePos.getMaxpos()) {
					imagePos.setPos(0);
				} else {
					imagePos.setPos(imagePos.getPos() + 1);
				}
				stage.setScene(showFile(list));

			}
		});

		Button exit = new Button();
		Image exitImg = new Image(getClass().getResourceAsStream("/images/exit.png"));
		imv = buttonSize(exitImg);
		exit.setGraphic(imv);
		exit.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		HBox menuList = new HBox(20);
		menuList.getChildren().add(prev);
		menuList.getChildren().add(next);
		if (list != null) {
			menuList.getChildren().add(chooseFile);
		}
		menuList.getChildren().add(exit);
		// menuList.spacingProperty(width/4);
		menuList.setAlignment(Pos.CENTER);
		root.getChildren().add(menuList);

		return new Scene(root, width, height + 100);
	}
	
	public ImageView buttonSize(Image img){
		ImageView imv = new ImageView(img);
		imv.setFitHeight(32);
		imv.setFitHeight(32);
		imv.setPreserveRatio(true);
		return imv;
	}

}
