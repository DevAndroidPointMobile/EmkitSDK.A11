package device.sdk;

import android.compat.annotation.UnsupportedAppUsage;

import device.common.ExGpioInterruptCallback;
import device.sdk.DeviceServer;

public class ExGpioManager {

	private static final String TAG = ExGpioManager.class.getSimpleName();
	private static ExGpioManager mThis = null;

    @UnsupportedAppUsage
	public ExGpioManager() {}
    @UnsupportedAppUsage
    public static ExGpioManager get() {
        if (mThis == null) {
            mThis = new ExGpioManager();
        }
        return mThis;
	}

    /**
     * Returns whether the extended GPIO board is activated.
     */
    @UnsupportedAppUsage
    public boolean isEnabled() {
        try {
            return DeviceServer.getIExGpioService().isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Sets whether the extended GPIO board is activated or not.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean setEnabled(boolean enable) {
        try {
            return DeviceServer.getIExGpioService().setEnabled(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Sets the assigned GPIO to input.
     * @param gpio The assigned GPIO number.
     * @param pull One of Floating (0), Pull-Up (1), Pull-Down (2).
     * @return 0 if the setting call succeeds, negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int setInputDirection(int gpio, int pull) {
        try {
            return DeviceServer.getIExGpioService().setInputDirection(gpio, pull);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sets the assigned GPIO to output.
     * @param gpio The assigned GPIO number.
     * @param val 1 sets HIGH and 0 sets LOW.
     * @return 0 if the setting call succeeds, negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int setOutputDirection(int gpio, int val) {
        try {
            return DeviceServer.getIExGpioService().setOutputDirection(gpio, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sets the voltage of the assigned GPIO.
     * Only GPIO set as output direction can be used.
     * @param gpio The assigned GPIO number.
     * @param val 1 sets HIGH and 0 sets LOW.
     * @return 0 if the setting call succeeds, negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int setValue(int gpio, int val) {
        try {
            return DeviceServer.getIExGpioService().setValue(gpio, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Gets the voltage of the assigned GPIO.
     * Only GPIO set as input direction can be used.
     * @param gpio The assigned GPIO number.
     * @return 0 if the voltage is low, 1 is high, negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int getValue(int gpio) {
        try {
            return DeviceServer.getIExGpioService().getValue(gpio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sets the rising ore falling edge to the assigned GPIO.
     * @param gpio The assigned GPIO number.
     * @param edge One of Nothing (0), Falling (1), Rising (2), Both edge (3).
     * @return 0 if the setting call succeeds, negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int setEdge(int gpio, int edge) {
        try {
            return DeviceServer.getIExGpioService().setEdge(gpio, edge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Registers an interrupt detection callback for the assigned GPIO.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean registerCallback(ExGpioInterruptCallback callback) {
		try {
            return DeviceServer.getIExGpioService().registerCallback(callback.getInterruptCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }

    /**
     * Unregisters an interrupt detection callback for the assigned GPIO.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean unregisterCallback(ExGpioInterruptCallback callback) {
        try {
            return DeviceServer.getIExGpioService().unregisterCallback(callback.getInterruptCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }
}
