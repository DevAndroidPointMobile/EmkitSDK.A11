package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class ConditionConfig implements Parcelable {
    /**
     * SymbologyLow value. (DCD_SYM_NIL ~ DCD_SYM_HONGKONG_2OF5)
     */
    @UnsupportedAppUsage
	public int symbologyLow;

    /**
     * SymbologyMiddle value. (DCD_SYM_IATA_2OF5 ~ DCD_SYM_USPS_4CB)
     */
    @UnsupportedAppUsage
	public int symbologyMiddle;

	/**
     * SymbologyHigh value. (DCD_SYM_RSS ~ DCD_SYM_LAST)
     */
    @UnsupportedAppUsage
	public int symbologyHigh;

	/**
     * Data length. (0 is any length)
     */
    @UnsupportedAppUsage
	public int dataLength;

	/**
     * String position. (1 is first character)
     */
    @UnsupportedAppUsage
	public int stringPosition;

    /**
     * String value.
     */
    @UnsupportedAppUsage
	public String stringValue;

	public static final Parcelable.Creator<ConditionConfig> CREATOR
	        = new Parcelable.Creator<ConditionConfig>() {
        public ConditionConfig createFromParcel(Parcel in) {
            return new ConditionConfig(in);
        }

        public ConditionConfig[] newArray(int size) {
            return new ConditionConfig[size];
        }
    };

    /**
     * Constructor of ConditionConfig.
     */
    @UnsupportedAppUsage
    public ConditionConfig() {
    }

    private ConditionConfig(Parcel in) {
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
        symbologyLow = in.readInt();
        symbologyMiddle = in.readInt();
		symbologyHigh = in.readInt();
		dataLength = in.readInt();
		stringPosition = in.readInt();
        stringValue = in.readString();
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
        dest.writeInt(symbologyLow);
        dest.writeInt(symbologyMiddle);
		dest.writeInt(symbologyHigh);
		dest.writeInt(dataLength);
		dest.writeInt(stringPosition);
        dest.writeString(stringValue);
    }
}
