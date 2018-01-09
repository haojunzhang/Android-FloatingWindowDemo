package idv.haojun.floatingwindowdemo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_alert_dialog).setOnClickListener(this);
        findViewById(R.id.tv_custom_dialog_by_alert_dialog).setOnClickListener(this);
        findViewById(R.id.tv_custom_dialog_by_activity).setOnClickListener(this);
        findViewById(R.id.tv_popup_window).setOnClickListener(this);
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
            case R.id.tv_custom_dialog_by_activity:
                startActivity(new Intent(this, CustomDialogActivity.class));
                break;
            case R.id.tv_popup_window:
                popupWindow(v);
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

    private void popupWindow(View clickView) {
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

}
