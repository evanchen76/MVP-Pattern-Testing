package evan.chen.app.mvp_pattern;

import android.util.Log;

/**
 * Created by 3758 on 2017/6/2.
 */

public class Injection {

    public static UserRepository provideUserRepository() {
        //Log.d("test", "provideUserRepository");
        LoginServiceApi loginServiceApi = new FakeLoginServiceApi();
        UserRepository userRepository = new UserRepositoryImpl(loginServiceApi);
        //Log.d("test", "provideUserRepository_2");
        return userRepository;
    }
}
