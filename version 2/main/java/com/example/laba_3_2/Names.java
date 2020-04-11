package com.example.laba_3_2;

public final class Names {
    public static class con{
        public static final String TABLE = "Students";
        public static final String TEMP = "Tmp";
        public static final String ID = "ID";
        public static final String DATE = "DATE";
        public static final String LAST = "LAST";
        public static final String FIRST = "FIRST";
        public static final String MIDDLE = "MIDDLE";
        public static final String DB_CREATE_2 = "create table " + TEMP + "(" +
                ID + " integer primary key autoincrement," +
                LAST + " text," +
                FIRST + " text," +
                MIDDLE + " text," +
                DATE + " text);";
    }

}
