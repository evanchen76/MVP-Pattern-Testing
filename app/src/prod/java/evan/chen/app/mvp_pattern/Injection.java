package evan.chen.app.mvp_pattern;

import android.util.Log;

import evan.chen.app.mvp_pattern.repository.UserRepository;
import evan.chen.app.mvp_pattern.repository.UserRepositoryImpl;
import evan.chen.app.mvp_pattern.service.LoginServiceApi;
import evan.chen.app.mvp_pattern.service.LoginServiceApiImpl;

/**
 * Created by 3758 on 2017/6/2.
 */

public class Injection {

    public static UserRepository provideUserRepository() {
        Log.d("test", "provideUserRepository_prod");
        LoginServiceApi loginServiceApi = new LoginServiceApiImpl();
        UserRepository userRepository = new UserRepositoryImpl(loginServiceApi);
        return userRepository;
    }
}
