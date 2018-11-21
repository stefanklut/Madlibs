package com.example.stefan.stefanklut_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class DisplayStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);

        // Get the intent and retrieve the story
        Intent intent = getIntent();
        Story retrievedStory = (Story) intent.getSerializableExtra("madlib story");

        // Display the story to the user in Html mode
        TextView storyDisplay = findViewById(R.id.textViewStoryDisplay);
        storyDisplay.setText(Html.fromHtml(retrievedStory.toString(), Html.FROM_HTML_MODE_LEGACY));
    }

    public void newStory(View view) {
        // Go to the story selection screen while clearing the activities in-between
        Intent intent = new Intent(DisplayStoryActivity.this, StorySelectorActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Go to the story selection screen while clearing the activities in-between
        Intent intent = new Intent(DisplayStoryActivity.this, StorySelectorActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
