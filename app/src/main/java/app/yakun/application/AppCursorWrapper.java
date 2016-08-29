package app.yakun.application;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;

/**
 * Created by Fire on 2016/7/15.
 */
public class AppCursorWrapper extends CursorWrapper {
    public AppCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Application getApp(){
        int id = getInt(getColumnIndex("id"));
        String company = getString(getColumnIndex("company"));
        String position = getString(getColumnIndex("position"));
        int interviewed = getInt(getColumnIndex("interviewed"));
        long date = getLong(getColumnIndex("date"));
        String note = getString(getColumnIndex("note"));

        Application app = new Application();
        app.setId(id);
        app.setCompany(company);
        app.setPosition(position);
        app.setIfInterviewed(interviewed != 0);
        app.setDate(new Date(date));
        app.setNote(note);

        return app;
    }
}
