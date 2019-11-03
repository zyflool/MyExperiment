package com.example.criminalintent;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.criminalintent.database.CrimeCursorWrapper;
import com.example.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.io.File;
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
        ContentValues values = getContentValues(c);

        //第一个参数是数据表名(CrimeTable.NAME),第三个是要写入的数据。
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    //遍历查询取出所有的crime,返回 Crime 数组对象
    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        /**
         * 数据库cursor之所以被称为cursor,是因为它内部就像有根手指似的,总是指向查询的某个地方。
         * 因此,要从cursor中取出数据,首先要调用 moveToFirst() 方法移动虚拟手指指向第一个元素。
         * 读取行记录后,再调用 moveToNext() 方法,读取下一行记录,
         * 直到 isAfterLast() 说没有数据可取为止。
         * 最后别忘了调用 Cursor 的 close() 方法关闭它,否则会出错:轻则应用报错,重则应用崩溃。
         */
        try {
            cursor.moveToFirst();
            while ( !cursor.isAfterLast() ) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    //返回指向某个具体位置的 File 对象
    public File getPhotoFile(Crime crime) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, crime.getPhotoFilename());
    }

    //CrimeLab.getCrime(UUID) 方法类似于 getCrimes() 方法
    // 唯一区别就是,它只需要取出已存在的首条记录
    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime (Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        //创建 where 子句(第三个参数),然后指定 where子句中的参数值( String[] 数组参数)
        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    /**
     * 参数 table 是要查询的数据表。
     * 参数 columns 指定要依次获取哪些字段的值。
     * 参数 where 和 whereArgs 的作用与 update(...) 方法中的一样。
     */
    //Cursor 是个表数据处理工具。其功能就是封装数据表中的原始字段值
    //让 queryCrimes(...) 方法返回 CrimeCursorWrapper 对象
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }

    /**
     * 负责处理数据库写入和更新操作的辅助类是 ContentValues 。
     * 它是一个键值存储类,只能用于处理SQLite数据。
     * 将 Crime 记录转换为 ContentValues ,实际就是在 CrimeLab 中创建 ContentValues 实例。
     * ContentValues 的键就是数据表字段
     */
    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());

        return values;
    }

}
