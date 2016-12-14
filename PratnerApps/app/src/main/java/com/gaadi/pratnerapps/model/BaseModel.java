package com.gaadi.pratnerapps.model;

import android.graphics.Typeface;

/**
 * Created by Mushareb Ali on 23/5/16.
 */
public class BaseModel {

    public int colorPrimary;
    public int colorPrimaryDark;
    public int colorAccent;
    public int textColorHint;
    public int textColor;
    public int secondTextColor;
    public int colorControlNormal;
    public int colorControlActivated;
    public int colorControlHighlight;
    public int backgroundColor;
    public Typeface textTypeface;


    public int getColorControlActivated() {
        return colorControlActivated;
    }

    public void setColorControlActivated(int colorControlActivated) {
        this.colorControlActivated = colorControlActivated;
    }

    public int getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public void setColorPrimaryDark(int colorPrimaryDark) {
        this.colorPrimaryDark = colorPrimaryDark;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public int getTextColorHint() {
        return textColorHint;
    }

    public void setTextColorHint(int textColorHint) {
        this.textColorHint = textColorHint;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getSecondTextColor() {
        return secondTextColor;
    }

    public void setSecondTextColor(int secondTextColor) {
        this.secondTextColor = secondTextColor;
    }

    public int getColorControlNormal() {
        return colorControlNormal;
    }

    public void setColorControlNormal(int colorControlNormal) {
        this.colorControlNormal = colorControlNormal;
    }

    public int getColorControlHighlight() {
        return colorControlHighlight;
    }

    public void setColorControlHighlight(int colorControlHighlight) {
        this.colorControlHighlight = colorControlHighlight;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Typeface getTextTypeface() {
        return textTypeface;
    }

    public void setTextTypeface(Typeface textTypeface) {
        this.textTypeface = textTypeface;
    }


}
