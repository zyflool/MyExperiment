package com.example.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.criminalintent.Crime;
import com.example.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

/**
 * 使用 CrimeCursorWrapper 类,可直接从 CrimeLab 中取得 List<Crime> 。
 * 大致思路无外乎将查询返回的cursor封装到 CrimeCursorWrapper 类中,
 * 然后调用 getCrime()方法遍历取出 Crime 。
 */

/**
 * 使用 CursorWrapper 创建可复用的专用 Cursor 子类。
 * 用 CursorWrapper 封装 Cursor 的对象,然后再添加有用的扩展方法。
 * 这样封装的目的就是定制新方法,以方便操作内部 Cursor 。
 */
public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper (Cursor cursor) {
        super(cursor);
    }

    //获取相关字段值
    public Crime getCrime () {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return crime;
    }
}
