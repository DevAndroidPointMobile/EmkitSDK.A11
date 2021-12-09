package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Handler;

import device.common.IExGpioInterruptCallback;

public abstract class ExGpioInterruptCallback {
    private final Object mLock = new Object();
    private CallbackWrapper mStub;
    private Handler mHandler;

    @UnsupportedAppUsage
    public ExGpioInterruptCallback() {}

    @UnsupportedAppUsage
    public ExGpioInterruptCallback(Handler handler) {
        mHandler = handler;
    }

    @UnsupportedAppUsage
    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    @UnsupportedAppUsage
    public IExGpioInterruptCallback getInterruptCallback() {
        synchronized (mLock) {
            if (mStub == null) {
                mStub = new CallbackWrapper(this);
            }
            return mStub;
        }
    }

    @UnsupportedAppUsage
    public IExGpioInterruptCallback releaseCallback() {
        synchronized (mLock) {
            final CallbackWrapper oldStub = mStub;
            if (oldStub != null) {
                oldStub.releaseCallback();
                mStub = null;
            }
            return oldStub;
        }
    }

    @UnsupportedAppUsage
    public void onChanged(int gpio) {}

    @UnsupportedAppUsage
    public final void dispatchChangedState(int gpio) {
        if (mHandler == null) {
            onChanged(gpio);
        } else {
            mHandler.post(new NotificationRunnable(gpio));
        }
    }

    private final class NotificationRunnable implements Runnable {
        private final int mGpio;
        @UnsupportedAppUsage
        public NotificationRunnable(int gpio) {
            mGpio = gpio;
        }
        @Override
        public void run() {
            ExGpioInterruptCallback.this.onChanged(mGpio);
        }
    }

    private static final class CallbackWrapper extends IExGpioInterruptCallback.Stub {
        private ExGpioInterruptCallback mCallback;
        @UnsupportedAppUsage
        public CallbackWrapper(ExGpioInterruptCallback callback) {
            mCallback = callback;
        }
        @Override
        @UnsupportedAppUsage
        public void onChanged(int gpio) {
            ExGpioInterruptCallback callback = mCallback;
            if (callback != null) {
                callback.dispatchChangedState(gpio);
            }
        }
        @UnsupportedAppUsage
        public void releaseCallback() {
            mCallback = null;
        }
    }
}
