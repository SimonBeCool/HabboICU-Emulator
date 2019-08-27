package io.bobba.poc.communication.outgoing;

import io.bobba.poc.communication.protocol.ServerMessage;
import io.bobba.poc.communication.protocol.ServerOpCodes;
import io.bobba.poc.core.items.ItemType;
import io.bobba.poc.core.rooms.items.RoomItem;

public class InventarComposer extends ServerMessage {
	private static int count = 1;
    public InventarComposer(int furniId,int item, int state, String type) {
        super(ServerOpCodes.ITEM_INVENTAR);
        appendInt(count);
        appendInt(furniId);
        appendString(type);
        appendInt(item);
        appendInt(state);
    }
}
