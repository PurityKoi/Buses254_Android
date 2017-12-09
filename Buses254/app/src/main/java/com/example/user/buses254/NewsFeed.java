package com.example.user.buses254;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import Adapter.FeedAdapter;
import Common.ErrorTracker;
import Common.HTTPDataHandler;
import Common.RecyclerItemClickListener;
import Model.RssObject;

public class NewsFeed extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    RssObject rssObject;
    private SwipeRefreshLayout mSwipeLayout;
    FeedAdapter feedAdapter;

    //RSS Link
    private final String RSS_Link = "http://rss.cnn.com/rss/edition_africa.rss";
    private final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        toolbar = (Toolbar) findViewById(R.id.newsfeedToolbar);
        toolbar.setTitle("News");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.newsfeedRecyclerV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                feedAdapter.OpenObject(position);
            }
        }));


        loadRss();

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRss();
            }

        });


    }


    private void loadRss() {
        AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog = new ProgressDialog(NewsFeed.this);

            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                    if (s == ErrorTracker.Connection_Error){
                        Toast.makeText(getApplicationContext(), " Connection Error", Toast.LENGTH_LONG).show();

                    }else if (s == ErrorTracker.IO_Error){
                        Toast.makeText(getApplicationContext(), " Read Error", Toast.LENGTH_LONG).show();

                    }else if (s == ErrorTracker.Response_Error) {
                        Toast.makeText(getApplicationContext(), " Response Error", Toast.LENGTH_LONG).show();

                    }else if ( s == ErrorTracker.Wrong_URL_Format) {
                        Toast.makeText(getApplicationContext(), " Wrong URL Format", Toast.LENGTH_LONG).show();

                    }

                else {
                        progressDialog.dismiss();
                        rssObject = new Gson().fromJson(s, RssObject.class);
                        feedAdapter = new FeedAdapter(rssObject, getBaseContext());
                        recyclerView.setAdapter(feedAdapter);
                        feedAdapter.notifyDataSetChanged();
                    }
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_Link);
        loadRSSAsync.execute(url_get_data.toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rss_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh)
            loadRss();
        return true;
    }
}
