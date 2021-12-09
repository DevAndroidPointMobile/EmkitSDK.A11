package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class StatusEvent implements Parcelable {

    @UnsupportedAppUsage
	public int linkState;
    @UnsupportedAppUsage
    public int triggerState;
    @UnsupportedAppUsage
    public int lowBatt;
    @UnsupportedAppUsage
    public int autoOff;
    @UnsupportedAppUsage
    public int powerOff;

	public static final Parcelable.Creator<StatusEvent> CREATOR
	        = new Parcelable.Creator<StatusEvent>() {
        public StatusEvent createFromParcel(Parcel in) {
            return new StatusEvent(in);
        }

        public StatusEvent[] newArray(int size) {
            return new StatusEvent[size];
        }
    };

    /**
     * Constructor of StatusEvent.
     */
    @UnsupportedAppUsage
    public StatusEvent() {
    }

    private StatusEvent(Parcel in) {
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
        linkState = in.readInt();
        triggerState = in.readInt();
        lowBatt = in.readInt();
        autoOff = in.readInt();
        powerOff = in.readInt();
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
        dest.writeInt(linkState);
        dest.writeInt(triggerState);
        dest.writeInt(lowBatt);
        dest.writeInt(autoOff);
        dest.writeInt(powerOff);
    }
}
