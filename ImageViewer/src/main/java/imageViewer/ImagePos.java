package imageViewer;

public class ImagePos {
	private int pos;
	private int maxpos;
	private boolean autoSliderRun;
	
	ImagePos(){
		
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getMaxpos() {
		return maxpos;
	}

	public void setMaxpos(int maxpos) {
		this.maxpos = maxpos;
	}

	public boolean isAutoSliderRun() {
		return autoSliderRun;
	}

	public void setAutoSliderRun(boolean autoSliderRun) {
		this.autoSliderRun = autoSliderRun;
	}

}
