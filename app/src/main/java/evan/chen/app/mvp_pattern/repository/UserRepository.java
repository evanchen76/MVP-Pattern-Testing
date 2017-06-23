package evan.chen.app.mvp_pattern.repository;

import evan.chen.app.mvp_pattern.Model.Login;
import evan.chen.app.mvp_pattern.Model.User;

/**
 * Created by 3758 on 2017/6/2.
 */

public interface UserRepository {

    interface LoginCallback {

        void onLoginResult(boolean isSuccess, User user);
    }

    void userLogin(Login login, final LoginCallback callback);
}
