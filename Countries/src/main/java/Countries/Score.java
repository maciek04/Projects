package Countries;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score {

	private IntegerProperty score = new SimpleIntegerProperty(0);
	private GameState gameState;
	
	public Score(){
		setGameState(GameState.START);
	}

	public int getScore() {
		return score.get();
	}

	public void setScore(int diff) {
		this.score.set(score.get() + diff);
	}
	
	public void resetScore(){
		this.score.set(0);
		setGameState(GameState.PLAY);
	}
	public IntegerProperty scoreProperty(){
		return score;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	
}
