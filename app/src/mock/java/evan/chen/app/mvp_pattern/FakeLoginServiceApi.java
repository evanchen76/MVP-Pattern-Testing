package evan.chen.app.mvp_pattern;

/**
 * Created by 3758 on 2017/6/2.
 */

public class FakeLoginServiceApi implements LoginServiceApi {

    @Override
    public void login(String id, String password, LoginServiceCallback callback) {
        callback.onLoginResult(true);
    }
}
