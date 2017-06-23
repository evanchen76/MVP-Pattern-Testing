package evan.chen.app.mvp_pattern;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import evan.chen.app.mvp_pattern.Model.Login;
import evan.chen.app.mvp_pattern.Model.User;
import evan.chen.app.mvp_pattern.repository.UserRepository;
import evan.chen.app.mvp_pattern.repository.UserRepositoryImpl;
import evan.chen.app.mvp_pattern.service.LoginServiceApi;

import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserRepositoryUnitTest {

    private UserRepository mUserRepository;

    @Mock
    private LoginServiceApi mLoginServiceApi;

    @Mock
    private UserRepository.LoginCallback loginCallback;

    @Captor
    private ArgumentCaptor<LoginServiceApi.LoginServiceCallback> mLoginServiceCallbackCaptor;

    @Mock
    private LoginPresenter mLoginPresenter;

    @Before
    public void setupUserRepository() {

        MockitoAnnotations.initMocks(this);

        mUserRepository = new UserRepositoryImpl(mLoginServiceApi);
    }

    @Test
    public void loginRepository_to_loginServiceApi_to_onLoginResult() throws Exception {
        String loginId = "Evan";
        String password = "123";

        Login login = new Login();
        login.userId = loginId;
        login.password = password;

        User user = new User();
        user.userId = "evan";
        user.userName = "evan chen";
        user.email = "evan.chen@mail.com";

        //Act
        mUserRepository.userLogin(login, loginCallback);

        //Assert
        verify(mLoginServiceApi).loginApi(eq(login), mLoginServiceCallbackCaptor.capture());

        //Act
        mLoginServiceCallbackCaptor.getValue().onLoginResult(true, user);

        //Assert
        verify(loginCallback).onLoginResult(true, user);
    }
}
