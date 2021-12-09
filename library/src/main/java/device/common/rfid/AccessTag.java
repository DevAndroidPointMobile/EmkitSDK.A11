package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class AccessTag implements Parcelable {

    @UnsupportedAppUsage
    /**
     * [Input parameter] length the length of data to read, write, or delete.
     */
    public int length;
    @UnsupportedAppUsage
    /**
     * [Input parameter] the memory bank of the tag to read, write, or delete.
     * <table border='1' cellspacing='0' cellpadding='3'>
     *  <tr>
     *   <th>Value</th>
     *   <th>Description</th>
     *  </tr>
     *  <tr>
     *   <td>0</td>
     *   <td>RESERVED bank</td>
     *  </tr>
     *  <tr>
     *   <td>1</td>
     *   <td>EPC bank</td>
     *  </tr>
     *  <tr>
     *   <td>2</td>
     *   <td>TID bank</td>
     *  </tr>
     *  <tr>
     *   <td>3</td>
     *   <td>USER bank</td>
     *  </tr>
     * </table>
     */
    public int memBank;
    @UnsupportedAppUsage
    /**
     * [Input parameter] an offset from the beginning of the memory bank.
     */
    public int offset;
    @UnsupportedAppUsage
    /**
     * [Input parameter] the data to write.
     */
    public String wTagData; // for write only
    @UnsupportedAppUsage
    /**
     * [Input parameter] the access password.
     */
    public String acsPwd;
    @UnsupportedAppUsage
    /**
     * [Input parameter] the kill password.
     */
    public String killPwd;
    @UnsupportedAppUsage
    /**
     * [Input parameter] the block write or erase unit.
     * <table border='1' cellspacing='0' cellpadding='3'>
     *  <tr>
     *   <th>Value</th>
     *   <th>Description</th>
     *  </tr>
     *  <tr>
     *   <td>0</td>
     *   <td>auto detect (default)</td>
     *  </tr>
     *  <tr>
     *   <td>1</td>
     *   <td>force one word block write</td>
     *  </tr>
     *  <tr>
     *   <td>2</td>
     *   <td>force two word block write</td>
     *  </tr>
     * </table>
     */
    public int blkMode; // used Block write and erase only
    @UnsupportedAppUsage
    /**
     * [Return result] ID of a tag access by the command.
     */
    public String tagId;
    @UnsupportedAppUsage
    /**
     * [Return result] UHF RFID EPC Global Gen2 Tag Error Codes.
     * <table border='1' cellspacing='0' cellpadding='3'>
     *  <tr>
     *   <th>Error Code</th>
     *   <th>Name</th>
     *   <th>Description</th>
     *  </tr>
     *  <tr>
     *   <td><code>0x00</code></td>
     *   <td>Other error</td>
     *   <td>Catch-all for errors not covered by other codes</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x01</code></td>
     *   <td>No supported</td>
     *   <td>The Tag does not support the specified parameters or feature</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x02</code></td>
     *   <td>Insufficient privileges</td>
     *   <td>The Interrogator did not authenticate itself with sufficient
     *    privileges for the Tag to perform the operation</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x03</code></td>
     *   <td>Memory overrun</td>
     *   <td>The Tag memory location does not exist, is too small, or the Tag
     *    does not support the specified EPC length</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x04</code></td>
     *   <td>Memory locked</td>
     *   <td>The Tag memory location is locked or permalocked and is either
     *    not writeable or not readable</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x05</code></td>
     *   <td>Crypto suite error</td>
     *   <td>Catch-all for errors specified by the cryptographic suite</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x06</code></td>
     *   <td>Command not encapsulated</td>
     *   <td>The Interrogator did not encapsulate the command in an AuthComm
     *    or SecureComm as required</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x07</code></td>
     *   <td>Response Buffer overflow</td>
     *   <td>The operation failed because the ResponseBuffer overflowed</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x08</code></td>
     *   <td>Security timeout</td>
     *   <td>The command failed because the Tag is in a security timeout</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x0b</code></td>
     *   <td>Insufficient power</td>
     *   <td>The Tag has insufficient power to perform the operation</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x0f</code></td>
     *   <td>Non-specific error</td>
     *   <td>The Tag does not support error-specific codes</td>
     *  </tr>
     * </table>
     */
    public int errTag;
    @UnsupportedAppUsage
    /**
     * [Return result] UHF RF transceiver error codes.
     * <table border='1' cellspacing='0' cellpadding='3'>
     *  <tr>
     *   <th>Error code</th>
     *   <th>Description</th>
     *  </tr>
     *  <tr>
     *   <td><code>0x01</code></td>
     *   <td>Handle Mismatch</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x02</code></td>
     *   <td>CRC error on tag response</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x03</code></td>
     *   <td>No tag Reply</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x04</code></td>
     *   <td>Invalid Password</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x05</code></td>
     *   <td>Zero Kill Password</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x06</code></td>
     *   <td>Tag Lost</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x07</code></td>
     *   <td>CMD Format Error</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x08</code></td>
     *   <td>Read Count Invalid</td>
     *  </tr>
     *  <tr>
     *   <td><code>0x09</code></td>
     *   <td>Out of retries</td>
     *  </tr>
     * </table>
     */
    public int errOp;

	public static final Parcelable.Creator<AccessTag> CREATOR
	        = new Parcelable.Creator<AccessTag>() {
        public AccessTag createFromParcel(Parcel in) {
            return new AccessTag(in);
        }

        public AccessTag[] newArray(int size) {
            return new AccessTag[size];
        }
    };

    /**
     * Constructor of AccessTag.
     */
    @UnsupportedAppUsage
    public AccessTag() {
    }

    private AccessTag(Parcel in) {
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
        length = in.readInt();
        memBank = in.readInt();
        offset = in.readInt();
        wTagData = in.readString();
        acsPwd = in.readString();
        killPwd = in.readString();
        blkMode = in.readInt();
        tagId = in.readString();
        errTag = in.readInt();
        errOp = in.readInt();
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
        dest.writeInt(length);
        dest.writeInt(memBank);
        dest.writeInt(offset);
        dest.writeString(wTagData);
        dest.writeString(acsPwd);
        dest.writeString(killPwd);
        dest.writeInt(blkMode);
        dest.writeString(tagId);
        dest.writeInt(errTag);
        dest.writeInt(errOp);
    }
}
