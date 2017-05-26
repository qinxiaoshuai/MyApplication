package com.example.mydream.mydreamapp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mydream.mydreamapp.R;
import com.example.mydream.mydreamapp.appconfig.ConfigDataMethod;
import com.example.mydream.mydreamapp.tool.toast.AppMsg;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ConfigDataMethod.bmobInit(getApplicationContext());

        //默认用户名密码
//        Intent intent = getIntent();
//        //从Intent当中根据key取得value
//        if (intent != null) {
//            String name = intent.getStringExtra("name");
//            String pwd = intent.getStringExtra("pwd");
//            _emailText.setText(name);
//            _passwordText.setText(pwd);
//        }
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess(email, password);
                        // onLoginFailed();
                        progressDialog.dismiss();

                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(String name, String pwd) {
        _loginButton.setEnabled(true);
        selectUser(name, pwd);

    }

    public void onLoginFailed() {
        _loginButton.setEnabled(true);
        ConfigDataMethod.toastShow(this, "Please enter the correct mailbox and password!", AppMsg.STYLE_GULES);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || email.length() < 3) {
            _emailText.setError("enter a valid name");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    //登录
    public void selectUser(final String name, final String pwd) {
        BmobUser bu2 = new BmobUser();
        bu2.setUsername(name);
        bu2.setPassword(pwd);
        bu2.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                isUser(e, name, pwd);
            }
        });
    }

    private void isUser(BmobException e, String name, String pwd) {
        if (e == null) {

            ConfigDataMethod.toastShow(this, "登录成功，正在跳转", AppMsg.STYLE_ALERT);
            Intent intent = new Intent();
            intent.setClass(this, FragmentActivity.class);
            startActivity(intent);
            finish();
            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
        } else {

            ConfigDataMethod.toastShow(this, "登录失败,请检查用户名和密码", AppMsg.STYLE_GULES);

        }


    }

}
