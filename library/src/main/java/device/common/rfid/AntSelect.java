package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class AntSelect implements Parcelable {

    @UnsupportedAppUsage
	public int ant_1;
    @UnsupportedAppUsage
    public int ant_2;

	public static final Parcelable.Creator<AntSelect> CREATOR
	        = new Parcelable.Creator<AntSelect>() {
        public AntSelect createFromParcel(Parcel in) {
            return new AntSelect(in);
        }

        public AntSelect[] newArray(int size) {
            return new AntSelect[size];
        }
    };

    /**
     * Constructor of AntSelect.
     */
    @UnsupportedAppUsage
    public AntSelect() {
    }

    private AntSelect(Parcel in) {
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
        ant_1 = in.readInt();
        ant_2 = in.readInt();
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
        dest.writeInt(ant_1);
        dest.writeInt(ant_2);
    }
}
