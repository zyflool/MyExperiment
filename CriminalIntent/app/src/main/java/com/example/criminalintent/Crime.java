package com.example.criminalintent;

import java.util.Date;
import java.util.UUID; //UUID 是Android框架里的Java工具类。有了它,产生唯一ID值就方便多了。在构造方法里,调用 UUID.randomUUID() 产生一个随机唯一ID值

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    public Crime () {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public void setId(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
