package com.github.javierugarte.searchtoolbar;


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

import com.github.javierugarte.searchtoolbar.utils.InputUtils;

public class SearchToolbar extends Toolbar {

    private EditText keyword;
    private ImageButton clear;
    private SearchToolbarListener listener;
    private LinearLayout containerSearch;

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

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.search_toolbar, this, true);

        keyword = (EditText)findViewById(R.id.search_keyword);
        clear = (ImageButton)findViewById(R.id.reset_button);
        containerSearch = (LinearLayout)findViewById(R.id.container_search);

        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword.setText("");
            }
        });

        showSearch();
    }

    public void setHint(int hint) {
        keyword.setHint(hint);
    }

    public void setHint(CharSequence hint) {
        keyword.setHint(hint);
    }

    private void showSearch() {
        containerSearch.setVisibility(VISIBLE);
        keyword.addTextChangedListener(textWatcher);
        keyword.requestFocus();
        InputUtils.showKeyboard(getContext(), keyword);
        if(listener!=null) {
            listener.onSearch(keyword.getText().toString());
        }
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

}
