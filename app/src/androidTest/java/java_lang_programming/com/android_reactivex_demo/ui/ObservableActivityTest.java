package java_lang_programming.com.android_reactivex_demo.ui;

import android.support.test.espresso.ViewAction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java_lang_programming.com.android_reactivex_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by msuzuki on 2016/09/04.
 * https://github.com/googlesamples/android-testing/blob/master/ui/espresso/BasicSample/app/build.gradle
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ObservableActivityTest {

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<ObservableActivity> mActivityRule = new ActivityTestRule<>(
            ObservableActivity.class);

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
//        onView(withId(R.id.result))
//                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.observable_just_j7)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.result)).check(matches(withText("3")));
    }
}
