package com.weact.formulaire_parcel;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String fname;
    private String name;
    private String gender;
    private String email;

    public User(String fname, String name, String gender) { // CONSTRUCTOR WITH GIVEN ARGS : FNAME ; NAME ; GENDER
        super();
        this.fname = fname;
        this.name = name;
        this.gender = gender;
        this.email = fname + "." + name + "@ludus-academie.com";
    }

    protected User(Parcel in) {
        fname = in.readString();
        name = in.readString();
        gender = in.readString();
        email = in.readString();
    }

    //REQUIRED PARCELABLE METHOD
    public static final Creator<User> CREATOR = new Creator<User>() {

        //Create object from parcel
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        //Create array of objects
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //SETTERS
    public void setFName(String fname){ //FNAME
        this.fname = fname;
    }
    public void setName(String name){ //NAME
        this.name = name;
    }
    public void setGender(String gender){ //GENDER
        this.gender = gender;
    }
    //EMAIL HAS NO SETTER, AUTO SET BY CUSTRUCTOR

    //GETTERS
    public String getFName(){ //FNAME
        return fname;
    }
    public String getName(){ //NAME
        return name;
    }
    public String getGender(){ // GENDER
        return gender;
    }
    public String getEmail(){ //EMAIL
        return email;
    }

    //PARCEL METHODS
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(fname);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(email);
    }
}