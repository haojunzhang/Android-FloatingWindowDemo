package idv.haojun.floatingwindowdemo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_alert_dialog).setOnClickListener(this);
        findViewById(R.id.tv_custom_dialog_by_alert_dialog).setOnClickListener(this);
        findViewById(R.id.tv_custom_dialog_ios).setOnClickListener(this);
        findViewById(R.id.tv_custom_dialog_by_activity).setOnClickListener(this);
        findViewById(R.id.tv_popup_window).setOnClickListener(this);
        findViewById(R.id.tv_floating_window).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alert_dialog:
                alertDialog();
                break;
            case R.id.tv_custom_dialog_by_alert_dialog:
                customDialogByAlertDialog();
                break;
            case R.id.tv_custom_dialog_ios:
                customDialogIOS();
                break;
            case R.id.tv_custom_dialog_by_activity:
                startActivity(new Intent(this, CustomDialogActivity.class));
                break;
            case R.id.tv_popup_window:
                popupWindow();
                break;
            case R.id.tv_floating_window:
                checkDrawOverlayPermission();
                break;
        }
    }

    private void alertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.message)
                .setPositiveButton(R.string.good, null)
                .setNegativeButton(R.string.bad, null)
                .setNeutralButton(R.string.fine, null)
                .show();
    }

    private void customDialogByAlertDialog() {
        @SuppressLint("InflateParams") final View v = LayoutInflater.from(this).inflate(R.layout.custom_dialog_by_alert_dialog, null);
        final AlertDialog ad = new AlertDialog.Builder(this)
                .setView(v)
                .show();

        v.findViewById(R.id.iv_custom_dialog_by_alert_dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ad != null)
                    ad.dismiss();
            }
        });

        v.findViewById(R.id.tv_custom_dialog_by_alert_dialog_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ad != null)
                    ad.dismiss();
            }
        });

        v.findViewById(R.id.tv_custom_dialog_by_alert_dialog_bad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ad != null)
                    ad.dismiss();
            }
        });
    }

    private void customDialogIOS() {
        new IOSAlertDialog(this).show();
    }

    private void popupWindow() {
        final View v = LayoutInflater.from(this).inflate(R.layout.popup_window, null);
        final PopupWindow popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.findViewById(R.id.ll_popup_window_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        v.findViewById(R.id.ll_popup_window_look).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        v.findViewById(R.id.ll_popup_window_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.AppTheme_PopWindowDefault);
        popupWindow.showAtLocation(findViewById(R.id.rl_main), Gravity.CENTER, 0, 0);
    }

    public final static int Overlay_REQUEST_CODE = 251;

    public void checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, Overlay_REQUEST_CODE);
            } else {
                openFloatingWindow();
            }
        } else {
            openFloatingWindow();
        }
    }

    private void openFloatingWindow() {
        Intent intent = new Intent(this, FloatingWindow.class);
        stopService(intent);
        startService(intent);
    }
}
