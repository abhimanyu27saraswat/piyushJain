package com.purplesweetbox.piyushjain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by flip on 13/03/18.
 */

public class TrailStation implements Parcelable {

    public int id;
    public int sequenceNo;
    public String GPSLocation;
    public String trailStationName;
    public String learningTrailId;
    public String instructions;

    public TrailStation(){}

    protected TrailStation(Parcel in) {
        id = in.readInt();
        sequenceNo = in.readInt();
        GPSLocation = in.readString();
        trailStationName = in.readString();
        learningTrailId = in.readString();
        instructions = in.readString();
    }

    public static final Creator<TrailStation> CREATOR = new Creator<TrailStation>() {
        @Override
        public TrailStation createFromParcel(Parcel in) {
            return new TrailStation(in);
        }

        @Override
        public TrailStation[] newArray(int size) {
            return new TrailStation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeInt(sequenceNo);
        dest.writeString(GPSLocation);
        dest.writeString(trailStationName);
        dest.writeString(learningTrailId);
        dest.writeString(instructions);


    }
}
