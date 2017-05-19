package com.example.ellis.haxddesign.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.ellis.haxddesign.Model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Randy Bruner on 5/17/17.
 */

public class PlayerFragment extends ListFragment {

    static final String EXTRA_OBJECTID = "objectid";

    public static final String TAG = "PlayerFragment";
    private ArrayList<Player> players;
    PlayerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Backendless.initApp(getContext(), "73B8E514-FB28-B17E-FF84-3BF6B88BD000",
            "929DA8BC-4FDE-CFAF-FF66-0B4B156DCB00", "v1");

        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        //create list of players
        players = new ArrayList<Player>();
        populateList();

        //set listView adapter
        return rootView;
    }

    private void populateList() {
        Backendless.Persistence.of( "Player" ).find( new AsyncCallback<BackendlessCollection<Map>>(){
            @Override
            public void handleResponse( BackendlessCollection<Map> foundPlayers )
            {
                //every loaded object from the "Player" table is now an individual java.util.Map
                List<Map> playersList = foundPlayers.getData();
                for(Map m : playersList) {
                    Player player = new Player((String)m.get("name"));
                    player.setBusy((boolean)m.get("busy"));
                    player.setHcr((int) m.get("hcr"));
                    player.objectId = (String) m.get("objectId");
                    player.ownerId = (String) m.get("ownerId");

                    Log.d(TAG, "ObjectId: "+player.objectId+" OwnerId: "+player.ownerId);

                    players.add(player);
                }

                //Remove user and busy players
                BackendlessUser user = Backendless.UserService.CurrentUser();
                Iterator<Player> iter = players.iterator();
                while (iter.hasNext()) {
                    Player p = iter.next();

                    if (p.busy) {
                        iter.remove();
                    } else if (p.ownerId.equals(user.getObjectId())) {
                        iter.remove();
                    }
                }

                //sort player list
                Collections.sort(players, new PlayerComparatorName());

                //fill custom adapter
                adapter = new PlayerAdapter(getActivity(), players);
                setListAdapter(adapter);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, Log and Toast the Error
                Toast.makeText(getContext(), ""+fault.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "handleFault: "+fault.getMessage());
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //get object that was clicked
        Player p = (Player) l.getItemAtPosition(position);
        //pull out info from object
        String objectId = p.objectId;
        //store info in extras
        Intent i = new Intent(getContext(), HackerActivity.class);
        i.putExtra(EXTRA_OBJECTID, objectId);
        //start activity
        startActivity(i);
    }
}
