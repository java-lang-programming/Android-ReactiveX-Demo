package java_lang_programming.com.android_reactivex_demo.eventbus;

/**
 * RxBusProvider for RxBus
 */
public class RxBusProvider {
    private static final RxBus BUS = new RxBus();

    private RxBusProvider() {
        // No instances.
    }

    public static RxBus getInstance() {
        return BUS;
    }
}
