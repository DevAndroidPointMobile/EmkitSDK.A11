package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class ModeOfInvent implements Parcelable {

    @UnsupportedAppUsage
	public int single;
    @UnsupportedAppUsage
    public int select;
    @UnsupportedAppUsage
    public int timeout;

	public static final Parcelable.Creator<ModeOfInvent> CREATOR
	        = new Parcelable.Creator<ModeOfInvent>() {
        public ModeOfInvent createFromParcel(Parcel in) {
            return new ModeOfInvent(in);
        }

        public ModeOfInvent[] newArray(int size) {
            return new ModeOfInvent[size];
        }
    };

    /**
     * Constructor of ModeOfInvent.
     */
    @UnsupportedAppUsage
    public ModeOfInvent() {
    }

    private ModeOfInvent(Parcel in) {
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
        single = in.readInt();
        select = in.readInt();
        timeout = in.readInt();
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
        dest.writeInt(single);
        dest.writeInt(select);
        dest.writeInt(timeout);
    }
}
