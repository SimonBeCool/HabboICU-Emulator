package io.bobba.poc.communication.incoming.generic;

import io.bobba.poc.BobbaEnvironment;
import io.bobba.poc.communication.incoming.IIncomingEvent;
import io.bobba.poc.communication.outgoing.InventarComposer;
import io.bobba.poc.communication.protocol.ClientMessage;
import io.bobba.poc.core.gameclients.GameClient;
import io.bobba.poc.core.rooms.Room;

public class Login implements IIncomingEvent {

    @Override
    public void handle(GameClient client, ClientMessage request) {
        String username = request.popString();
        String look = request.popString();
        String motto = "Write your own motto..";
        BobbaEnvironment.getInstance().getGame().getAuthenticator().tryLogin(client, username, look, motto);
        
        Room room = client.getUser().getCurrentRoom();
    }
}
