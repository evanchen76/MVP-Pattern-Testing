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
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;

public class LoginPresenterUnitTest {

    @Mock
    private UserRepository mUserRepository;

    @Mock
    private LoginContract.ILoginView mLoginVIew;

    @Captor
    private ArgumentCaptor<UserRepository.LoginCallback> mLoginCallbackCaptor;

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void login_success_should_call_viewOnLoginSuccess() throws Exception {
        // arrange
        LoginPresenter loginPresenter = new LoginPresenter(mUserRepository, mLoginVIew);

        String loginId = "evan";
        String password = "123";

        Login login = new Login();
        login.userId = loginId;
        login.password = password;

        User user = new User();
        user.userId = "evan";
        user.userName = "evan chen";
        user.email = "evan.chen@mail.com";

        // Act
        loginPresenter.login(loginId, password);

        // Assert
        verify(mUserRepository).userLogin(refEq(login), mLoginCallbackCaptor.capture());

        // Act
        mLoginCallbackCaptor.getValue().onLoginResult(true, user);

        // Assert
        verify(mLoginVIew).onLoginSuccess(eq(user));
    }
}
