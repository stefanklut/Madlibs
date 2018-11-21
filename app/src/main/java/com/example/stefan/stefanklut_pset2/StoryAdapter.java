package com.example.stefan.stefanklut_pset2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class StoryAdapter extends ArrayAdapter<StoryInfo> {
    private ArrayList<StoryInfo> stories;

    public StoryAdapter(Context context, int resource, ArrayList<StoryInfo> objects) {
        super(context, resource, objects);
        stories = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        // Variables for the views in the convert view
        TextView name = convertView.findViewById(R.id.textViewName);

        // Depending on the position set the name and avatar to the friend
        StoryInfo storyInfo = stories.get(position);
        name.setText(storyInfo.getName());

        return convertView;
    }
}
