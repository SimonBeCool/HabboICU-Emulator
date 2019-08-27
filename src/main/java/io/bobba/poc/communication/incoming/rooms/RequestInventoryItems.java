package io.bobba.poc.communication.incoming.rooms;

import io.bobba.poc.BobbaEnvironment;
import io.bobba.poc.communication.incoming.IIncomingEvent;
import io.bobba.poc.communication.outgoing.InventarComposer;
import io.bobba.poc.communication.outgoing.SerializeFloorItemComposer;
import io.bobba.poc.communication.protocol.ClientMessage;
import io.bobba.poc.core.Game;
import io.bobba.poc.core.gameclients.GameClient;
import io.bobba.poc.core.rooms.*;
import io.bobba.poc.core.rooms.items.RoomItem;
import io.bobba.poc.core.rooms.users.RoomUser;


public class RequestInventoryItems implements IIncomingEvent {
    @Override
    public void handle(GameClient client, ClientMessage request) {
    	 
    	Room room = client.getUser().getCurrentRoom();
    	RoomUser user = client.getUser().getCurrentRoomUser();
         if (user != null){	
        	 if(user.Inventar() != true) {
        		// Floor 
     			room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("LT_skull"), "F");
     			room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("stories_shakespeare_tree"), "F");
     			room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("anc_artifact3"), "F");
     			room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("anc_waterfall"), "F");
     			room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("anc_talltree"), "F");
        		room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("club_sofa"), "F");
        		room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("stand_polyfon_z"), "F");
        		room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("lt_patch"), "F");
        		room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("lt_stone2"), "F");
        		room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("lt_gate"), "F");
        		room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("footylamp"), "F"); 
        		room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("country_grass"), "F");  
        		
        		// Wall
        		 for (int i = 0; i < 12; i++) {
        			 room.getRoomUserManager().InventoryItem(client, Game.itemId++, 6, 10, 0.0, 1, 0, BobbaEnvironment.getInstance().getGame().getItemManager().findItem("lt_jngl_wall"), "W");  
        	        }
        		room.getRoomUserManager().InventoryGiven(client, true);
        	 }
        	}
         return;
    }
}
