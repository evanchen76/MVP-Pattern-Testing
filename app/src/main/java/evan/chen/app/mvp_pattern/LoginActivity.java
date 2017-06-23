package evan.chen.app.mvp_pattern;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import evan.chen.app.mvp_pattern.Model.User;
import evan.chen.app.mvp_pattern.repository.UserRepository;

public class LoginActivity extends AppCompatActivity implements LoginContract.ILoginView  {
    private LoginPresenter presenter;

    private Button loginButton;
    private EditText loginId;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserRepository userRepository = Injection.provideUserRepository();
        presenter = new LoginPresenter(userRepository, this);

        this.loginButton = (Button) this.findViewById(R.id.logn);
        this.loginId = (EditText) findViewById(R.id.login_id);
        this.password = (EditText) findViewById(R.id.password);

        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(loginId.getText().toString(), password.getText().toString());
            }
        });
    }

    public void setPresenter(LoginPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void onLoginSuccess(User user) {
        Intent i=new Intent(this,LoginSuccessActivity.class);
        i.putExtra("USER_NAME", user.userName);

        this.startActivity(i);
    }

    @Override
    public void onLoginFail() {
        this.password.setText("");

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Login Fail");
        alertDialog.setMessage("Invalid id or password");
        alertDialog.setCancelable(true);
        DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        };

        alertDialog.setNeutralButton("OK", OkClick);

        alertDialog.show();
    }
}
