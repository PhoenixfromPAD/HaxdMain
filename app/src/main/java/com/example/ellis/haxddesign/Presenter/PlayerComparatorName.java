package com.example.ellis.haxddesign.Presenter;

import com.example.ellis.haxddesign.Model.Player;

import java.util.Comparator;

/**
 * Created by Randy Bruner on 5/19/17.
 */

public class PlayerComparatorName implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {

        return player1.getName().compareTo(player2.getName());
    }
}
