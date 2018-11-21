package com.example.stefan.stefanklut_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class StorySelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_selector);

        ArrayList<StoryInfo> stories = new ArrayList<StoryInfo>(){{
            add(new StoryInfo("Simple", R.raw.madlib0_simple));
            add(new StoryInfo("Tarzan", R.raw.madlib1_tarzan));
            add(new StoryInfo("University", R.raw.madlib2_university));
            add(new StoryInfo("Clothes", R.raw.madlib3_clothes));
            add(new StoryInfo("Dance", R.raw.madlib4_dance));
        }};

        // Create the friendsAdapter
        StoryAdapter adapter = new StoryAdapter(this, R.layout.grid_item, stories);

        // Find the grid view and add the Adapter and OnItemClickListener
        GridView gv = findViewById(R.id.gridView);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new OnItemClickListener());
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Retrieve what story was clicked
            StoryInfo clickedStoryInfo = (StoryInfo) parent.getItemAtPosition(position);

            // Open new activity
            Intent intent = new Intent(StorySelectorActivity.this, InputActivity.class);
            intent.putExtra("clicked story", clickedStoryInfo);
            startActivity(intent);
        }
    }
}
