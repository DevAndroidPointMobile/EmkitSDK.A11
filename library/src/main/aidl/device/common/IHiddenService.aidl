package device.common;

import android.content.ComponentName;
import android.content.pm.UserInfo;

interface IHiddenService {
    void setBrightnessLED(int id, int brightness);
    void setBrightnessExtendLED(int id, int brightness, int brightnessMode);
    void setColorLED(int id, int color);
    void setFlashingLED(int id, int color, int mode, int onMS, int offMS);
    void stopFlashingLED(int id);
    void pulseLED(int id);
    void pulseExtendLED(int id, int color, int onMS);
    void turnOffLED(int id);
    void setLightLockedLED(int id, int color, int mode, int onMS, int offMS, int brightnessMode);

    void reboot(boolean confirm, String reason, boolean wait);
    void lockNow(boolean go2sleep);
    void toggleBrightness();
    boolean getShowSoftKeyboard();
    void setShowSoftKeyboard(boolean show);

    String getSecureSettings(String name);
    void putSecureSettings(String name, String value);

    String getSystemProperty(in String key, in String def);
    void setSystemProperty(in String key, in String val);

    void setBoolean(in String key, in boolean value, in int userID, in String keyID);
    void setLong(in String key, in long value, in int userID, in String keyID);
    void setString(in String key, in String value, in int userID, in String keyID);
    boolean getBoolean(in String key, in boolean value, in int userID, in String keyID);
    long getLong(in String key, in long value, in int userID, in String keyID);
    String getString(in String key, in String value, in int userID, in String keyID);
    void setPassword(in byte[] hash, in int userID, in String keyID);
    boolean checkPassword(in byte[] hash, in int userID, in String keyID);
    byte[] getHashPassword(in int userID, in String keyID);
    boolean havePassword(in int userID, in String keyID);
    void removeUser(int userID);

    String getModelName();
    String getProcessorName();
    int getHwRevision();
    String getHwRevName();
    int setModuleType(int index, long type);
    long getModuleType(int index);
    String getModuleName(int index);

    int clearRegionMDB(int offset, int length);
    int writeIntegerMDB(int offset, int buffer);
    int writeBytesMDB(int offset, in byte[] buffer);
    int writeStringMDB(int offset, String buffer);
    int readIntegerMDB(int offset);
    byte[] readBytesMDB(int offset, int length);
    String readStringMDB(int offset, int length);

    int clearFieldMDB(int field);
    int writeFieldMDB(int field, String buffer);
    String readFieldMDB(int field);

    boolean setCustomBootScreenLogoBmp(in byte[] buffer, int length);
    boolean setCustomBootScreenLogoBmpRange(in byte[] buffer, int start, int end);
    void deleteCustomBootScreenLogoBmp();

    boolean isUsingConsoleSerialPort();
    void setUsingConsoleSerialPort(boolean using);

    boolean isEnabledShowKernelLog();
    void setEnabledShowKernelLog(boolean enabled);

    boolean isEnabledSaveLogcat();
    void setEnabledSaveLogcat(boolean enabled);

    boolean isEnabledSuspendNotification();
    void setEnabledSuspendNotification(boolean enabled);

    String readLineSystem(String filename);
    void writeLineSystem(String filename, String value);
    void runCommand(in String[] cmd);

    Map loadKeyValues(String absolutePath);
    boolean storeKeyValuesToFile(String absolutePath, in Map keyValues);

    void setAlwaysAllowUsbDebugging(boolean enabled);
    void setStayOnWhilePluggedInForProduction(boolean enabled);

    boolean setCpuScalingMinFreq(int freq);
    int getCpuScalingMinFreq();
    int getCpuScalingCurFreq(int cpu);
    int[] getCpuScalingAvailableFreqs();

    int getMajorNumberOfBaseModel();

    boolean getKeyboardVisibility();
    void setKeyboardVisibility(boolean enable);

    void turnOnKeyboardBacklight(boolean enable);

    int getTouchSensitivityValue();
    void setTouchSensitivityValue(int sensitivity);

    boolean getVibrateOnTouchEnabled();
    void setVibrateOnTouchEnabled(boolean enable);

    int getPasswordMinimumLength(in ComponentName who, int userHandle);
    int getPasswordHistoryLength(in ComponentName who, int userHandle);
    int getPasswordMinimumLetters(in ComponentName who, int userHandle);
    int getPasswordMinimumUpperCase(in ComponentName who, int userHandle);
    int getPasswordMinimumLowerCase(in ComponentName who, int userHandle);
    int getPasswordMinimumNumeric(in ComponentName who, int userHandle);
    int getPasswordMinimumSymbols(in ComponentName who, int userHandle);
    int getPasswordMinimumNonLetter(in ComponentName who, int userHandle);

    List<UserInfo> getProfiles(int userHandle);
    byte[] readBytesSystem(String filepath);
    boolean writeBytesSystem(String filepath, in byte[] buffer);

    int getCpuCount();
    String getAttestationDeviceId();

    String getBaseModelName();
    void setHumidityEnabled(boolean enable);

    int[] getCpuScalingAvailableFreqsWidthCpu(int cpu);
    boolean setCpuScalingMinFreqWidthCpu(int lowFreq, int highFreq);

    int getKeyboardBacklightLevel();
    boolean setKeyboardBacklightLevel(int level);

    String getSystemSettings(String name);
    void putSystemSettings(String name, String value);
    String getGlobalSettings(String name);
    void putGlobalSettings(String name, String value);
}
