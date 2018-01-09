package idv.haojun.floatingwindowdemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_alert_dialog).setOnClickListener(this);
        findViewById(R.id.tv_custom_alert_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alert_dialog:
                alertDialog();
                break;
            case R.id.tv_custom_alert_dialog:
                customAlertDialog();
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

    private void customAlertDialog() {

    }


}
