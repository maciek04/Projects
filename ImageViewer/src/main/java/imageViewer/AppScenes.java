package imageViewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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
import javafx.util.Duration;

public class AppScenes {

	Stage stage;
	ImageView imv;
	final ImagePos imagePos = new ImagePos();
	final FileChooser fileChooser = new FileChooser();
	List<File> list = null;
	ImageView imvList[] = null;
	Button autoSlide;
	VBox root = new VBox();
	StackPane box = new StackPane();
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = (screenSize.getWidth() * 0.8);
	double height = (screenSize.getHeight() * 0.8);
	boolean autoSlideRun = false;

	AppScenes(Stage stage) {
		this.stage = stage;
		imagePos.setPos(0);
	}

	public Scene showFile(final List<File> list) {

		if (!(list == null)) {
			this.list = list;
			int s = 0;
			this.root = new VBox();
		}
		;
		stage.setTitle("ImageViewer");
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
				setList(list);
				loadImvList();
				stage.setScene(showFile(list));
			}

		});

		if (list == null) {
			System.out.println(list);
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
			// imv.setFitWidth(width);
			// imv.setFitHeight(height);
			// imv.setPreserveRatio(true);
			// imv.setSmooth(true);
			// imv.setCache(true);
			box.getChildren().add(makeImv(imv));
		}

		Button prev = new Button();
		Image prevImg = new Image(getClass().getResourceAsStream("/images/Previous.png"));
		imv = buttonSize(prevImg);
		prev.setGraphic(imv);
		if(this.list == null){ prev.setDisable(true);}else{prev.setDisable(false);}
		prev.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				imagePos.setAutoSliderRun(false);
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
		if(this.list == null){ next.setDisable(true);}else{next.setDisable(false);}
		next.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				imagePos.setAutoSliderRun(false);
				if (imagePos.getPos() == imagePos.getMaxpos()) {
					imagePos.setPos(0);
				} else {
					imagePos.setPos(imagePos.getPos() + 1);
				}
				stage.setScene(showFile(list));

			}
		});
		autoSlide = new Button("AutoSlide");

		autoSlide.setOnAction(e -> {
			autoSlide();
		});

		Button exit = new Button();
		Image exitImg = new Image(getClass().getResourceAsStream("/images/exit.png"));
		imv = buttonSize(exitImg);
		exit.setGraphic(imv);
		exit.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				imagePos.setAutoSliderRun(false);
				System.exit(0);
			}
		});
		HBox menuList = new HBox(20);
		menuList.getChildren().add(prev);
		menuList.getChildren().add(next);
		if (list != null) {
			menuList.getChildren().add(chooseFile);
		}
		// menuList.getChildren().add(autoSlide);
		menuList.getChildren().add(exit);
		// menuList.spacingProperty(width/4);
		menuList.setAlignment(Pos.CENTER);
		root.getChildren().add(menuList);

		if (!(imvList == null)) {
			System.out.print(imvList[0]);
			System.out.println(imvList[1]);
		}
		return new Scene(root, width, height + 100);
	}

	public void loadImvList() {

		ImageView slides[] = new ImageView[this.list.size()];
		int pos = 0;
		for (File img : this.list) {
			slides[pos] = new ImageView(new Image("file:" + img));
			pos++;

		}
		this.imvList = slides;
	}

	public void autoSlide() {
		System.out.println("AutoSlider incoming");

		// this.root = new VBox();
		SequentialTransition slideshow = new SequentialTransition();
		this.box.getChildren().removeAll();
		System.out.println(imvList);
		for (ImageView slide : this.imvList) {
			slide = makeImv(slide);
			SequentialTransition sequentialTransition = new SequentialTransition();

			FadeTransition fadeIn = getFadeTransition(slide, 0.0, 1.0, 2000);
			// PauseTransition stayOn = new PauseTransition(Duration.millis(0));
			// FadeTransition fadeOut = getFadeTransition(slide, 0.0, 1.0,
			// 2000);

			sequentialTransition.getChildren().addAll(fadeIn/* , fadeOut */);
			slide.setOpacity(0);
			this.box.getChildren().add(slide);
			slideshow.getChildren().add(sequentialTransition);

		}
		if (!autoSlideRun) {
			autoSlideRun = true;
			slideshow.play();
		} else {
			autoSlideRun = false;
			slideshow.stop();
		}
	}

	public FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue,
			int durationInMilliseconds) {

		FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
		ft.setFromValue(fromValue);
		ft.setToValue(toValue);

		return ft;

	}

	public void setList(List<File> list) {
		this.list = list;
	}

	public ImageView makeImv(ImageView imv) {
		imv.setFitWidth(width);
		imv.setFitHeight(height);
		imv.setPreserveRatio(true);
		imv.setSmooth(true);
		imv.setCache(true);
		return imv;
	}

	public ImageView buttonSize(Image img) {
		ImageView imv = new ImageView(img);
		imv.setFitHeight(32);
		imv.setFitHeight(32);
		imv.setPreserveRatio(true);
		return imv;
	}

}
