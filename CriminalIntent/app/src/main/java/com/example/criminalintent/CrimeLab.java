package com.example.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if ( sCrimeLab == null ) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
        //(1) 打开/data/data/com.bignerdranch.android.criminalintent/databases/crimeBase.db数据库;如果
        //不存在,就先创建crimeBase.db数据库文件。
        //(2) 如果是首次创建数据库,就调用 onCreate(SQLiteDatabase) 方法,然后保存最新的版本号。
        //(3) 如果已创建过数据库,首先检查它的版本号。
        // 如果 CrimeBaseHelper 中的版本号更高,就调用 onUpgrade(SQLiteDatabase, int, int) 方法升级。
    }

    public void addCrime(Crime c) {

    }

    public List<Crime> getCrimes() {
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id) {

        return null;
    }

    public int getCrimeIndex (Crime crime) {
        return mCrimes.indexOf(crime);
    }
}
