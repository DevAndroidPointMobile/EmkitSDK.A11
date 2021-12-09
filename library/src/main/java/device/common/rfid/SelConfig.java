package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class SelConfig implements Parcelable {

    @UnsupportedAppUsage
	public int index;
    @UnsupportedAppUsage
    public int length;
    @UnsupportedAppUsage
    public int memBank;
    @UnsupportedAppUsage
    public int offset;
    @UnsupportedAppUsage
    public String selectData;
    @UnsupportedAppUsage
    public int target;
    @UnsupportedAppUsage
    public int action;

	public static final Parcelable.Creator<SelConfig> CREATOR
	        = new Parcelable.Creator<SelConfig>() {
        public SelConfig createFromParcel(Parcel in) {
            return new SelConfig(in);
        }

        public SelConfig[] newArray(int size) {
            return new SelConfig[size];
        }
    };

    /**
     * Constructor of SelConfig.
     */
    @UnsupportedAppUsage
    public SelConfig() {
    }

    private SelConfig(Parcel in) {
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
        length = in.readInt();
        memBank = in.readInt();
        offset = in.readInt();
        selectData = in.readString();
        target = in.readInt();
        action = in.readInt();
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
        dest.writeInt(length);
        dest.writeInt(memBank);
        dest.writeInt(offset);
        dest.writeString(selectData);
        dest.writeInt(target);
        dest.writeInt(action);
    }
}
