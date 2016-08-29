package app.yakun.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Fire on 2016/7/16.
 */
public class NewFragment extends Fragment {
    private Button createOne;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_frag, container, false);
        createOne = (Button) v.findViewById(R.id.create);
        createOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Application app = new Application();
                AppLab.get(getActivity()).addApp(app);
                Intent intent = DetailPagerActivity.newIntent(getActivity(), 0);
                startActivity(intent);
            }
        });
        return v;
    }
}
