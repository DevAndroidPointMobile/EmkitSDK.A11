package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class BattEvent_ext implements Parcelable {

    @UnsupportedAppUsage
	public int batt;

    @UnsupportedAppUsage
    public int charge;

    @UnsupportedAppUsage
    public int volt;

    @UnsupportedAppUsage
    public int temp;

    @UnsupportedAppUsage
    public int cycle;

	public static final Parcelable.Creator<BattEvent_ext> CREATOR
	        = new Parcelable.Creator<BattEvent_ext>() {
        public BattEvent_ext createFromParcel(Parcel in) {
            return new BattEvent_ext(in);
        }

        public BattEvent_ext[] newArray(int size) {
            return new BattEvent_ext[size];
        }
    };

    /**
     * Constructor of BattEvent_ext.
     */
    @UnsupportedAppUsage
    public BattEvent_ext() {
    }

    private BattEvent_ext(Parcel in) {
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
        batt = in.readInt();
        charge = in.readInt();
        volt = in.readInt();
        temp = in.readInt();
        cycle = in.readInt();
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
        dest.writeInt(batt);
        dest.writeInt(charge);
        dest.writeInt(volt);
        dest.writeInt(temp);
        dest.writeInt(cycle);
    }
}
