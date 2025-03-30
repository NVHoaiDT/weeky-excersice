package com.example.rview_indicator;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IconAdapter adapter;
    private List<IconModel> arrayList;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcIcon);

        arrayList = new ArrayList<>();
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 1"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 2"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 3"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 4"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 5"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 6"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 7"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 8"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 10"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 11"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 12"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 13"));
        arrayList.add(new IconModel(R.drawable.ic_clipboard, "test 14"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new IconAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new LinePagerIndicatorDecoration());

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterListener(s);
                return false;
            }
        });
    }

    private void filterListener(String text){
        List<IconModel> filterList = new ArrayList<>();
        for(IconModel item : arrayList){
            if(item.getDesc().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this, " no ", Toast.LENGTH_SHORT).show();
        }else{
            adapter.setListenerList(filterList);
        }
    }

}
