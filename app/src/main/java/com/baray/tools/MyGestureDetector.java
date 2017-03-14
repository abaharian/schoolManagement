package com.baray.tools;

import android.view.MotionEvent;

/**
 * Created by Akram on 3/14/2017.
 */
public interface MyGestureDetector {
        public boolean onTouchEvent(MotionEvent ev);

        public boolean isScaling();

        public boolean isDragging();

        public void setOnGestureListener(OnGestureListener listener);
}
