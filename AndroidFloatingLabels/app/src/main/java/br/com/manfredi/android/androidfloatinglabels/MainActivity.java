package br.com.manfredi.android.androidfloatinglabels;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPassword;

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;

    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnSignUp = (Button) findViewById(R.id.btn_sign_up);

        etName.addTextChangedListener(new MyTextWatcher(etName));
        etEmail.addTextChangedListener(new MyTextWatcher(etEmail));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));

    }

    public void btnSignUpOnClick(View view) {
        if (!isValidName()) {
            return;
        }

        if (!isValidEmail()) {
            return;
        }

        if (!isValidPassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), R.string.success_msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidName() {

        if (etName.getText().toString().trim().isEmpty()) {

            inputLayoutName.setError(getString(R.string.error_msg_name_empty));
            requestFocus(etName);
            return false;
        } else {

            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean isValidEmail() {

        String email = etEmail.getText().toString().trim();

        if (email.isEmpty()) {

            inputLayoutEmail.setError(getString(R.string.error_msg_email_empty));
            requestFocus(etEmail);
            return false;
        } else if (!isValidEmail(email)) {

            inputLayoutEmail.setError(getString(R.string.error_msg_email_invalid));
            requestFocus(etEmail);
            return false;
        } else {

            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean isValidPassword() {

        if (etPassword.getText().toString().trim().isEmpty()) {

            inputLayoutPassword.setError(getString(R.string.error_msg_password_empty));
            requestFocus(etPassword);
            return false;
        } else {

            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_name:
                    isValidName();
                    break;
                case R.id.et_email:
                    isValidEmail();
                    break;
                case R.id.et_password:
                    isValidPassword();
                    break;
            }
        }
    }

}
