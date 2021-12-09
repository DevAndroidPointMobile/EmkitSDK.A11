package device.common;

import android.view.KeyEvent;
import device.common.HijackingKeys;
import device.common.HiJackData;

interface IHiJackService {
    String getCurrentKCMapFile();
    int changeKCMapFile(String path, in byte[] data);
    boolean removeKCMapFile();
    HijackingKeys[] getHijackingKeys();
    int setAllHiJackData(in HiJackData[] dataList);
    HiJackData[] getAllHiJackData();
    int getTestMode();
    void changeTrayIcon(inout KeyEvent event);
    void updateMetaState(in KeyEvent event);
    boolean hijackingKey(inout KeyEvent event, boolean useCache);
    boolean hasWakeupRes(in KeyEvent event);
    void performKeyPressFeedback(in KeyEvent event);
    void broadcastKey(in String action, in String extra, in KeyEvent event);
    boolean hasHardwareKey(int keyCode);
    void useUnifiedKeycode(boolean enabled);
    boolean isUsingUnifiedKeycode();
    boolean isWakeupRes(int resourceID);
    boolean setWakeupRes(int resourceID, boolean enabled);
    boolean isFinishedHandle();
    boolean changeKCMapFileToDefault();
    boolean setFixedNumberMode(boolean on);
    boolean isDirectInputStyle();
    boolean setDirectInputStyle(boolean on);
    boolean isKeyControlMode();
    boolean setKeyControlMode(boolean on);
    int getKeypadMode();
    boolean setKeypadMode(int mode);
    char[] getSymbolGridArray();
    boolean setSymbolGridArray(in char[] symbolArray);
    int getSymbolGridItem();
    boolean setSymbolGridItem(int index);
}
