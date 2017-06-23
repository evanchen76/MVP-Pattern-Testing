package evan.chen.app.mvp_pattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        TextView loginResult = (TextView) findViewById(R.id.login_result);
        loginResult.setText("LoginUser:" + userName);
    }
}
