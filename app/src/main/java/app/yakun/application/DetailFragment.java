package app.yakun.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;

/**
 * Created by Fire on 2016/7/11.
 */
public class DetailFragment extends Fragment {
    private Application app;
    private EditText company;
    private EditText position;
    private Button date;
    private CheckBox interviewed;
    private Button confirm;
    private Button delete;
    private EditText note;
    private AppLab appLab;
    public static DetailFragment newInstance(int id){
        Bundle args = new Bundle();
        args.putInt("index", id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getArguments().getInt("index");
        appLab = AppLab.get(getActivity());
        app = appLab.getApp(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_frag, container, false);
        company = (EditText)v.findViewById(R.id.company);
        position = (EditText)v.findViewById(R.id.position);
        date = (Button)v.findViewById(R.id.date);
        interviewed = (CheckBox)v.findViewById(R.id.interview);
        delete = (Button)v.findViewById(R.id.delete);
        confirm = (Button)v.findViewById(R.id.confirm);
        note = (EditText)v.findViewById(R.id.note);

        company.setText(app.getCompany());
        position.setText(app.getPosition());
        note.setText(app.getNote());
        String str = "Created on " + new SimpleDateFormat("MM/dd/yyyy").format(app.getDate()).toString();
        date.setText(str);
        date.setEnabled(false);
        interviewed.setChecked(app.getIfInterviewed());
        interviewed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                app.setIfInterviewed(b);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appLab.deleteApp(app);
                getActivity().finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        app.setCompany(company.getText().toString());
        app.setPosition(position.getText().toString());
        app.setNote(note.getText().toString());
        if(app.getCompany().trim().equals("")){
            appLab.deleteApp(app);
        }else{
            appLab.updateApp(app);
        }
    }
}
