package com.gaadi.pratnerapps.events;

/**
 * Created by lakshaygirdhar on 11/4/16.
 */
public class CheckChangedEvent extends Object {
    private boolean checked;
    private int position;

    public CheckChangedEvent(boolean checked, int position) {
        this.checked = checked;
        this.position = position;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
