package model;

public class ActivableItem {
	private boolean active;
	public ActivableItem(boolean active ) {
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
