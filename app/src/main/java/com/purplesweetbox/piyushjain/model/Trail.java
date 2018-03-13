package com.purplesweetbox.piyushjain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flip on 13/03/18.
 */

public class Trail implements Parcelable {

    public String trailId;
    public String trainerId;
    public String trailName;
    public List<String> trailStationList;

    public Trail(){}

    protected Trail(Parcel in) {

        trailName = in.readString();
        trainerId = in.readString();
        trailId = in.readString();
    }

    public static final Creator<Trail> CREATOR = new Creator<Trail>() {
        @Override
        public Trail createFromParcel(Parcel in) {
            return new Trail(in);
        }

        @Override
        public Trail[] newArray(int size) {
            return new Trail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

        dest.writeString(trailName);
        dest.writeString(trainerId);
        dest.writeString(trailId);
    }
}
