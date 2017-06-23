package evan.chen.app.mvp_pattern.service;

import android.os.Handler;

import evan.chen.app.mvp_pattern.Model.Login;
import evan.chen.app.mvp_pattern.Model.User;

/**
 * Created by 3758 on 2017/6/2.
 */

public class LoginServiceApiImpl implements LoginServiceApi {
    @Override
    public void loginApi(final Login login, final LoginServiceCallback callback) {
        // Simulate Login check
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ( login.userId.equals("evan") && login.password.equals("123")){

                    User user = new User();
                    user.userId = "evan";
                    user.userName = "evan chen";
                    user.email = "evan.chen@mail.com";

                    callback.onLoginResult(true, user);
                }else{
                    callback.onLoginResult(false, null);
                }
            }
        }, 1000);
    }
}
