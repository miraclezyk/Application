package app.yakun.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Fire on 2016/7/11.
 */
public class ListActivity extends AppCompatActivity {
    private AppLab appLab;
    private FragmentManager fm;
    private Fragment fragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_container);
        appLab = AppLab.get(this);
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.list_container);
        if(fragment == null){
            fragment = appLab.getApps().size() > 0 ? new ListFragment():new NewFragment();
            fm.beginTransaction().add(R.id.list_container, fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(appLab.getApps().size() > 0){
            if(fm.findFragmentById(R.id.list_frag) == null){
                fragment = new ListFragment();
                fm.beginTransaction().replace(R.id.list_container, fragment).commit();
            }
        }else{
            if(fm.findFragmentById(R.id.new_frag) == null){
                fragment = new NewFragment();
                fm.beginTransaction().replace(R.id.list_container, fragment).commit();
            }
        }
    }
}
