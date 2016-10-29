package imageViewer;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Test {

	public static void main(String[] args) {
		Rectangle rect = new Rectangle(100, 40, 100, 100);
		rect.setArcHeight(50);
		rect.setArcWidth(50);
		rect.setFill(Color.VIOLET);

		final Duration SEC_2 = Duration.millis(2000);
		final Duration SEC_3 = Duration.millis(3000);

		PauseTransition pt = new PauseTransition(Duration.millis(1000));
		FadeTransition ft = new FadeTransition(SEC_3);
		ft.setFromValue(1.0f);
		ft.setToValue(0.3f);
		ft.setCycleCount(2f);
		ft.setAutoReverse(true);
		TranslateTransition tt = new TranslateTransition(SEC_2);
		tt.setFromX(-100f);
		tt.setToX(100f);
		tt.setCycleCount(2f);
		tt.setAutoReverse(true);
		RotateTransition rt = new RotateTransition(SEC_3);
		rt.setByAngle(180f);
		rt.setCycleCount(4f);
		rt.setAutoReverse(true);
		ScaleTransition st = new ScaleTransition(SEC_2);
		st.setByX(1.5f);
		st.setByY(1.5f);
		st.setCycleCount(2f);
		st.setAutoReverse(true);

		SequentialTransition seqT = new SequentialTransition(rect, pt, ft, tt, rt, st);
		seqT.play();
	}

}
