package device.common;

import device.common.IExGpioInterruptCallback;

interface IExGpioService {
    boolean isEnabled();
    boolean setEnabled(boolean enable);
    int setInputDirection(int gpio, int pull);
    int setOutputDirection(int gpio, int val);
    int setValue(int gpio, int val);
    int getValue(int gpio);
    int setEdge(int gpio, int edge);
    boolean registerCallback(IExGpioInterruptCallback callback);
    boolean unregisterCallback(IExGpioInterruptCallback callback);
}
