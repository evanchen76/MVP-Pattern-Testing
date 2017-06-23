package evan.chen.app.mvp_pattern;

import android.support.annotation.NonNull;

import evan.chen.app.mvp_pattern.Model.Login;
import evan.chen.app.mvp_pattern.Model.User;
import evan.chen.app.mvp_pattern.repository.UserRepository;

/**
 * Created by Evan on 2017/5/11.
 */

public class LoginPresenter implements LoginContract.ILoginPresenter{
    private final LoginContract.ILoginView mView;
    private final UserRepository mUserRepository;

    LoginPresenter(@NonNull UserRepository userRepository, @NonNull LoginContract.ILoginView view) {
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void login(String id, String password) {

        Login login = new Login();
        login.userId = id;
        login.password = password;

        mUserRepository.userLogin(login, new UserRepository.LoginCallback() {
            @Override
            public void onLoginResult(boolean isSuccess, User user) {
                if ( isSuccess ){
                    mView.onLoginSuccess(user);
                }else{
                    mView.onLoginFail();
                }
            }
        });
    }
}
