package evan.chen.app.mvp_pattern;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;

import evan.chen.app.mvp_pattern.Model.User;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=25)
public class LoginActiityUnitTest {

    @Mock
    private LoginPresenter mPresenter;

    private  LoginActivity activity;

    @Before
    public void setupLoginPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        //ctivity = Robolectric.setupActivity(LoginActivity.class);

        activity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .start()
                .resume()
                .get();

        activity.setPresenter(mPresenter);
    }

    @Test
    public void all_view_should_not_null() throws Exception {

        Button button = (Button) activity.findViewById(R.id.logn);
        EditText loginId = (EditText) activity.findViewById(R.id.login_id);
        EditText password = (EditText) activity.findViewById(R.id.password);

        assertNotNull(button);
        assertNotNull(loginId);
        assertNotNull(password);
    }

    @Test
    public void loginButton_click_to_presenter() throws Exception {

        String loginId = "Evan";
        String passWord = "123";

        Button button = (Button) activity.findViewById(R.id.logn);
        EditText loginIdEditText = (EditText) activity.findViewById(R.id.login_id);
        EditText passwordEditText = (EditText) activity.findViewById(R.id.password);

        loginIdEditText.setText(loginId);
        passwordEditText.setText(passWord);

        //Act
        button.performClick();

        //Assert
        verify(mPresenter).login(eq(loginId), eq(passWord));
    }

    @Test
    public void login_success_should_start_SuccessActivity() throws Exception {
        //Arrange
        User user = new User();
        user.userId = "evan";
        user.userName = "evan chen";
        user.email = "evan.chen@mail.com";

        //Act
        activity.onLoginSuccess(user);

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent nextIntent = shadowActivity.getNextStartedActivity();

        //Assert
        assertEquals(nextIntent.getComponent().getClassName(), LoginSuccessActivity.class.getName());
        assertEquals(1, nextIntent.getExtras().size());
        assertEquals(user.userName, nextIntent.getExtras().getString("USER_NAME"));
    }

    @Test
    public void login_fail_should_clear_password_and_show_dialog() throws Exception {

        EditText password = (EditText) activity.findViewById(R.id.password);

        Dialog dialog = ShadowAlertDialog.getLatestDialog();

        assertNull(dialog);

        //Act

        activity.onLoginFail();
        dialog = ShadowAlertDialog.getLatestDialog();

        //Assert
        assertNotNull(dialog);
        assertTrue(dialog.isShowing());

        assertEquals(password.getText().toString(), "");
    }
}
