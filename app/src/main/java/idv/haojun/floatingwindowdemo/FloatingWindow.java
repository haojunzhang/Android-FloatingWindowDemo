package idv.haojun.floatingwindowdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FloatingWindow extends Service {

    private WindowManager windowManager;
    
    private View view;
    
    private WindowManager.LayoutParams windowsParams;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // init View
        view = LayoutInflater.from(this).inflate(R.layout.floating_window, null);

        view.findViewById(R.id.iv_floating_window_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf();
            }
        });

        view.findViewById(R.id.tv_floating_window_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf();
            }
        });

        view.findViewById(R.id.tv_floating_window_bad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf();
            }
        });


        windowsParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,//WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,//WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.TYPE_PHONE,
                //WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, // Not displaying keyboard on bg activity's EditText
                //WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, //Not work with EditText on keyboard
                PixelFormat.TRANSLUCENT);


        windowsParams.gravity = Gravity.CENTER;
        windowManager.addView(view, windowsParams);

        view.setOnTouchListener(new View.OnTouchListener() {

            private int initX;
            private int initY;
            private float initTouchX;
            private float initTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (isViewInBounds(view, (int) (event.getRawX()), (int) (event.getRawY()))) {
                    getFocus();
                } else {
                    cancelFocus();
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initX = windowsParams.x;
                        initY = windowsParams.y;
                        initTouchX = event.getRawX();
                        initTouchY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        windowsParams.x = initX + (int) (event.getRawX() - initTouchX);
                        windowsParams.y = initY + (int) (event.getRawY() - initTouchY);
                        windowManager.updateViewLayout(view, windowsParams);
                        break;
                }
                return false;
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (view != null) {
            windowManager.removeView(view);
        }
        super.onDestroy();
    }

    private boolean isViewInBounds(View view, int x, int y) {
        Rect outRect = new Rect();
        int[] location = new int[2];
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }

    private boolean isFocusing = true;
    
    private void getFocus() {
        if (!isFocusing) {
            windowsParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
            windowManager.updateViewLayout(view, windowsParams);
            isFocusing = true;
        }
    }

    private void cancelFocus() {
        if (isFocusing) {
            windowsParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
            windowManager.updateViewLayout(view, windowsParams);
            isFocusing = false;
        }
    }


}
