package com.example.stefan.stefanklut_pset2;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class InputActivity extends AppCompatActivity {

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Get the intent and retrieve the story info
        Intent intent = getIntent();
        StoryInfo retrievedStoryInfo = (StoryInfo) intent.getSerializableExtra("clicked story");

        InputStream is = getResources().openRawResource(retrievedStoryInfo.getRawId());
        if (savedInstanceState != null) {
            story = (Story) savedInstanceState.getSerializable("story");
        } else {
            story = new Story(is);
        }

        TextView info = findViewById(R.id.textViewInfo);
        String infoText = "Currently playing: " + retrievedStoryInfo.getName();
        info.setText(infoText);

        updateView();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);
    }

    private void updateView() {
        TextView remaining = findViewById(R.id.textViewRemaining);
        TextView instruction = findViewById(R.id.textViewInstruction);
        EditText input = findViewById(R.id.editTextInput);

        String nextPlaceholder = story.getNextPlaceholder().toLowerCase();
        String remainingText = story.getPlaceholderRemainingCount() + " word(s) remaining";
        String instructionText = "please type a/an " + nextPlaceholder;

        remaining.setText(remainingText);
        instruction.setText(instructionText);
        input.setHint(nextPlaceholder);
    }

    public void confirmInput(View view) {
        EditText input = findViewById(R.id.editTextInput);
        String inputText = input.getText().toString();
//        Log.d("madlib", "confirmInput: "+inputText);
        if (inputText.isEmpty()) {
            String errorMessage = "Please provide a " + story.getNextPlaceholder().toLowerCase() +
                                  " (Note that this can't be empty)";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return;
        }
        input.setText("");

        story.fillInPlaceholder(inputText);

        if (story.isFilledIn()) {
            Intent intent = new Intent(InputActivity.this, DisplayStoryActivity.class);
            intent.putExtra("madlib story", story);
            startActivity(intent);
        }

        updateView();
    }
}
