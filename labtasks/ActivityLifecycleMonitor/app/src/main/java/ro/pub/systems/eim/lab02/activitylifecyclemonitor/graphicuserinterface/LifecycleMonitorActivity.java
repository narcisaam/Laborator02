package ro.pub.systems.eim.lab02.activitylifecyclemonitor.graphicuserinterface;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

import ro.pub.systems.eim.lab02.activitylifecyclemonitor.R;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Constants;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Utilities;

public class LifecycleMonitorActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
            EditText passwordEditText = (EditText)findViewById(R.id.password_edit_text);
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.ok_button_content))) {
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                View popupContent;
                if (Utilities.allowAccess(getApplicationContext(), username, password)) {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_success, null);
                } else {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_fail, null);
                }
                final PopupWindow popupWindow = new PopupWindow(popupContent, android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
                Button dismissButton = (Button)popupContent.findViewById(R.id.dismiss_button);
                dismissButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.cancel_button_content))) {
                usernameEditText.setText(getResources().getText(R.string.empty));
                passwordEditText.setText(getResources().getText(R.string.empty));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lifecycle_monitor);
        if (savedInstanceState != null)
            Log.println (Log.DEBUG, "activitylifecycle", "OnCreate: was launched before");
        else
            Log.println (Log.DEBUG, "activitylifecycle", "OnCreate: first launch");
        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);


    }

    protected void onRestart() {
        super.onRestart();
        Log.println (Log.DEBUG, "activitylifecycle", "OnRestart");
    }
    protected void onStart() {
        super.onStart();
        Log.println (Log.DEBUG, "activitylifecycle", "onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.println (Log.DEBUG, "activitylifecycle", "OnResume");
    }

    protected void onPause() {
        super.onPause();
        Log.println (Log.DEBUG, "activitylifecycle", "OnPause");
    }


    protected void onStop() {
        super.onStop();
        Log.println (Log.DEBUG, "activitylifecycle", "onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.println (Log.DEBUG, "activitylifecycle", "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        CheckBox rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        savedInstanceState.putBoolean(Constants.REMEMBER_ME_CHECKBOX, rememberMeCheckBox.isChecked());
        EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
        savedInstanceState.putString(Constants.USERNAME_EDIT_TEXT, usernameEditText.getText().toString());
        EditText passwordEditText = (EditText)findViewById(R.id.password_edit_text);
        savedInstanceState.putString(Constants.PASSWORD_EDIT_TEXT, passwordEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CheckBox rememberMeCheckBox = (CheckBox)findViewById(R.id.remember_me_checkbox);
        if (rememberMeCheckBox.isChecked()) {
            EditText usernameEditText = (EditText) findViewById(R.id.username_edit_text);
            if (savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT) != null) {
                usernameEditText.setText(savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT));
            }

            EditText passwordEditText = (EditText) findViewById(R.id.password_edit_text);
            if (savedInstanceState.getString(Constants.PASSWORD_EDIT_TEXT) != null) {
                passwordEditText.setText(savedInstanceState.getString(Constants.PASSWORD_EDIT_TEXT));
            }
        }
    }
}