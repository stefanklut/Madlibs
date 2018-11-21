package com.example.stefan.stefanklut_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        // Check if there was already a story being written
        if (savedInstanceState != null) {
            story = (Story) savedInstanceState.getSerializable("story");
        } else {
            // create an input stream for the new story class
            InputStream is = getResources().openRawResource(retrievedStoryInfo.getRawId());
            story = new Story(is);
        }

        // Update the info about which story the user is playing
        TextView info = findViewById(R.id.textViewInfo);
        String infoText = "Currently playing: " + retrievedStoryInfo.getName();
        info.setText(infoText);

        // Update the values of the text fields on screen
        updateView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the story class
        outState.putSerializable("story", story);
    }

    private void updateView() {
        // Retrieve the text fields on screen
        TextView remaining = findViewById(R.id.textViewRemaining);
        TextView instruction = findViewById(R.id.textViewInstruction);
        EditText input = findViewById(R.id.editTextInput);

        // Create the strings that will be put in the text fields
        String nextPlaceholder = story.getNextPlaceholder().toLowerCase();
        String remainingText = story.getPlaceholderRemainingCount() + " word(s) remaining";
        String instructionText = "please type a/an " + nextPlaceholder;

        // Set the text fields to the strings
        remaining.setText(remainingText);
        instruction.setText(instructionText);
        input.setHint(nextPlaceholder);
    }

    public void confirmInput(View view) {
        // Get the edit text field and retrieve the typed string
        EditText input = findViewById(R.id.editTextInput);
        String inputText = input.getText().toString();

        // Display a toast if nothing was typed and return so that the value is not used
        if (inputText.isEmpty()) {
            String errorMessage = "Please provide a " + story.getNextPlaceholder().toLowerCase() +
                                  " (Note that this can't be empty)";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return;
        }

        // Clear the edit text field
        input.setText("");

        // add the typed text to the story
        story.fillInPlaceholder(inputText);

        // If it was the final word that needed to be typed open the display activity
        if (story.isFilledIn()) {
            Intent intent = new Intent(InputActivity.this, DisplayStoryActivity.class);
            intent.putExtra("madlib story", story);
            startActivity(intent);
            return;
        }

        // Update the values of the text fields on screen
        updateView();
    }
}
