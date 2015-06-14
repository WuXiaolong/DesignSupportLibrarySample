package com.example.wuxiaolongl.designsupportlibrarysample;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;


public class CoordinatorLayoutActivity extends AppCompatActivity {
    String[] dataList = {"NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout", "NavigationView", "TabLayout", "CoordinatorLayout"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("tab3"));

//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList));


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager = new GridLayoutManager(MyDownLoadActivity.this, 2);

        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        Activity activity;

        public RecyclerViewAdapter(Activity activity) {
            this.activity = activity;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.title.setText(dataList[position]);

        }

        @Override
        public int getItemCount() {
            return dataList.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView imgView;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView
                        .findViewById(R.id.title);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coordinator_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
