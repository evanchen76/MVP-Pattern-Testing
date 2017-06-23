package evan.chen.app.mvp_pattern;

import evan.chen.app.mvp_pattern.Model.User;

/**
 * Created by Evan on 2017/5/11.
 */

public interface LoginContract {
    interface ILoginView  {
        void onLoginSuccess(User user);

        void onLoginFail();
    }

    interface ILoginPresenter {
        void login(String id, String password);
    }
}
