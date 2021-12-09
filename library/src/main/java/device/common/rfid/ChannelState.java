package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class ChannelState implements Parcelable {

    @UnsupportedAppUsage
	public int channelIndex;
    @UnsupportedAppUsage
    public int enable;

	public static final Parcelable.Creator<ChannelState> CREATOR
	        = new Parcelable.Creator<ChannelState>() {
        public ChannelState createFromParcel(Parcel in) {
            return new ChannelState(in);
        }

        public ChannelState[] newArray(int size) {
            return new ChannelState[size];
        }
    };

    /**
     * Constructor of ChannelState.
     */
    @UnsupportedAppUsage
    public ChannelState() {
    }

    private ChannelState(Parcel in) {
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
        channelIndex = in.readInt();
        enable = in.readInt();
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
        dest.writeInt(channelIndex);
        dest.writeInt(enable);
    }
}
