package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class ReportFormatOfInvent_ext implements Parcelable {

    @UnsupportedAppUsage
	public int time;
    @UnsupportedAppUsage
    public int rssi;
    @UnsupportedAppUsage
    public int channel;
    @UnsupportedAppUsage
    public int temp;
    @UnsupportedAppUsage
    public int phase;
    @UnsupportedAppUsage
    public int ant;

	public static final Parcelable.Creator<ReportFormatOfInvent_ext> CREATOR
	        = new Parcelable.Creator<ReportFormatOfInvent_ext>() {
        public ReportFormatOfInvent_ext createFromParcel(Parcel in) {
            return new ReportFormatOfInvent_ext(in);
        }

        public ReportFormatOfInvent_ext[] newArray(int size) {
            return new ReportFormatOfInvent_ext[size];
        }
    };

    /**
     * Constructor of ReportFormatOfInvent_ext.
     */
    @UnsupportedAppUsage
    public ReportFormatOfInvent_ext() {
    }

    private ReportFormatOfInvent_ext(Parcel in) {
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
        channel = in.readInt();
        temp = in.readInt();
        phase = in.readInt();
        ant = in.readInt();
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
        dest.writeInt(channel);
        dest.writeInt(temp);
        dest.writeInt(phase);
        dest.writeInt(ant);
    }
}
