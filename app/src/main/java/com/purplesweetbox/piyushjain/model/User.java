package com.purplesweetbox.piyushjain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flip on 13/03/18.
 */

public class User implements Parcelable {


    public String userId;
    public String userName;
    public String LoginType;
    public String userEmail;
    public String userProfilePicture;
    public String userRole;

    public User(){}

    protected User(Parcel in) {

        userId = in.readString();
        userName = in.readString();
        userEmail = in.readString();
        LoginType = in.readString();
        userProfilePicture = in.readString();
        userRole = in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(userEmail);
        dest.writeString(LoginType);
        dest.writeString(userProfilePicture);
        dest.writeString(userRole);
    }
}
