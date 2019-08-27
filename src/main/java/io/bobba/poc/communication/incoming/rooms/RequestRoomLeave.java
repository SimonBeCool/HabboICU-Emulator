package io.bobba.poc.communication.incoming.rooms;

import io.bobba.poc.BobbaEnvironment;
import io.bobba.poc.communication.incoming.IIncomingEvent;
import io.bobba.poc.communication.protocol.ClientMessage;
import io.bobba.poc.core.gameclients.GameClient;
import io.bobba.poc.core.rooms.Room;

public class RequestRoomLeave implements IIncomingEvent {

    @Override
    public void handle(GameClient client, ClientMessage request) {
        Room room = client.getUser().getCurrentRoom();
        room.getRoomUserManager().removeUserFromCurrentRoom(client);
    }
}
