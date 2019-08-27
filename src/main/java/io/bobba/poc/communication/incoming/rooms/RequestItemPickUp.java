package io.bobba.poc.communication.incoming.rooms;

import io.bobba.poc.communication.incoming.IIncomingEvent;
import io.bobba.poc.communication.protocol.ClientMessage;
import io.bobba.poc.core.gameclients.GameClient;
import io.bobba.poc.core.rooms.Room;
import io.bobba.poc.core.rooms.users.RoomUser;

public class RequestItemPickUp implements IIncomingEvent {
    @Override
    public void handle(GameClient client, ClientMessage request) {
        int itemId = request.popInt();
        
        Room room = client.getUser().getCurrentRoom();
        RoomUser user = client.getUser().getCurrentRoomUser();
        if (user != null){
        	room.getRoomUserManager().furniaddInventory(client, itemId);
            user.furniRemove(itemId);
        }
    }
}