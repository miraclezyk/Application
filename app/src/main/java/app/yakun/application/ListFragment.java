package app.yakun.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fire on 2016/7/11.
 */
public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private AppLab appLab;

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private int id;
        public TextView textView;
        public CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView)itemView.findViewById(R.id.title);
            checkBox = (CheckBox)itemView.findViewById(R.id.inter);
        }

        @Override
        public void onClick(View view) {
            Intent intent = DetailPagerActivity.newIntent(getActivity(), id);
            startActivity(intent);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {

            this.id = id;
        }
    }

    private class ListAdapter extends RecyclerView.Adapter<ViewHolder>{
        private List<Application> list;
        public ListAdapter(List<Application> list){
            this.list = list;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setId(position);
            Application app = list.get(position);
            holder.textView.setText((position+1) + ". " + app.getCompany());
            holder.checkBox.setChecked(app.getIfInterviewed());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setApps(List<Application> apps){
            list = apps;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appLab = AppLab.get(getActivity());
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_frag, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        if(adapter == null){
            adapter = new ListAdapter(appLab.getApps());
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setApps(appLab.getApps());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_app:
                Application app = new Application();
                appLab.addApp(app);
                Intent intent = DetailPagerActivity.newIntent(getActivity(), 0);
                startActivity(intent);
                return true;
            case R.id.search:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Search");
                dialog.setMessage("Please enter the company name:");
                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                input.setSingleLine();
                dialog.setView(input);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int index = appLab.getIndexByName(input.getText().toString().trim());
                        if(index == -1){
                            AlertDialog.Builder noMatch = new AlertDialog.Builder(getActivity());
                            noMatch.setTitle("No record matches.");
                            noMatch.setPositiveButton("OK", null);
                            noMatch.show();
                        }else{
                            recyclerView.getLayoutManager().scrollToPosition(index);
                        }
                    }
                });
                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
