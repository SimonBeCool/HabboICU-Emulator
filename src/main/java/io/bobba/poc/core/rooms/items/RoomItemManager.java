package io.bobba.poc.core.rooms.items;

import io.bobba.poc.BobbaEnvironment;
import io.bobba.poc.communication.outgoing.FurniRemoveComposer;
import io.bobba.poc.communication.outgoing.InventarComposer;
import io.bobba.poc.communication.outgoing.InventarItemRemoveComposer;
import io.bobba.poc.communication.outgoing.SerializeFloorItemComposer;
import io.bobba.poc.communication.outgoing.SerializeWallItemComposer;
import io.bobba.poc.core.Game;
import io.bobba.poc.core.gameclients.GameClient;
import io.bobba.poc.core.items.BaseItem;
import io.bobba.poc.core.items.BaseItemManager;
import io.bobba.poc.core.rooms.Room;
import io.bobba.poc.core.rooms.users.RoomUser;
import io.bobba.poc.misc.logging.LogLevel;
import io.bobba.poc.misc.logging.Logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomItemManager {
    private Map<Integer, RoomItem> floorItems;
    private Map<Integer, WallItem> wallItems;
    private Room room;
    private BaseItemManager base;
	private double z;
	public RoomItemManager(Room room) {
        this.room = room;
        floorItems = new HashMap<>();
        wallItems = new HashMap<>();
    }

    public RoomItem getItem(int id) {
        if (floorItems.containsKey(id))
            return floorItems.get(id);
        if (wallItems.containsKey(id))
            return wallItems.get(id);
        return null;
    }

    public List<RoomItem> getFloorItems() {
        return new ArrayList<>(floorItems.values());
    }

    public List<WallItem> getWallItems() {
        return new ArrayList<>(wallItems.values());
    }
    
    // ADD FLOOR ITEM
    
    public void addFloorItemToRoom(int id, int x, int y, double z, int rot, int state, BaseItem baseItem) {
    	if (getItem(id) == null) {
            floorItems.put(id, new RoomItem(id, x, y, z, rot, state, room, baseItem));
        } else {
        	floorItems.put(id, new RoomItem(id, x, y, z, rot, state, room, baseItem));
        }
    	room.getGameMap().addItemToMap(floorItems.get(id));
        room.sendMessage(new SerializeFloorItemComposer(floorItems.get(id)));
        room.getRoomUserManager().updateUserStatusses();
    }
    
    // ADD WALL ITEM
    
    public void addWallItemToRoom(int id, int x, int y, int rot, int state, BaseItem baseItem) {
        if (getItem(id) == null) {
            wallItems.put(id, new WallItem(id, x, y, rot, state, room, baseItem));
            room.sendMessage(new SerializeWallItemComposer(wallItems.get(id)));
        } else {
        	wallItems.put(id, new WallItem(id, x, y, rot, state, room, baseItem));
            room.sendMessage(new SerializeWallItemComposer(wallItems.get(id)));
        }
    }
    
    // USER ON FURNI EVENT

    public void furniInteract(RoomUser user, int itemId) {
        RoomItem item = getItem(itemId);
        if (item != null) {
            item.getInteractor().onTrigger(user, true);
        }
    }
    
    // MOVE A ITEM
    
    public void moveTo(int id, int x, int y, int rotation) {
    	 RoomItem item = getItem(id);
    	 room.getRoomItemManager().addFloorItemToRoom(id, x, y, item.getZ(), rotation, item.getState(), item.getBaseItem());
    }
    
    // REMOVE ITEMS
    
	public void removeItem(int id) {
        RoomItem item = getItem(id);
        if (item != null) {
            if (item instanceof WallItem) {
                wallItems.remove(item);
            } else {
                floorItems.remove(item);
                room.getGameMap().removeItemFromMap(item);
                room.getRoomUserManager().updateUserStatusses();
            }
            room.sendMessage(new FurniRemoveComposer(id));
        }
    }
    
    // REMOVE ALL ITEMS

    public void removeAllFurniture() {
        List<Integer> items = new ArrayList<>();
        items.addAll(floorItems.keySet());
        items.addAll(wallItems.keySet());
        for (int itemId : items) {
        	RoomItem iteme = getItem(itemId);
        	removeItem(iteme.getId());
        }
    }
    
    // INVENTAR // TODO: find a better way to init a inventory item 
    
    public void InventoryItems(GameClient client, int id, int x, int y, double z, int rot, int state, BaseItem baseItem, String type) {
    	if(type != "W") {
    		floorItems.put(id, new RoomItem(id, x, y, z, rot, state, room, baseItem));
        	client.sendMessage(new InventarComposer(id, baseItem.getBaseId(), 0, type));
        	room.getGameMap().addItemToMap(floorItems.get(id));
            room.sendMessage(new SerializeFloorItemComposer(floorItems.get(id)));
    	} else {
    		wallItems.put(id, new WallItem(id, x, y, rot, state, room, baseItem));
        	client.sendMessage(new InventarComposer(id, baseItem.getBaseId(), 0, type));
        	room.getGameMap().addItemToMap(wallItems.get(id));
        	room.sendMessage(new SerializeWallItemComposer(wallItems.get(id)));
    	}
        room.getRoomUserManager().updateUserStatusses();
    	room.getRoomItemManager().removeItem(id);
    }
    
    // ADD A ITEM FROM INVENTORY TO FLOOR OR WALL
    
    public void addTo(int id, int x, int y, int rotation) {
   	 	RoomItem item = getItem(id);
   	 	if (item != null) {
   	 		if(item instanceof WallItem) {
   	 			room.getRoomItemManager().InvRemove(id);
   	 			room.getRoomItemManager().addWallItemToRoom(id, x, y, rotation, item.getState(), item.getBaseItem());
	 		} else {
	 			room.getRoomItemManager().InvRemove(id);
	 			room.getRoomItemManager().addFloorItemToRoom(id, x, y, item.getZ(), rotation, item.getState(), item.getBaseItem());
	 		}
   	 	}
    }
    
    // REMOVE A INVENTORY ITEM
    
    public void InvRemove(int id) {
    	room.sendMessage(new InventarItemRemoveComposer(id));
    }

    public void onCycle() {
    }

	
}
