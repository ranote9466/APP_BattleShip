package com.soen6441.battleship.model.ship;

import com.soen6441.battleship.view.util.Constants;

public class ShipFactory {
    public static Ship create(String name) {
        Ship ship = null;
        
        if (name.equals(Constants.SHIP_NAME_BATTLESHIP)) {
            ship = new Battleship(Constants.SHIP_NAME_BATTLESHIP);
        } else if (name.equals(Constants.SHIP_NAME_CARRIER)) {
            ship = new Carrier(Constants.SHIP_NAME_CARRIER);
        } else if (name.equals(Constants.SHIP_NAME_CRUISER)) {
            ship = new Cruiser(Constants.SHIP_NAME_CRUISER);
        } else if (name.equals(Constants.SHIP_NAME_DESTROYER)) {
            ship = new Destroyer(Constants.SHIP_NAME_DESTROYER);
        } else if (name.equals(Constants.SHIP_NAME_SUBMARINE)) {
            ship = new Submarine(Constants.SHIP_NAME_SUBMARINE);
        }
        return ship;
    }
}
