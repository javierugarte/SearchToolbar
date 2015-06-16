package com.github.rakawestu.searchtoolbar;


import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.rakawestu.searchtoolbar.utils.InputUtils;

/**
 * @author rakawm
 */
public class SearchToolbar extends Toolbar {

    private EditText keyword;
    private ImageButton search, clear;
    private SearchToolbarListener listener;
    private TextView title;
    private LinearLayout container, containerSearch;

    public SearchToolbar(Context context) {
        super(context);
        init();
    }

    public SearchToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setSearchToolbarListener(SearchToolbarListener listener) {
        this.listener = listener;
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.search_toolbar, this, true);

        keyword = (EditText)findViewById(R.id.search_keyword);
        search = (ImageButton)findViewById(R.id.search_button);
        clear = (ImageButton)findViewById(R.id.reset_button);
        container = (LinearLayout)findViewById(R.id.container);
        containerSearch = (LinearLayout)findViewById(R.id.container_search);
        title = (TextView)findViewById(R.id.toolbar_title);

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearch();
            }
        });
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword.setText("");
            }
        });
    }

    private void showSearch(){
        containerSearch.setVisibility(VISIBLE);
        container.setVisibility(GONE);
        keyword.addTextChangedListener(textWatcher);
        keyword.requestFocus();
        setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
            }
        });
        InputUtils.showKeyboard(getContext(), keyword);
        if(listener!=null) {
            listener.onSearch(keyword.getText().toString());
        }
    }

    private void closeSearch(){
        container.setVisibility(VISIBLE);
        containerSearch.setVisibility(GONE);
        keyword.removeTextChangedListener(textWatcher);
        setNavigationIcon(null);
        setNavigationOnClickListener(null);
        InputUtils.hideKeyboard(getContext(), container);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(listener!=null) {
                if(s.length()==0) {
                    listener.onClear();
                } else {
                    listener.onSearch(s.toString());
                }
            }
        }
    };

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.title.setText(title);
    }
}
