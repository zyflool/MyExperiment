package com.example.criminalintent.database;

/**
 * 定义schema的Java类
 */
public class CrimeDbSchema {

    /** 描述数据表的内部类
     * CrimeTable 内部类唯一的用途就是定义描述数据表元素的 String 常量。
     * 它的首个定义是数据库表名( CrimeTable.NAME )
     */
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        /**
         * 数据表字段
         */
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}