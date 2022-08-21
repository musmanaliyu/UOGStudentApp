package com.example.ass1;

import android.os.Parcel;
import android.os.Parcelable;

public class Transcript_Info implements Parcelable {

    private String name;
    private String ID;
    private String roll;
    private String address;
    private String DOB;
    private String reg;
    private String semester;
    private String contact;
    private String Bank;
    private String Challan;
    private String Amount;
    private String Date;

    public Transcript_Info(String name, String ID, String roll, String address, String DOB, String reg, String semester, String contact, String bank, String challan, String amount, String date) {
        this.name = name;
        this.ID = ID;
        this.roll = roll;
        this.address = address;
        this.DOB = DOB;
        this.reg = reg;
        this.semester = semester;
        this.contact = contact;
        this.Bank = bank;
        this.Challan = challan;
        this.Amount = amount;
        this.Date = date;
    }

    protected Transcript_Info(Parcel in) {
        name = in.readString();
        ID = in.readString();
        roll = in.readString();
        address = in.readString();
        DOB = in.readString();
        reg = in.readString();
        semester = in.readString();
        contact = in.readString();
        Bank = in.readString();
        Challan = in.readString();
        Amount = in.readString();
        Date = in.readString();
    }

    public static final Creator<Transcript_Info> CREATOR = new Creator<Transcript_Info>() {
        @Override
        public Transcript_Info createFromParcel(Parcel in) {
            return new Transcript_Info(in);
        }

        @Override
        public Transcript_Info[] newArray(int size) {
            return new Transcript_Info[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getChallan() {
        return Challan;
    }

    public void setChallan(String challan) {
        Challan = challan;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ID);
        dest.writeString(roll);
        dest.writeString(address);
        dest.writeString(DOB);
        dest.writeString(reg);
        dest.writeString(semester);
        dest.writeString(contact);
        dest.writeString(Bank);
        dest.writeString(Challan);
        dest.writeString(Amount);
        dest.writeString(Date);
    }
}
