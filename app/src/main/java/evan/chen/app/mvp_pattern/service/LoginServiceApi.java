package evan.chen.app.mvp_pattern.service;

import evan.chen.app.mvp_pattern.Model.Login;
import evan.chen.app.mvp_pattern.Model.User;

/**
 * Created by 3758 on 2017/6/2.
 */

public interface LoginServiceApi {
    interface LoginServiceCallback {
        void onLoginResult(boolean isSuccess, User user);
    }

    void loginApi(Login login, LoginServiceCallback callback);
}
