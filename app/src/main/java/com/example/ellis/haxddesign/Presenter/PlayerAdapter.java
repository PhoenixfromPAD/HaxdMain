package com.example.ellis.haxddesign.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ellis.haxddesign.Model.Player;
import com.example.ellis.haxddesign.R;

import java.util.List;

/**
 * Created by Randy Bruner on 5/17/17.
 */

public class PlayerAdapter extends ArrayAdapter<Player>{

    public PlayerAdapter (Context context, List<Player> players) {
        super(context, 0, players);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_player, null);
        }
        Player player = getItem(position);

        //wire up our view
        TextView nameTextEdit = (TextView) convertView.findViewById(R.id.itemPlayer_textView_name);
        TextView otherTextView = (TextView) convertView.findViewById(R.id.itemPlayer_textView_other);
        TextView levelTextView = (TextView) convertView.findViewById(R.id.itemPlayer_textView_level);

        //put text
        nameTextEdit.setText(player.name);
        otherTextView.setText(""+player.busy);
        levelTextView.setText(""+player.hcr);

        return convertView;
    }
}
