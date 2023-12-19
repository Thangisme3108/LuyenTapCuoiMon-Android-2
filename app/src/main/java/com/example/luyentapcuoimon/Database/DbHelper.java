package com.example.luyentapcuoimon.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
        public static final String Db_name = "CSDL";

        public DbHelper (Context context) {
            super(context, Db_name, null, 1);
        }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_ds_thi = "create table ds_thi (id integer primary key autoincrement, ngay_thi text, ca text, phong text, ten_mon text)";
        sqLiteDatabase.execSQL(tb_ds_thi);

        String data = "insert into ds_thi (ngay_thi, ca, phong, ten_mon) values ('01/12/2023', 'Ca 2', 'L206', 'Android 2')";
        String data2 = "insert into ds_thi (ngay_thi, ca, phong, ten_mon) values ('05/12/2023', 'Ca 3', 'P202', 'Android 3')";

        sqLiteDatabase.execSQL(data);
        sqLiteDatabase.execSQL(data2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("drop table if exists ds_thi");
            onCreate(sqLiteDatabase);
        }
    }
}
