package com.github.javierugarte.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.javierugarte.searchtoolbar.SearchToolbar;
import com.github.javierugarte.searchtoolbar.SearchToolbarListener;


public class SampleActivity extends AppCompatActivity implements SearchToolbarListener {

    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        text = (TextView)findViewById(R.id.search_text);

        SearchToolbar toolbar = (SearchToolbar)findViewById(R.id.search_toolbar);
        toolbar.setTitle(getTitle());
        toolbar.setSearchToolbarListener(this);
        toolbar.setHint(R.string.hint_search);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onSearch(String keyword) {
        text.setText(keyword);
    }

    @Override
    public void onClear() {
        text.setText("No text.");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
