package idv.haojun.floatingwindowdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class MyiOSAlertDialog extends Dialog implements DialogInterface {

    private String mTitle;
    private String mMessage;
    private String mPositiveButtonText;
    private String mNegativeButtonText;
    private DialogInterface.OnClickListener mPositiveOnClickListener;
    private DialogInterface.OnClickListener mNegativeOnClickListener;

    public MyiOSAlertDialog(@NonNull Context context, String mTitle, String mMessage, String mPositiveButtonText, String mNegativeButtonText, OnClickListener mPositiveOnClickListener, OnClickListener mNegativeOnClickListener) {
        super(context);
        this.mTitle = mTitle;
        this.mMessage = mMessage;
        this.mPositiveButtonText = mPositiveButtonText;
        this.mNegativeButtonText = mNegativeButtonText;
        this.mPositiveOnClickListener = mPositiveOnClickListener;
        this.mNegativeOnClickListener = mNegativeOnClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("---", "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ios);
        getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        TextView tv_title = findViewById(R.id.tv_dialog_ios_title);
        TextView tv_message = findViewById(R.id.tv_dialog_ios_message);
        TextView tv_positive_button = findViewById(R.id.tv_dialog_ios_positive_button);
        TextView tv_negative_button = findViewById(R.id.tv_dialog_ios_negative_button);

        if (mTitle != null) {
            tv_title.setText(mTitle);
            tv_title.setVisibility(View.VISIBLE);
        }

        if (mMessage != null) {
            tv_message.setText(mMessage);
            tv_message.setVisibility(View.VISIBLE);
        }

        if (mPositiveButtonText != null) {
            tv_positive_button.setText(mPositiveButtonText);
            tv_positive_button.setVisibility(View.VISIBLE);
            tv_positive_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPositiveOnClickListener != null) {
                        mPositiveOnClickListener.onClick(MyiOSAlertDialog.this, 0);
                    }
                    dismiss();
                }
            });
        }

        if (mNegativeButtonText != null) {
            tv_negative_button.setText(mNegativeButtonText);
            tv_negative_button.setVisibility(View.VISIBLE);
            tv_negative_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNegativeOnClickListener != null) {
                        mNegativeOnClickListener.onClick(MyiOSAlertDialog.this, 0);
                    }
                    dismiss();
                }
            });
        }
    }

    @Override
    public void show() {
        super.show();
        Log.d("---", "show");
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static class Builder {

        private Context context;
        private String mTitle;
        private String mMessage;
        private String mPositiveButtonText;
        private String mNegativeButtonText;
        private DialogInterface.OnClickListener mPositiveOnClickListener;
        private DialogInterface.OnClickListener mNegativeOnClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(int id) {
            return setTitle(context.getString(id));
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(int id) {
            return setMessage(context.getString(id));
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setPositiveButton(int id, DialogInterface.OnClickListener onClickListener) {
            return setPositiveButton(context.getString(id), onClickListener);
        }

        public Builder setPositiveButton(String text, DialogInterface.OnClickListener onClickListener) {
            this.mPositiveButtonText = text;
            this.mPositiveOnClickListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(int id, DialogInterface.OnClickListener onClickListener) {
            return setNegativeButton(context.getString(id), onClickListener);
        }

        public Builder setNegativeButton(String text, DialogInterface.OnClickListener onClickListener) {
            this.mNegativeButtonText = text;
            this.mNegativeOnClickListener = onClickListener;
            return this;
        }

        public MyiOSAlertDialog show() {
            MyiOSAlertDialog dialog = new MyiOSAlertDialog(
                    context,
                    mTitle,
                    mMessage,
                    mPositiveButtonText,
                    mNegativeButtonText,
                    mPositiveOnClickListener,
                    mNegativeOnClickListener
            );
            dialog.show();
            return dialog;
        }
    }
}
