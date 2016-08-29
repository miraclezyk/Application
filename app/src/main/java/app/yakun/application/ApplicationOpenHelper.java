package app.yakun.application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fire on 2016/7/15.
 */
public class ApplicationOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_APPLICATION = "create table Application ("
            + "id integer primary key autoincrement,"
            + "company, position, interviewed, date, note)";

    public ApplicationOpenHelper(Context context) {
        super(context, "application.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_APPLICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
