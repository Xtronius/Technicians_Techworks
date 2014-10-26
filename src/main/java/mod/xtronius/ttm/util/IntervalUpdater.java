package mod.xtronius.ttm.util;

public class IntervalUpdater {
	
	private final ITimer obj;
	private boolean canUpdate = true;
	private int updateTimes = 1;
	private float delay = 0.01F;
	private int timer = 0;
	
	/**Takes in the ITimer in-which the timer interval method will be called*/
	public IntervalUpdater(ITimer obj) {
		this.obj = obj;
	}

	/**Updates the timer-(Note: Must be called my ITimer obj)*/
	public void updateTimer() {
		if(this.canUpdate()) {
			this.incrementTimer();
			if(timer == getUpdateInterval()) 
				intervalUpdate();
		}
	}
	
	/**Updates when the timer ticks hit the timer interval*/
	private void intervalUpdate() {
		for(int i = 0; i < this.getUpdateTimes(); i++)
			this.obj.intervalUpdate(this.getCurrentTick());
	}
	
	/**The interval(seconds) between each call of the intervalUpdate method*/
	public int getUpdateInterval() { 
		return (int) (this.getDelay() * 20);
	}
	
	public int getCurrentTick() {
		return this.timer;
	}
	
	/**Ticks the timer*/
	private void incrementTimer() { 
		if(timer <= getUpdateInterval()) timer++;
		else timer = 0; 
	}
	
	/**Gets the delay*/
	public float getDelay() {
		return this.delay;
	}
	
	/**Sets the delay*/
	public void setDelay(float delay) {
		this.delay = delay;
	}
	
	/**Can the timer tick*/
	public boolean canUpdate() {
		return canUpdate;
	}

	/**Sets whether the timer can tick*/
	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}

	/** Gets the number of timers the intervalUpdate method will be called per occation that the timer ticks hits the timer interval*/
	public int getUpdateTimes() {
		return updateTimes;
	}

	/** Sets the number of timers the intervalUpdate method will be called per occation that the timer ticks hits the timer interval*/
	public void setUpdateTimes(int updateTimes) {
		this.updateTimes = updateTimes;
	}
}
