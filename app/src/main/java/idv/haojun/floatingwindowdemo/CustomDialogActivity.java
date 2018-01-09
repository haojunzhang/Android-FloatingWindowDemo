package idv.haojun.floatingwindowdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class CustomDialogActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_dialog);

        findViewById(R.id.iv_custom_dialog_by_activity_close).setOnClickListener(this);
        findViewById(R.id.tv_custom_dialog_by_activity_bad).setOnClickListener(this);
        findViewById(R.id.tv_custom_dialog_by_activity_good).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
