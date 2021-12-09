package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class ActionConfig implements Parcelable {
    /**
     * Action index (ScanConst.ActionIndex)
     */
    @UnsupportedAppUsage
	public int index;

    /**
     * Action order (priority). (0 base)
     */
    @UnsupportedAppUsage
	public int order;

	/**
     * First number value.
     */
    @UnsupportedAppUsage
	public int firstNumberValue;

	/**
     * Second number value.
     */
    @UnsupportedAppUsage
	public int secondNumberValue;

	/**
     * First string length.
     */
    @UnsupportedAppUsage
	public int lengthOfFirstString;

	/**
     * First string value.
     */
    @UnsupportedAppUsage
	public String firstStringValue;

	/**
     * Second string length.
     */
    @UnsupportedAppUsage
	public int lengthOfSecondString;

    /**
     * Second string value.
     */
    @UnsupportedAppUsage
	public String secondStringValue;

	public static final Parcelable.Creator<ActionConfig> CREATOR
	        = new Parcelable.Creator<ActionConfig>() {
        public ActionConfig createFromParcel(Parcel in) {
            return new ActionConfig(in);
        }

        public ActionConfig[] newArray(int size) {
            return new ActionConfig[size];
        }
    };

    /**
     * Constructor of ActionConfig.
     */
    @UnsupportedAppUsage
    public ActionConfig() {
    }

    private ActionConfig(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Set the rectangle's coordinates from the data stored in the specified parcel.
     *
     * @param in  The parcel to read the rectangle's coordinate from
     *
     * @see   writeToParcel()
     */
    public void readFromParcel(Parcel in) {
        index = in.readInt();
        order = in.readInt();
		firstNumberValue = in.readInt();
		secondNumberValue = in.readInt();
		lengthOfFirstString = in.readInt();
		firstStringValue = in.readString();
		lengthOfSecondString = in.readInt();
        secondStringValue = in.readString();
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's marchalled representation.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write this rectangle to the specified parcel.
     *
     * @param dest   The parcel to write the rectangle's coordinate into
     * @param flags  Additional flags about how the object should be written. Unused.
     *
     * @see   readFromParcel()
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeInt(order);
		dest.writeInt(firstNumberValue);
		dest.writeInt(secondNumberValue);
		dest.writeInt(lengthOfFirstString);
		dest.writeString(firstStringValue);
		dest.writeInt(lengthOfSecondString);
        dest.writeString(secondStringValue);
    }
}
