package evan.chen.app.mvp_pattern;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static junit.framework.Assert.assertNotNull;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=25)
public class LoginResultActivityUnitTest {

    private  LoginSuccessActivity activity;

    @Before
    public void setupLoginPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        activity = Robolectric.setupActivity(LoginSuccessActivity.class);
    }

    @Test
    public void view_ui_should_not_null() throws Exception {

        TextView results = (TextView) activity.findViewById(R.id.login_result);

        // Assert
        assertNotNull(results);
    }
}
