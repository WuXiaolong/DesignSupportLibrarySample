package com.wuxiaolong.designsupportlibrarysample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
 * Created by 吴小龙同學
 * on 2015/11/16
 * 官网文档：https://material.google.com/
 * 个人博客：http://wuxiaolong.me/
 * 公众号：吴小龙同学
 */
public class LoginActivity extends BaseActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextInputLayout emailTextInput, passwordTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar("登录");
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        emailTextInput = (TextInputLayout) findViewById(R.id.emailTextInput);
        passwordTextInput = (TextInputLayout) findViewById(R.id.passwordTextInput);
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
        emailTextInput.setErrorEnabled(false);
        passwordTextInput.setErrorEnabled(false);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailTextInput.setError("请输入邮箱");
            return;
        } else if (!isEmailValid(email)) {
            emailTextInput.setError("请输入有效的邮箱");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordTextInput.setError("请输入密码");
            return;
        } else if (!isPasswordValid(password)) {
            passwordTextInput.setError("密码过短");
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

