package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

/**
* Key map DB data class
*
*/
public class HiJackData implements Parcelable {
    @UnsupportedAppUsage
    public static final String INTENT_BROADCAST_KEY = "device.common.BROADCAST_KEYEVENT";
    @UnsupportedAppUsage
    public static final String EXTRA_BROADCAST_KEY = "EXTRA_KEYEVENT";
    @UnsupportedAppUsage
	public static final String EXTRA_CUSTOM_BROADCAST_ISKEYDOWN = "EXTRA_ISKEYDOWN";

    @UnsupportedAppUsage
    public static final int FLAG_NORMAL = 0;
    @UnsupportedAppUsage
    public static final int FLAG_INSERT = 1;
    @UnsupportedAppUsage
    public static final int FLAG_UPDATE = 2;
    @UnsupportedAppUsage
    public static final int FLAG_DELETE = 3;

    private int mFlag = FLAG_NORMAL;
    private long mID = 0;
    private String mLabel = "";
    private int mDefaultKeyCode = 0;
    private String mDefaultSymbol = "";
	private int mDefineKeyCode = 0;
	private String mDefineSymbol = "";
    private int mConvertKeyCode = 0;
    private String mConvertSymbol = "";
    private String mActivityNameOfExecuteApp = "";
    private String mPackageNameOfExecuteApp = "";
    private int mBroadcastKey = 0;
    private String mCustomBroadcastIntent = "";
    private String mMacroKeyCodes = ""; //[#19076][dana.lee] Add key Macro

    public static final Parcelable.Creator<HiJackData> CREATOR
        = new Parcelable.Creator<HiJackData>() {
            public HiJackData createFromParcel(Parcel in) {
                return new HiJackData(in);
            }
            public HiJackData[] newArray(int size) {
                return new HiJackData[size];
            }
        };

    @UnsupportedAppUsage
    public HiJackData() {
    }

    private HiJackData(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        setFlag(in.readInt());
        setID(in.readLong());
        setLabel(in.readString());
        setDefaultKeyCode(in.readInt());
        setDefaultSymbol(in.readString());
        setDefineKeyCode(in.readInt());
        setDefineSymbol(in.readString());
        setConvertKeyCode(in.readInt());
        setConvertSymbol(in.readString());
        setActivityNameOfExecuteApp(in.readString());
        setPackageNameOfExecuteApp(in.readString());
        setBroadcastKey(in.readInt());
        setCustomBroadcastIntent(in.readString());
        setMacroKeyCodes(in.readString()); //[#19076][dana.lee] Add key Macro
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getFlag());
        dest.writeLong(getID());
        dest.writeString(getLabel());
        dest.writeInt(getDefaultKeyCode());
        dest.writeString(getDefaultSymbol());
        dest.writeInt(getDefineKeyCode());
        dest.writeString(getDefineSymbol());
        dest.writeInt(getConvertKeyCode());
        dest.writeString(getConvertSymbol());
        dest.writeString(getActivityNameOfExecuteApp());
        dest.writeString(getPackageNameOfExecuteApp());
        dest.writeInt(getBroadcastKey());
        dest.writeString(getCustomBroadcastIntent());
        dest.writeString(getMacroKeyCodes()); //[#19076][dana.lee] Add key Macro
    }

    /**
     * This function gets the DB flag.
     *
     * @return DB flag with int
     * <pre>
     * public static final int FLAG_NORMAL = 0;
     * public static final int FLAG_INSERT = 1;
     * public static final int FLAG_UPDATE = 2;
     * public static final int FLAG_DELETE = 3;
     * </pre>
     *
     * @see device.common.HiJackData#setFlag
     */
    @UnsupportedAppUsage
    public int getFlag() {
        return mFlag;
    }

    /**
     * This function sets the DB flag.
     *
     * @param flag new DB flag with int
     * <pre>
     * public static final int FLAG_NORMAL = 0;
     * public static final int FLAG_INSERT = 1;
     * public static final int FLAG_UPDATE = 2;
     * public static final int FLAG_DELETE = 3;
     * </pre>
     *
     * @see device.common.HiJackData#getFlag
     */
    @UnsupportedAppUsage
    public void setFlag(int flag) {
        this.mFlag = flag;
    }

    /**
     * This function gets the DB id.
     *
     * @return DB id with long
     *
     * @see device.common.HiJackData#setID
     */
    @UnsupportedAppUsage
    public long getID() {
        return mID;
    }

    /**
     * This function sets the DB id.
     *
     * @param id new DB id with long
     *
     * @see device.common.HiJackData#getID
     */
    @UnsupportedAppUsage
    public void setID(long id) {
        this.mID = id;
    }

    /**
     * This function gets the key label.
     *
     * @return key label with String
     *
     * @see device.common.HiJackData#setLabel
     */
    @UnsupportedAppUsage
    public String getLabel() {
        return mLabel;
    }

    /**
     * This function sets the key label.
     *
     * @param label new key label with String
     *
     * @see device.common.HiJackData#getLabel
     */
    @UnsupportedAppUsage
    public void setLabel(String label) {
        this.mLabel = label;
    }

    /**
     * This function gets the default key code.
     *
     * @return default key code with int
     *
     * @see device.common.HiJackData#setDefaultKeyCode
     */
    @UnsupportedAppUsage
    public int getDefaultKeyCode() {
        return mDefaultKeyCode;
    }

    /**
     * This function sets the default key code.
     *
     * @param defaultKeyCode new default key code with int
     *
     * @see device.common.HiJackData#getDefaultKeyCode
     */
    @UnsupportedAppUsage
    public void setDefaultKeyCode(int defaultKeyCode) {
        this.mDefaultKeyCode = defaultKeyCode;
    }

    /**
     * This function gets the default key symbol.
     *
     * @return default key symbol with String
     *
     * @see device.common.HiJackData#setDefaultSymbol
     */
    @UnsupportedAppUsage
    public String getDefaultSymbol() {
        return mDefaultSymbol;
    }

    /**
     * This function sets the default key symbol.
     *
     * @param defaultSymbol new default key symbol with String
     *
     * @see device.common.HiJackData#getDefaultSymbol
     */
    @UnsupportedAppUsage
    public void setDefaultSymbol(String defaultSymbol) {
        this.mDefaultSymbol = defaultSymbol;
    }

    /**
     * This function gets the define key code.
     *
     * @return define key code with int
     *
     * @see device.common.HiJackData#setDefineKeyCode
     */
    @UnsupportedAppUsage
    public int getDefineKeyCode() {
        return mDefineKeyCode;
    }

    /**
     * This function sets the define key code.
     *
     * @param defineKeyCode new define key code with int
     *
     * @see device.common.HiJackData#getDefineKeyCode
     */
    @UnsupportedAppUsage
    public void setDefineKeyCode(int defineKeyCode) {
        this.mDefineKeyCode = defineKeyCode;
    }

    /**
     * This function gets the define key symbol.
     *
     * @return define key symbol with String
     *
     * @see device.common.HiJackData#setDefineSymbol
     */
    @UnsupportedAppUsage
    public String getDefineSymbol() {
        return mDefineSymbol;
    }

    /**
     * This function sets the define key symbol.
     *
     * @param defineSymbol new define key symbol with String
     *
     * @see device.common.HiJackData#getDefineSymbol
     */
    @UnsupportedAppUsage
    public void setDefineSymbol(String defineSymbol) {
        this.mDefineSymbol = defineSymbol;
    }

    /**
     * This function gets the key code for converting to.
     *
     * @return key code for converting to with int
     *
     * @see device.common.HiJackData#setConvertKeyCode
     */
    @UnsupportedAppUsage
    public int getConvertKeyCode() {
        return mConvertKeyCode;
    }

    /**
     * This function sets the key code for converting to.
     *
     * @param convertKeyCode new key code for converting to with int
     *
     * @see device.common.HiJackData#getConvertKeyCode
     */
    @UnsupportedAppUsage
    public void setConvertKeyCode(int convertKeyCode) {
        this.mConvertKeyCode = convertKeyCode;
    }

    /**
     * This function gets the key symbol for converting to.
     *
     * @return key symbol for converting to with String
     *
     * @see device.common.HiJackData#setConvertSymbol
     */
    @UnsupportedAppUsage
    public String getConvertSymbol() {
        return mConvertSymbol;
    }

    /**
     * This function sets the key symbol for converting to.
     *
     * @param convertSymbol new key symbol for converting to with String
     *
     * @see device.common.HiJackData#getConvertSymbol
     */
    @UnsupportedAppUsage
    public void setConvertSymbol(String convertSymbol) {
        this.mConvertSymbol = convertSymbol;
    }

    /**
     * This function gets the app activity name for executing.
     *
     * @return app activity name for executing with String
     *
     * @see device.common.HiJackData#setActivityNameOfExecuteApp
     */
    @UnsupportedAppUsage
    public String getActivityNameOfExecuteApp() {
        return mActivityNameOfExecuteApp;
    }

    /**
     * This function sets the app activity name for executing.
     *
     * @param activityNameOfExecuteApp new app activity name for executing to with String
     *
     * @see device.common.HiJackData#getActivityNameOfExecuteApp
     */
    @UnsupportedAppUsage
    public void setActivityNameOfExecuteApp(String activityNameOfExecuteApp) {
        this.mActivityNameOfExecuteApp = activityNameOfExecuteApp;
    }

    /**
     * This function gets the app package name for executing.
     *
     * @return app package name for executing with String
     *
     * @see device.common.HiJackData#setPackageNameOfExecuteApp
     */
    @UnsupportedAppUsage
    public String getPackageNameOfExecuteApp() {
        return mPackageNameOfExecuteApp;
    }

    /**
     * This function sets the app package name for executing.
     *
     * @param packageNameOfExecuteApp new app package name for executing to with String
     *
     * @see device.common.HiJackData#getPackageNameOfExecuteApp
     */
    @UnsupportedAppUsage
    public void setPackageNameOfExecuteApp(String packageNameOfExecuteApp) {
        this.mPackageNameOfExecuteApp = packageNameOfExecuteApp;
    }

    /**
     * This function gets state of whether to broadcast key or not.
     *
     * @return state of whether to broadcast key or not with int (1 = true, 0 = false)
     *
     * @see device.common.HiJackData#setBroadcastKey
     */
    @UnsupportedAppUsage
    public int getBroadcastKey() {
        return mBroadcastKey;
    }

    /**
     * This function gets state of whether to broadcast key or not.
     *
     * @return state of whether to broadcast key or not with boolean
     *
     * @see device.common.HiJackData#setBroadcastKey
     */
    @UnsupportedAppUsage
    public boolean isBroadcastKey() {
        return (mBroadcastKey == 1);
    }

    /**
     * This function sets state of whether to broadcast key or not.
     *
     * @param enable new state of whether to broadcast key or not with int (1 = true, 0 = false)
     *
     * @see device.common.HiJackData#getBroadcastKey
     */
    @UnsupportedAppUsage
    public void setBroadcastKey(int enable) {
        this.mBroadcastKey = enable;
    }

    /**
     * This function gets the custom intent name for broadcasting.
     *
     * @return custom intent name for broadcasting with String
     *
     * @see device.common.HiJackData#setCustomBroadcastIntent
     */
    @UnsupportedAppUsage
    public String getCustomBroadcastIntent() {
        return mCustomBroadcastIntent;
    }

    /**
     * This function sets the custom intent name for broadcasting.
     *
     * @param intent new custom intent name for broadcasting to with String
     *
     * @see device.common.HiJackData#getCustomBroadcastIntent
     */
    @UnsupportedAppUsage
    public void setCustomBroadcastIntent(String intent) {
        this.mCustomBroadcastIntent = intent;
    }

    //[#19076][dana.lee] Add key Macro
    @UnsupportedAppUsage
    public String getMacroKeyCodes() {
        return mMacroKeyCodes;
    }

    @UnsupportedAppUsage
    public void setMacroKeyCodes(String keys) {
        this.mMacroKeyCodes = keys;
    }

    @Override
    public String toString() {
        return mLabel;
    }
}
