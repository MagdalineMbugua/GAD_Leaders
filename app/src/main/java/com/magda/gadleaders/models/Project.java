package com.magda.gadleaders.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String projectLink;

    public Project() {
    }

    public Project(String firstName, String lastName, String emailAddress, String projectLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.projectLink = projectLink;
    }

    protected Project(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        emailAddress = in.readString();
        projectLink = in.readString();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(emailAddress);
        dest.writeString(projectLink);
    }
}
