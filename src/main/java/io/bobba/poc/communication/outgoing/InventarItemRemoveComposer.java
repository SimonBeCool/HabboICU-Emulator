package io.bobba.poc.communication.outgoing;

import io.bobba.poc.communication.protocol.ServerMessage;
import io.bobba.poc.communication.protocol.ServerOpCodes;

public class InventarItemRemoveComposer extends ServerMessage {
    public InventarItemRemoveComposer(int furniId) {
        super(ServerOpCodes.ITEM_INVENTAR_REMOVE);
        appendInt(furniId);
    }
}
