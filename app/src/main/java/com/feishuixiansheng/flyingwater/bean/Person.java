package com.feishuixiansheng.flyingwater.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author dupengfei
 * @create 2019/2/25 0025
 * @Describe
 */

public class Person implements Parcelable {
    private String mName;

    public Person(String name) {
        mName = name;
    }

    protected Person(Parcel in) {
        mName = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
    }

    @Override
    public String toString() {
        return "Person{" + "mName='" + mName + '\'' + '}';
    }
}

