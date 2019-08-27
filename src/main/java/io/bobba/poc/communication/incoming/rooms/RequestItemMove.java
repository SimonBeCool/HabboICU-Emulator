package io.bobba.poc.communication.incoming.rooms;

import io.bobba.poc.communication.incoming.IIncomingEvent;
import io.bobba.poc.communication.outgoing.FurniRemoveComposer;
import io.bobba.poc.communication.outgoing.SerializeFloorItemComposer;
import io.bobba.poc.communication.protocol.ClientMessage;
import io.bobba.poc.core.gameclients.GameClient;
import io.bobba.poc.core.items.BaseItem;
import io.bobba.poc.core.rooms.*;
import io.bobba.poc.core.rooms.items.RoomItem;
import io.bobba.poc.core.rooms.users.RoomUser;

public class RequestItemMove implements IIncomingEvent {
	
    @Override
    public void handle(GameClient client, ClientMessage request) {
    	
    	int furniid = request.popInt();
        int x = request.popInt();
        int y = request.popInt();
        int rotation = request.popInt();
        
        RoomUser user = client.getUser().getCurrentRoomUser();
        if (user != null){
        	user.furniRemove(furniid);
        	user.furniMove(furniid, x, y, rotation);
        }
    }
}