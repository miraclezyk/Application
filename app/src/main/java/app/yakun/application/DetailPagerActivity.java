package app.yakun.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by Fire on 2016/7/12.
 */
public class DetailPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Application> apps;

    public static Intent newIntent(Context context, int id){
        Intent intent = new Intent(context, DetailPagerActivity.class);
        intent.putExtra("index", id);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pager);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        apps = AppLab.get(this).getApps();
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return DetailFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return apps.size();
            }
        });
        viewPager.setCurrentItem(getIntent().getIntExtra("index",0));
    }
}
