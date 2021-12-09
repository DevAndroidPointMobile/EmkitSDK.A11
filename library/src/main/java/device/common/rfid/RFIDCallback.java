package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Handler;
import device.common.rfid.RecvPacket;

public abstract class RFIDCallback {
    private final Object mLock = new Object();
    private Transport mTransport;
    Handler mHandler;

    @UnsupportedAppUsage
    public RFIDCallback() {
    }

    @UnsupportedAppUsage
    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    @UnsupportedAppUsage
    public RFIDCallback(Handler handler) {
        mHandler = handler;
    }

    @UnsupportedAppUsage
    public IRFIDCallback getRFIDCallback() {
        synchronized (mLock) {
            if (mTransport == null) {
                mTransport = new Transport(this);
            }
            return mTransport;
        }
    }

    @UnsupportedAppUsage
    public IRFIDCallback RFIDCallback() {
        synchronized (mLock) {
            final Transport oldTransport = mTransport;
            if (oldTransport != null) {
                oldTransport.releaseRFIDCallback();
                mTransport = null;
            }
            return oldTransport;
        }
    }

    @UnsupportedAppUsage
    public boolean deliverSelfNotification() {
        return false;
    }

    @UnsupportedAppUsage
    public void onNotifyDataWriteFail() {
    }

    /*
    @UnsupportedAppUsage
    public void onBtDeviceConnected() {
    }

    @UnsupportedAppUsage
    public void onBtDeviceConnectFail() {
    }
    */

    @UnsupportedAppUsage
    public void onNotifyReceivedPacket(RecvPacket recvPacket) {
    }

    @UnsupportedAppUsage
    public void onNotifyChangedState(int state) {
    }

    @UnsupportedAppUsage
    public final void dispatchonNotifyDataWriteFail(){
        if (mHandler == null) {
            onNotifyDataWriteFail();
        } else {
            mHandler.post(new NotificationRunnable_onNotifyDataWriteFail());
        }
    }

    /*
    @UnsupportedAppUsage
    public final void dispatchonBtDeviceConnected() {
        if (mHandler == null) {
            onBtDeviceConnected();
        } else {
            mHandler.post(new NotificationRunnable_onBtDeviceConnected());
        }
    }

    @UnsupportedAppUsage
    public final void dispatchonBtDeviceConnectFail() {
        if (mHandler == null) {
            onBtDeviceConnectFail();
        } else {
            mHandler.post(new NotificationRunnable_onBtDeviceConnectFail());
        }
    }
    */

    @UnsupportedAppUsage
    public final void dispatchonNotifyReceivedPacket(RecvPacket recvPacket) {
        if (mHandler == null) {
            onNotifyReceivedPacket(recvPacket);
        } else {
            mHandler.post(new NotificationRunnable_onNotifyReceivedPacket(recvPacket));
        }
    }

    @UnsupportedAppUsage
    public final void dispatchonNotifyChangedState(int state) {
        if (mHandler == null) {
            onNotifyChangedState(state);
        } else {
            mHandler.post(new NotificationRunnable_onNotifyChangedState(state));
        }
    }

    private final class NotificationRunnable_onNotifyDataWriteFail implements Runnable {
        @UnsupportedAppUsage
        public NotificationRunnable_onNotifyDataWriteFail(){
        }
        @Override
        public void run() {
            RFIDCallback.this.onNotifyDataWriteFail();
        }
    }

    /*
    private final class NotificationRunnable_onBtDeviceConnected implements Runnable {
        @UnsupportedAppUsage
        public NotificationRunnable_onBtDeviceConnected(){
        }
        @Override
        public void run() {
            RFIDCallback.this.onBtDeviceConnected();
        }
    }

    private final class NotificationRunnable_onBtDeviceConnectFail implements Runnable {
        @UnsupportedAppUsage
        public NotificationRunnable_onBtDeviceConnectFail() {
        }
        @Override
        public void run() {
            RFIDCallback.this.onBtDeviceConnectFail();
        }
    }
    */

    private final class NotificationRunnable_onNotifyReceivedPacket implements Runnable {
        private final RecvPacket mPacket;
        @UnsupportedAppUsage
        public NotificationRunnable_onNotifyReceivedPacket(RecvPacket recvPacket) {
            mPacket = recvPacket;
        }
        @Override
        public void run() {
            RFIDCallback.this.onNotifyReceivedPacket(mPacket);
        }
    }

    private final class NotificationRunnable_onNotifyChangedState implements Runnable {
        private final int mState;
        @UnsupportedAppUsage
        public NotificationRunnable_onNotifyChangedState(int state) {
            mState = state;
        }
        @Override
        public void run() {
            RFIDCallback.this.onNotifyChangedState(mState);
        }
    }

    private static final class Transport extends IRFIDCallback.Stub {
        private RFIDCallback mRFIDCallback;
        @UnsupportedAppUsage
        public Transport(RFIDCallback RFIDCallback) {
            mRFIDCallback = RFIDCallback;
        }

        @Override
        @UnsupportedAppUsage
        public void onNotifyDataWriteFail() {
            RFIDCallback rfidCallback = mRFIDCallback;
            if (rfidCallback != null) {
                rfidCallback.dispatchonNotifyDataWriteFail();
            }
        }

        /*
        @Override
        @UnsupportedAppUsage
        public void onBtDeviceConnected() {
            RFIDCallback rfidCallback = mRFIDCallback;
            if (rfidCallback != null) {
                rfidCallback.dispatchonBtDeviceConnected();
            }
        }

        @Override
        @UnsupportedAppUsage
        public void onBtDeviceConnectFail() {
            RFIDCallback rfidCallback = mRFIDCallback;
            if (rfidCallback != null) {
                rfidCallback.dispatchonBtDeviceConnectFail();
            }
        }
        */

        @Override
        @UnsupportedAppUsage
        public void onNotifyReceivedPacket(RecvPacket recvPacket) {
            RFIDCallback rfidCallback = mRFIDCallback;
            if (rfidCallback != null) {
                rfidCallback.dispatchonNotifyReceivedPacket(recvPacket);
            }
        }

        @Override
        @UnsupportedAppUsage
        public void onNotifyChangedState(int state) {
            RFIDCallback rfidCallback = mRFIDCallback;
            if (rfidCallback != null) {
                rfidCallback.dispatchonNotifyChangedState(state);
            }
        }

        @UnsupportedAppUsage
        public void releaseRFIDCallback() {
            mRFIDCallback = null;
        }
    }
}
