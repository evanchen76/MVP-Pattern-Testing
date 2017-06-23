package evan.chen.app.mvp_pattern.repository;

import android.support.annotation.NonNull;

import evan.chen.app.mvp_pattern.Model.Login;
import evan.chen.app.mvp_pattern.Model.User;
import evan.chen.app.mvp_pattern.service.LoginServiceApi;

/**
 * Created by 3758 on 2017/6/2.
 */

public class UserRepositoryImpl implements UserRepository {
    private final LoginServiceApi mLoginServiceApi;

    public UserRepositoryImpl(@NonNull LoginServiceApi loginServiceApi) {
        this.mLoginServiceApi = loginServiceApi;
    }

    @Override
    public void userLogin(Login login, final LoginCallback callback) {
        mLoginServiceApi.loginApi(login, new LoginServiceApi.LoginServiceCallback() {
            @Override
            public void onLoginResult(boolean isSuccess, User user) {
                callback.onLoginResult(isSuccess, user);
            }
        });
    }
}
