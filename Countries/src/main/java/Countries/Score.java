package Countries;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score {

	private IntegerProperty score = new SimpleIntegerProperty(0);
	
	public Score(){
		
	}

	public int getScore() {
		return score.get();
	}

	public void setScore(int diff) {
		this.score.set(score.get() + diff);
	}
	
	public IntegerProperty scoreProperty(){
		return score;
	}
	
	
}
