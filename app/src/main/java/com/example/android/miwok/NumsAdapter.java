package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import android.media.MediaPlayer;


public class NumsAdapter extends ArrayAdapter<Words> {
    public int mColorRsId;
    private MediaPlayer mediaPlayer;


    public NumsAdapter(Activity context, ArrayList<Words> words, int colorRsId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorRsId = colorRsId;

    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {// Check if the existing view is being reused, otherwise inflate the view
    View listItemView = convertView;
        if(listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
                R.layout.list_item, parent, false);
    }

    // Get the {@link AndroidFlavor} object located at this position in the list
         Words currentWords = getItem(position);

    // Find the TextView in the list_item.xml layout with the ID version_name
         TextView numsTextView = (TextView) listItemView.findViewById(R.id.miw_text);
    // Get the version name from the current AndroidFlavor object and
    // set this text on the name TextView
        numsTextView.setText(currentWords.getmMiwokTranslation());

    // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defualtTextView = (TextView) listItemView.findViewById(R.id.en_text);
    // Get the version number from the current AndroidFlavor object and
    // set this text on the number TextView
        defualtTextView.setText(currentWords.getmDefaultTranslation());

        ImageView imgView = (ImageView) listItemView.findViewById(R.id.image);





        if (currentWords.hasImage()){

        imgView.setImageResource(currentWords.getmImgRscId());
        imgView.setVisibility(View.VISIBLE);}
        else {
            imgView.setVisibility(View.GONE);

        }
        View container = listItemView.findViewById(R.id.layout_container);
        int color = ContextCompat.getColor(getContext(),mColorRsId);
        container.setBackgroundColor(color);



    // Return the whole list item layout (containing 2 TextViews and an ImageView)
    // so that it can be shown in the ListView
        return listItemView;
}



}
