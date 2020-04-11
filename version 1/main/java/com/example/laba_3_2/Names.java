package com.example.laba_3_2;

public final class Names {
    public static class con{
        public static final String TABLE = "Students";
        public static final String ID = "ID";
        public static final String FIO = "FIO";
        public static final String DATE = "DATE";
        public static final String DB_CREATE = "create table " + TABLE + "("
                + ID + " integer primary key autoincrement, "
                + FIO + " text, "
                + DATE + " text" + ");";
    }

}
