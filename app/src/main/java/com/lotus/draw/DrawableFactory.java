package com.lotus.draw;

import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by Thl on 2017/3/27.
 */

public class DrawableFactory {

    private DrawableFactory(){}
    private static DrawableFactory mInstance;
    public static DrawableFactory getInstance(){
        synchronized (DrawableFactory.class){
            if (mInstance==null){
                synchronized (DrawableFactory.class){
                    if (mInstance==null){
                        mInstance=new DrawableFactory();
                    }
                }
            }
        }
        return mInstance;
    }

    public GradientDrawable createShape(int strokeWidth,int strokeColor,int roundRadius,int fillColor){
        GradientDrawable shape = new GradientDrawable();//创建drawable
        shape.setColor(fillColor);
        shape.setCornerRadius(roundRadius);
        shape.setStroke(strokeWidth, strokeColor);
        return shape;
    }
    public GradientDrawable createShape(int roundRadius,int fillColor){
        GradientDrawable shape = new GradientDrawable();//创建drawable
        shape.setColor(fillColor);
        shape.setCornerRadius(roundRadius);
        shape.setStroke(1, fillColor);
        return shape;
    }

    public StateListDrawable  createStateListDrawable(int roundRadius,int normalColor,int pressedColor,int disableColor){
        StateListDrawable stateListDrawable = new StateListDrawable ();//创建drawable
        stateListDrawable.addState(new int[] { android.R.attr.state_pressed },  createShape(roundRadius,pressedColor));
        // View.ENABLED_FOCUSED_STATE_SET
        stateListDrawable.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, createShape(roundRadius,normalColor));
        // View.ENABLED_STATE_SET
        stateListDrawable.addState(new int[] { -android.R.attr.state_enabled },  createShape(roundRadius,disableColor));
        // View.FOCUSED_STATE_SET
        stateListDrawable.addState(new int[] { android.R.attr.state_focused },  createShape(roundRadius,normalColor));
        // View.WINDOW_FOCUSED_STATE_SET
        stateListDrawable.addState(new int[] { android.R.attr.state_window_focused },  createShape(roundRadius,normalColor));
        // View.EMPTY_STATE_SET
        stateListDrawable.addState(new int[] {}, createShape(roundRadius,normalColor));
        return stateListDrawable;
    }

    /** 对TextView设置不同状态时其文字颜色。 */
    private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}
