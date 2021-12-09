package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

/**
* Key class for mapping keys
*
*/
public class HijackingKeys implements Parcelable {
    @UnsupportedAppUsage
    public static final int FLAG_INCLUDE = 0; // Even if the key doesn't exist, it's in the key list.
    @UnsupportedAppUsage
    public static final int FLAG_EXCLUDE = 1; // Even if the key exits, it's not in the key list.
    @UnsupportedAppUsage
    public static final int FLAG_IFEXIST = 2; // If the key doesn't exist, it's not in the key list, and if the key exists, it's also in the key list.
    @UnsupportedAppUsage
    public static final int FLAG_SCANNER = 3; // For scanner key handles.
    @UnsupportedAppUsage
    public static final int FLAG_SPECIAL = 4; // Not used.

    private String mLabel = "";
	private String mDefaultSymbol = "";
	private int mDefaultKeyCode = 0;
    private String mDefineSymbol = "";
    private int mDefineKeyCode = 0;
    private int mFlag = FLAG_INCLUDE;

    public static final Parcelable.Creator<HijackingKeys> CREATOR
            = new Parcelable.Creator<HijackingKeys>() {
        public HijackingKeys createFromParcel(Parcel in) {
            return new HijackingKeys(in);
        }
        public HijackingKeys[] newArray(int size) {
            return new HijackingKeys[size];
        }
    };

    @UnsupportedAppUsage
    public HijackingKeys() {
    }

    @UnsupportedAppUsage
    public HijackingKeys(String label) {
        mLabel = label;
        mDefaultSymbol = label;
        mDefineSymbol = label;
    }

    private HijackingKeys(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        setLabel(in.readString());
		setDefaultSymbol(in.readString());
		setDefaultKeyCode(in.readInt());
        setDefineSymbol(in.readString());
        setDefineKeyCode(in.readInt());
        setFlag(in.readInt());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLabel);
		dest.writeString(mDefaultSymbol);
		dest.writeInt(mDefaultKeyCode);
        dest.writeString(mDefineSymbol);
        dest.writeInt(mDefineKeyCode);
        dest.writeInt(mFlag);
    }

    /**
    * This function gets the key label.
    *
    * @return key label with String
    *
    * @see device.common.HijackingKeys#setLabel
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
    * @see device.common.HijackingKeys#getLabel
    */
    @UnsupportedAppUsage
    public void setLabel(String label) {
        this.mLabel = label;
    }

    /**
    * This function gets the default key symbol such as SCANNER_F, DEL, HOME, VOLUME_UP and so on.
    *
    * @return default key symbol with String
    *
    * @see device.common.HijackingKeys#setDefaultSymbol
    */
    @UnsupportedAppUsage
    public String getDefaultSymbol() {
        return mDefaultSymbol;
    }
    /**
    * This function sets the default key symbol such as SCANNER_F, DEL, HOME, VOLUME_UP and so on.
    *
    * @param defaultSymbol new default key symbol with String
    *
    * @see device.common.HijackingKeys#getDefaultSymbol
    */
    @UnsupportedAppUsage
    public void setDefaultSymbol(String defaultSymbol) {
        this.mDefaultSymbol = defaultSymbol;
    }
    /**
    * This function gets the default key code.
    *
    * @return default key code with int
    *
    * @see device.common.HijackingKeys#setDefaultKeyCode
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
    * @see device.common.HijackingKeys#getDefaultKeyCode
    */
    @UnsupportedAppUsage
    public void setDefaultKeyCode(int defaultKeyCode) {
        this.mDefaultKeyCode = defaultKeyCode;
    }
    /**
    * This function gets the define key symbol such as SCANNER_F, DEL, HOME, VOLUME_UP and so on.
    *
    * @return define key symbol with String
    *
    * @see device.common.HijackingKeys#setDefineSymbol
    */
    @UnsupportedAppUsage
    public String getDefineSymbol() {
        return mDefineSymbol;
    }

    /**
    * This function sets the define key symbol such as SCANNER_F, DEL, HOME, VOLUME_UP and so on.
    *
    * @param defineSymbol new define key symbol with String
    *
    * @see device.common.HijackingKeys#getDefineSymbol
    */
    @UnsupportedAppUsage
    public void setDefineSymbol(String defineSymbol) {
        this.mDefineSymbol = defineSymbol;
    }

    /**
    * This function gets the define key code.
    *
    * @return define key code with int
    *
    * @see device.common.HijackingKeys#setDefineKeyCode
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
    * @see device.common.HijackingKeys#getDefineKeyCode
    */
    @UnsupportedAppUsage
    public void setDefineKeyCode(int defineKeyCode) {
        this.mDefineKeyCode = defineKeyCode;
    }

    /**
    * This function gets the flag such as FLAG_INCLUDE, FLAG_SCANNER and FLAG_SPECIAL.
    *
    * @return flag with int
    *
    * @see device.common.HijackingKeys#setFlag
    */
    @UnsupportedAppUsage
    public int getFlag() {
        return mFlag;
    }

    /**
    * This function sets the flag such as FLAG_INCLUDE, FLAG_SCANNER and FLAG_SPECIAL.
    *
    * @param flag new flag with int
    *
    * @see device.common.HijackingKeys#getFlag
    */
    @UnsupportedAppUsage
    public void setFlag(int flag) {
        this.mFlag = flag;
    }

    @Override
    public String toString() {
        return mLabel;
    }
}
