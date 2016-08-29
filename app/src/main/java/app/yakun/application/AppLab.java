package app.yakun.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Fire on 2016/7/12.
 */
public class AppLab {
    private static final String TABLE_NAME = "Application";
    private static AppLab appLab;
    private List<app.yakun.application.Application> applications;
    private Context mContext;
    private SQLiteDatabase db;
    public static AppLab get(Context context){
        if(appLab == null){
            appLab = new AppLab(context);
        }
        return appLab;
    }
    private AppLab(Context context){
        mContext = context.getApplicationContext();
        db = new ApplicationOpenHelper(mContext).getWritableDatabase();
        /*apps = new ArrayList<>();
        for(int i=0; i<30; i++){
            Application application = new Application("Company#"+i, "developer#"+i);
            apps.add(application);
        }*/
    }
    public List<Application> getApps(){
        applications = new ArrayList<>();
        AppCursorWrapper cursor = queryApps(null, null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                applications.add(cursor.getApp());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        if(applications.size() > 1){
            Collections.sort(applications, new Comparator<Application>() {
                @Override
                public int compare(Application application, Application t1) {
                    return t1.getDate().compareTo(application.getDate());
                }
            });
        }
        return applications;
    }
    public int getIndexByName(String name){
        for(int i=0; i<applications.size(); i++){
            if(applications.get(i).getCompany().equals(name))
                return i;
        }
        return -1;
    }
    public Application getApp(int id){
        return applications.get(id);
    }
    public void addApp(Application a){
        ContentValues values = getContentValues(a);
        db.insert(TABLE_NAME, null, values);
    }
    public void deleteApp(Application a){
        int id = a.getId();
        db.delete(TABLE_NAME, "id=?", new String[]{Integer.toString(id)});
    }
    public void updateApp(Application a){
        int id = a.getId();
        ContentValues values = getContentValues(a);
        db.update(TABLE_NAME, values, "id=?", new String[]{Integer.toString(id)});
    }
    public static ContentValues getContentValues(Application app){
        ContentValues values = new ContentValues();
        values.put("company", app.getCompany());
        values.put("position", app.getPosition());
        values.put("interviewed", app.getIfInterviewed() ? 1:0);
        values.put("date", app.getDate().getTime());
        values.put("note", app.getNote());
        return values;
    }
    private AppCursorWrapper queryApps(String whereClause, String[] whereArgs){
        Cursor cursor = db.query(TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        return new AppCursorWrapper(cursor);
    }
}
