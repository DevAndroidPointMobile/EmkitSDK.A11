package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class ReportFormatOfInvent implements Parcelable {

    @UnsupportedAppUsage
	public int time;
    @UnsupportedAppUsage
    public int rssi;

	public static final Parcelable.Creator<ReportFormatOfInvent> CREATOR
	        = new Parcelable.Creator<ReportFormatOfInvent>() {
        public ReportFormatOfInvent createFromParcel(Parcel in) {
            return new ReportFormatOfInvent(in);
        }

        public ReportFormatOfInvent[] newArray(int size) {
            return new ReportFormatOfInvent[size];
        }
    };

    /**
     * Constructor of ReportFormatOfInvent.
     */
    @UnsupportedAppUsage
    public ReportFormatOfInvent() {
    }

    private ReportFormatOfInvent(Parcel in) {
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
        time = in.readInt();
        rssi = in.readInt();
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
        dest.writeInt(time);
        dest.writeInt(rssi);
    }
}
