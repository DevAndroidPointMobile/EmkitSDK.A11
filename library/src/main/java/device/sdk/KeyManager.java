package device.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.compat.annotation.UnsupportedAppUsage;
import android.view.KeyEvent;
import android.content.Context;
import android.os.RemoteException;

import device.common.HiJackData;
import device.common.HijackingKeys;
import device.sdk.DeviceServer;

public class KeyManager {
    private static final String TAG = KeyManager.class.getSimpleName();
    private static KeyManager mInstance = null;
    static { System.loadLibrary("jni_keymanager"); }

    @UnsupportedAppUsage
    public static final int KEYCODE_SOFTKEY_LOCK = 1008; //KeyEvent.KEYCODE_SOFTKEY_LOCK;
    @UnsupportedAppUsage
    public static final int KEYPAD_MODE_NORMAL = 0;
    @UnsupportedAppUsage
    public static final int KEYPAD_MODE_NUMBER = 1;
    @UnsupportedAppUsage
    public static final int KEYPAD_MODE_LOWERCASE = 2;
    @UnsupportedAppUsage
    public static final int KEYPAD_MODE_UPPERCASE = 3;
    @UnsupportedAppUsage
    public static final int KEYPAD_MODE_FUNCTION = 4;

    @UnsupportedAppUsage
    public static KeyManager getInstance() {
        if (mInstance == null) {
            mInstance = new KeyManager();
        }
        return mInstance;
    }
    @UnsupportedAppUsage
    public KeyManager() {}

    /* For Changing KeyCharacterMap File */
    private native int checkKCMapFile(String path);
    private static byte[] getBytesFromFile(String path) {
        try {
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length];

            if (length > Integer.MAX_VALUE) {
                bytes = null;
            } else {
                int offset = 0;
                int numRead = 0;
                while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                    offset += numRead;
                }
            }
            is.close();
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This function reads current KCM file path.
     *
     * @return file path with String
     */
    @UnsupportedAppUsage
    public String getCurrentKCMapFile() throws RemoteException {
        return DeviceServer.getIHiJackService().getCurrentKCMapFile();
    }

    /**
     * This function changes key map with new KCM file.
     *
     * @param path new KCM file path with String.
     *
     * @return Zero indicates success; Nonzero indicates failure.
     */
    @UnsupportedAppUsage
    public int changeKCMapFile(String path) throws RemoteException {
        int result = checkKCMapFile(path);
        if (result == 0) {
            result = DeviceServer.getIHiJackService().changeKCMapFile(path, getBytesFromFile(path));
        }
        return result;
    }

    /**
     * This function removes a user changed KCM file and sets with default KCM file.
     *
     * @return true indicates success; false indicates failure
     */
    @UnsupportedAppUsage
    public boolean removeKCMapFile() throws RemoteException {
        return DeviceServer.getIHiJackService().removeKCMapFile();
    }

    /**
     * This function gets the key list to be able to re-map to.
     *
     * @return key list to be able to re-map to with KeyManagerKey array
     */
    @UnsupportedAppUsage
    public HijackingKeys[] getHijackingKeys()  throws RemoteException {
        return DeviceServer.getIHiJackService().getHijackingKeys();
    }

    /**
     * This function updates the key map DB with new KeyManagerData list.
     *
     * @param dataList new KeyManagerData array
     *
     * @return mapping count with int
     *
     * @see device.sdk.KeyManager#getAllHiJackData
     */
    @UnsupportedAppUsage
    public int setAllHiJackData(HiJackData[] dataList) throws RemoteException {
        return DeviceServer.getIHiJackService().setAllHiJackData(dataList);
    }

    /**
     * This function gets all probrammable key data from key map DB.
     *
     * @return all probrammable key data with KeyManagerData array
     *
     * @see device.sdk.KeyManager#setAllHiJackData
     */
    @UnsupportedAppUsage
    public HiJackData[] getAllHiJackData() throws RemoteException {
        return DeviceServer.getIHiJackService().getAllHiJackData();
    }

    /**
     * Sets whether or not to use the unified keycode.
     * Works only on Android versions 6 and below.
     * @param enabled {@code true} to enable, {@code false} to disable.
     * @see #isUsingUnifiedKeycode
     */
    @UnsupportedAppUsage
    public void useUnifiedKeycode(boolean enabled) throws RemoteException {
        DeviceServer.getIHiJackService().useUnifiedKeycode(enabled);
    }

    /**
     * Gets whether the unified keycode is used.
     * Works only on Android versions 6 and below.
     * @return {@code true} When the use of unified keycode is enabled
     * @see #useUnifiedKeycode
     */
    @UnsupportedAppUsage
    public boolean isUsingUnifiedKeycode() throws RemoteException {
        return DeviceServer.getIHiJackService().isUsingUnifiedKeycode();
    }

    /**
     * This function sets with default KCM file.
     *
     * @return true indicates success; false indicates failure
     */
    @UnsupportedAppUsage
    public boolean changeKCMapFileToDefault() throws RemoteException {
        return DeviceServer.getIHiJackService().changeKCMapFileToDefault();
    }

    /**
     * Gets whether the direct input style is.
     * @return {@code ture} When the direct input style is enabled
     */
    @UnsupportedAppUsage
    public boolean isDirectInputStyle() throws RemoteException {
        return DeviceServer.getIHiJackService().isDirectInputStyle();
    }

    /**
     * Sets whether the direct input style.
     * @return {@code ture} When the direct input style is enabled
     */
    @UnsupportedAppUsage
    public boolean setDirectInputStyle(boolean enable) throws RemoteException {
        return DeviceServer.getIHiJackService().setDirectInputStyle(enable);
    }

    /**
     * Gets whether the key control mode is.
     * @return {@code ture} When the key control mode is enabled
     */
    @UnsupportedAppUsage
    public boolean isKeyControlMode() throws RemoteException {
        return DeviceServer.getIHiJackService().isKeyControlMode();
    }

    /**
     * Sets whether the key control mode.
     * @return {@code ture} When the key control mode is enabled
     */
    @UnsupportedAppUsage
    public boolean setKeyControlMode(boolean enable) throws RemoteException {
        return DeviceServer.getIHiJackService().setKeyControlMode(enable);
    }

    /**
     * Gets the current keypad mode.
     * @return One of {@link #KEYPAD_MODE_NORMAL}, {@link #KEYPAD_MODE_NUMBER}, {@link #KEYPAD_MODE_LOWERCASE}, {@link #KEYPAD_MODE_UPPERCASE} or {@link #KEYPAD_MODE_FUNCTION}.
     */
    @UnsupportedAppUsage
    public int getKeypadMode() throws RemoteException {
        return DeviceServer.getIHiJackService().getKeypadMode();
    }

    /**
     * Sets the keypad mode directly.
     * @param mode One of {@link #KEYPAD_MODE_NORMAL}, {@link #KEYPAD_MODE_NUMBER}, {@link #KEYPAD_MODE_LOWERCASE}, {@link #KEYPAD_MODE_UPPERCASE} or {@link #KEYPAD_MODE_FUNCTION}.
     */
    @UnsupportedAppUsage
    public boolean setKeypadMode(int mode) throws RemoteException {
        return DeviceServer.getIHiJackService().setKeypadMode(mode);
    }
}

