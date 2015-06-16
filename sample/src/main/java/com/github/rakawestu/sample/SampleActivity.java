package com.github.rakawestu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.rakawestu.searchtoolbar.SearchToolbar;
import com.github.rakawestu.searchtoolbar.SearchToolbarListener;

/**
 * @author rakawm
 */
public class SampleActivity extends AppCompatActivity implements SearchToolbarListener{

    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        SearchToolbar toolbar = (SearchToolbar)findViewById(R.id.search_toolbar);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.search_text);
        toolbar.setSearchToolbarListener(this);
    }

    @Override
    public void onSearch(String keyword) {
        text.setText(keyword);
    }

    @Override
    public void onClear() {
        text.setText("No text.");
    }
}
