package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class AntPower implements Parcelable {

    @UnsupportedAppUsage
	public int ant_1_power;
    @UnsupportedAppUsage
    public int ant_2_power;

	public static final Parcelable.Creator<AntPower> CREATOR
	        = new Parcelable.Creator<AntPower>() {
        public AntPower createFromParcel(Parcel in) {
            return new AntPower(in);
        }

        public AntPower[] newArray(int size) {
            return new AntPower[size];
        }
    };

    /**
     * Constructor of AntPower.
     */
    @UnsupportedAppUsage
    public AntPower() {
    }

    private AntPower(Parcel in) {
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
        ant_1_power = in.readInt();
        ant_2_power = in.readInt();
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
        dest.writeInt(ant_1_power);
        dest.writeInt(ant_2_power);
    }
}
