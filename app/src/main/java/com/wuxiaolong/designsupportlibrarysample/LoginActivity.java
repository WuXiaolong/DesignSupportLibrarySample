package com.wuxiaolong.designsupportlibrarysample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wuxiaolong.androidutils.library.LogUtil;


/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        loginView = findViewById(R.id.loginView);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_DONE) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }


    private void attemptLogin() {
        LogUtil.d("attemptLogin");
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("请输入邮箱");
            showSnackbar(loginView, "请输入邮箱");
            return;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("请输入有效的邮箱");
            showSnackbar(loginView, "请输入有效的邮箱");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("请输入密码");
            showSnackbar(loginView, "请输入密码");
            return;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("密码过短");
            showSnackbar(loginView, "密码过短");
            return;
        }


        mActivity.startActivity(new Intent(mActivity, MainActivity.class));
        finish();

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


}

