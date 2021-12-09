package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Handler;

public abstract class DecodeStateCallback {
    private final Object mLock = new Object();
    private Transport mTransport;
    Handler mHandler;

    @UnsupportedAppUsage
    public DecodeStateCallback() {
    }

    @UnsupportedAppUsage
    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    @UnsupportedAppUsage
    public DecodeStateCallback(Handler handler) {
        mHandler = handler;
    }

    @UnsupportedAppUsage
    public IDecodeStateCallback getDecodeStateCallback() {
        synchronized (mLock) {
            if (mTransport == null) {
                mTransport = new Transport(this);
            }
            return mTransport;
        }
    }

    @UnsupportedAppUsage
    public IDecodeStateCallback releaseDecodeStateCallback() {
        synchronized (mLock) {
            final Transport oldTransport = mTransport;
            if (oldTransport != null) {
                oldTransport.releaseDecodeStateCallback();
                mTransport = null;
            }
            return oldTransport;
        }
    }

    @UnsupportedAppUsage
    public boolean deliverSelfNotification() {
        return false;
    }

    /**
     * Called when state of scanner is chanaged.
     *
     * @param state changed state of scanner
     * <pre>
     * public class ScanConst {
     *  public static final int STATE_INIT = 0;
     *  public static final int STATE_TURNING_ON = 1;
     *  public static final int STATE_ON = 2;
     *  public static final int STATE_TURNING_OFF = 3;
     *  public static final int STATE_OFF = 4;
     *  public static final int STATE_LOCK_BY_CAMERA = 5;
     *  public static final int STATE_UNLOCK_BY_CAMERA = 6;
     *  public static final int STATE_CAMERA_ON = 7;
     *  public static final int STATE_CAMERA_OFF = 8;
     *  public static final int STATE_SUSPENDING = 9;
     *  public static final int STATE_SUSPEND_DONE = 10;
     *  public static final int STATE_RESUMING = 11;
     *  public static final int STATE_RESUME_DONE = 12;
     * }
     * <pre>
     */
    @UnsupportedAppUsage
    public void onChangedState(int state) {
    }

    @UnsupportedAppUsage
    public final void dispatchChangedState(int state) {
        if (mHandler == null) {
            onChangedState(state);
        } else {
            mHandler.post(new NotificationRunnable(state));
        }
    }

    private final class NotificationRunnable implements Runnable {
        private final int mState;
        @UnsupportedAppUsage
        public NotificationRunnable(int state) {
            mState = state;
        }
        @Override
        public void run() {
            DecodeStateCallback.this.onChangedState(mState);
        }
    }

    private static final class Transport extends IDecodeStateCallback.Stub {
        private DecodeStateCallback mDecodeStateCallback;
        @UnsupportedAppUsage
        public Transport(DecodeStateCallback decodeStateCallback) {
            mDecodeStateCallback = decodeStateCallback;
        }
        @Override
        @UnsupportedAppUsage
        public void onChangedState(int state) {
            DecodeStateCallback decodeStateCallback = mDecodeStateCallback;
            if (decodeStateCallback != null) {
                decodeStateCallback.dispatchChangedState(state);
            }
        }
        @UnsupportedAppUsage
        public void releaseDecodeStateCallback() {
            mDecodeStateCallback = null;
        }
    }
}
