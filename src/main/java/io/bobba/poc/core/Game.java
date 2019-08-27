package io.bobba.poc.core;

import io.bobba.poc.BobbaEnvironment;
import io.bobba.poc.core.gameclients.GameClientManager;
import io.bobba.poc.core.items.BaseItem;
import io.bobba.poc.core.items.BaseItemManager;
import io.bobba.poc.core.rooms.Room;
import io.bobba.poc.core.rooms.gamemap.RoomModel;
import io.bobba.poc.core.users.Authenticator;
import io.bobba.poc.misc.logging.Logging;
import io.bobba.poc.net.ConnectionManager;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import com.google.gson.Gson; 
import com.google.gson.JsonArray; 
import com.google.gson.JsonObject; 

public class Game {
    private ConnectionManager connectionManager;
    private GameClientManager gameClientManager;
    private Authenticator authenticator;
    private static BaseItemManager itemManager;
    private static Room room;

    private final int DELTA_TIME = 500;
    public static int baseItemId = 0;
    public static int itemId = 0;
  
    @SuppressWarnings("unused")
	private void addBaseItems() {
       
        // Floor Item 
    	
        BaseItem club_sofa = itemManager.addRoomItem(baseItemId++, 267, 2, 1, 1.0, "club_sofa", 1, false, false, true, Arrays.asList(0, 2, 4, 6));
        BaseItem lt_patch = itemManager.addRoomItem(baseItemId++, 3188, 2, 2, 0.01, "lt_patch", 3, false, true, false, Arrays.asList(0, 2, 4, 6));
        BaseItem lt_stone2 = itemManager.addRoomItem(baseItemId++, 3177, 2, 1, 1.05, "lt_stone2", 3, true, false, false, Arrays.asList(0, 2));
        BaseItem lt_gate = itemManager.addRoomItem(baseItemId++, 3172, 2, 1, 0.01, "lt_gate", 2, false, true, false, Arrays.asList(0, 2, 4, 6));
        BaseItem scifidoor_4 = itemManager.addRoomItem(baseItemId++, 1575, 1, 1, 0, "scifidoor*4", 2, false, true, false, Arrays.asList(2, 4));
        BaseItem hween12_scarecrow = itemManager.addRoomItem(baseItemId++, 4733, 1, 1, 0, "hween12_scarecrow", 2, false, false, false, Arrays.asList(0, 2, 4, 6));
        BaseItem rare_icecream_2 = itemManager.addRoomItem(baseItemId++, 1632, 1, 1, 0, "rare_icecream*2", 2, false, false, false, Arrays.asList(2, 4));
        BaseItem rare_dragon_5 = itemManager.addRoomItem(baseItemId++, 1621, 1, 1, 0, "rare_dragonlamp*5", 2, false, false, false, Arrays.asList(2, 4));
        BaseItem hween12_cart = itemManager.addRoomItem(baseItemId++, 4729, 1, 1, 1.0, "hween12_cart", 2, false, false, true, Arrays.asList(0, 2, 4, 6));
        BaseItem small_chair_armas = itemManager.addRoomItem(baseItemId++, 55, 1, 1, 1.0, "small_chair_armas", 1, false, false, true, Arrays.asList(0, 2, 4, 6));
        BaseItem hween12_track = itemManager.addRoomItem(baseItemId++, 4731, 1, 1, 0.25, "hween12_track", 3, true, true, false, Arrays.asList(0, 2, 4, 6));
        BaseItem hween12_track_crl = itemManager.addRoomItem(baseItemId++, 4736, 1, 1, 0.25, "hween12_track_crl", 3, true, true, false, Arrays.asList(0, 2, 4, 6));
        BaseItem hween12_track_crr = itemManager.addRoomItem(baseItemId++, 4739, 1, 1, 0.25, "hween12_track_crr", 3, true, true, false, Arrays.asList(0, 2, 4, 6));
        BaseItem LT_skull = itemManager.addRoomItem(baseItemId++, 3189, 1, 1, 0.4, "LT_skull", 1, false, false, false, Arrays.asList(0, 2, 4, 6));
        BaseItem stories_shakespeare_tree = itemManager.addRoomItem(baseItemId++, 5735, 2, 2, 0, "stories_shakespeare_tree", 2, false, false, false, Arrays.asList(0, 2));
        BaseItem anc_artifact3 = itemManager.addRoomItem(baseItemId++, 4655, 3, 1, 0, "anc_artifact3", 1, false, false, false, Arrays.asList(2, 4));
        BaseItem anc_waterfall = itemManager.addRoomItem(baseItemId++, 4651, 1, 1, 0, "anc_waterfall", 1, false, true, false, Arrays.asList(2, 4));
        BaseItem anc_talltree = itemManager.addRoomItem(baseItemId++, 4650, 2, 2, 0, "anc_talltree", 1, false, false, false, Arrays.asList(2, 4));
        BaseItem anc_comfy_tree = itemManager.addRoomItem(baseItemId++, 4653, 1, 1, 0, "anc_comfy_tree", 1, false, false, false, Arrays.asList(0, 2, 4, 6));
        BaseItem doorD = itemManager.addRoomItem(baseItemId++, 1505, 1, 1, 0, "doorD", 3, false, true, false, Arrays.asList(2, 4));
        BaseItem duck = itemManager.addRoomItem(baseItemId++, 179, 1, 1, 1.0, "duck", 1, false, false, false, Arrays.asList(0, 2, 4, 6));
        BaseItem stand_polyfon_z = itemManager.addRoomItem(baseItemId++, 25, 1, 1, 1.0, "stand_polyfon_z", 1, true, false, false, Arrays.asList(0, 2, 4, 6));
        BaseItem scifirocket3 = itemManager.addRoomItem(baseItemId++, 1565, 1, 1, 0, "scifirocket*3", 2, false, false, false, Arrays.asList(0));
        BaseItem footylamp = itemManager.addRoomItem(baseItemId++, 1757, 1, 1, 0, "footylamp", 2, false, true, true, Arrays.asList(2, 4)); 
        BaseItem country_grass = itemManager.addRoomItem(baseItemId++, 3162, 1, 1, 0, "country_grass", 2, false, false , false, Arrays.asList(2, 4)); 
    	
        // Wall Item
        BaseItem lt_jngl_wall = itemManager.addWallItem(baseItemId++, 4121, "lt_jngl_wall", 3);
        BaseItem anc_sunset_wall = itemManager.addWallItem(baseItemId++, 4462, "anc_sunset_wall", 2);
        BaseItem wall_china = itemManager.addRoomItem(baseItemId++, 2001, 1, 1, 0, "wall_china", 2, false, false , false, Arrays.asList(2, 4)); 
        
        
        // Basic Room
        double z = 0.0;

        int currentX = 0;
        for (int i = 0; i < 7; i++) {
            int currentY = 0;
            for (int j = 0; j < 6; j++) {
                room.getRoomItemManager().addFloorItemToRoom(itemId++, 1 + currentX, currentY, z, lt_patch.getDirections().get(0), 1, lt_patch);
                currentY += 2;
            }
            currentX += 2;
        }
        for (int i = 0; i < 7; i++) {
           room.getRoomItemManager().addFloorItemToRoom(itemId++, 1 + (i * 2), 11, z, lt_patch.getDirections().get(0), 1, lt_patch);
        }
        
        room.getRoomItemManager().addFloorItemToRoom(itemId++, 2, 1, z, stories_shakespeare_tree.getDirections().get(0), 0, stories_shakespeare_tree); 
        room.getRoomItemManager().addFloorItemToRoom(itemId++, 2, 10, z, lt_stone2.getDirections().get(1), 0, lt_stone2);

        room.getRoomItemManager().addFloorItemToRoom(itemId++, 2, 8, z, lt_stone2.getDirections().get(1), 0, lt_stone2);
        room.getRoomItemManager().addFloorItemToRoom(itemId++, 2, 6, z, lt_stone2.getDirections().get(1), 0, lt_stone2);
        room.getRoomItemManager().addFloorItemToRoom(itemId++, 2, 4, z, lt_stone2.getDirections().get(1), 0, lt_stone2);


        room.getRoomItemManager().addFloorItemToRoom(itemId++, 3, 4, z, hween12_scarecrow.getDirections().get(1), 1, hween12_scarecrow);

        room.getRoomItemManager().addFloorItemToRoom(itemId++, 1, 3, z, lt_stone2.getDirections().get(0), 0, lt_stone2);
        room.getRoomItemManager().addFloorItemToRoom(itemId++, 3, 3, z, lt_stone2.getDirections().get(0), 0, lt_stone2);
        room.getRoomItemManager().addFloorItemToRoom(itemId++, 5, 3, z, lt_stone2.getDirections().get(0), 0, lt_stone2);
    }
    
 
    private void loadSslServer(int port) throws Exception {
        connectionManager = new ConnectionManager(port, this.gameClientManager);

        if (BobbaEnvironment.getConfigManager().getSslEnabled().toLowerCase().equals("true")) {

            // load up the key store
            String storeType = BobbaEnvironment.getConfigManager().getSslStoreType();
            String keyStore = BobbaEnvironment.getConfigManager().getSslStoreFile();
            String storePassword = BobbaEnvironment.getConfigManager().getSslStorePassword();
            String keyPassword = BobbaEnvironment.getConfigManager().getSslKeyPassword();

            KeyStore ks = KeyStore.getInstance(storeType);
            File kf = new File(keyStore);
            ks.load(new FileInputStream(kf), storePassword.toCharArray());

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, keyPassword.toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            SSLContext sslContext = null;
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            connectionManager.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(sslContext));
        }

        connectionManager.start();
    }
    
    public Game(int port) throws Exception {
        this.gameClientManager = new GameClientManager();
        this.authenticator = new Authenticator();
        this.itemManager = new BaseItemManager();
        this.room = new Room(1, "Test room", new RoomModel());
        loadSslServer(port);

        addBaseItems();
        
        Thread roomThread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameThread();
            }
        });
        roomThread.start();
    }

    private void gameThread() {
        Instant starts, ends;
        while (true) {
            try {
                starts = Instant.now();
                onCycle();
                ends = Instant.now();

                long sleepTime = DELTA_TIME - Duration.between(starts, ends).toMillis();
                if (sleepTime < 0) {
                    sleepTime = 0;
                }

                Thread.sleep(sleepTime);

            } catch (Exception e) {
                Logging.getInstance().logError("Game thread error", e, this.getClass());
            }
        }
    }

    public void onCycle() {
        room.onCycle();
    }

    public Room getRoom() {
        return room;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public GameClientManager getGameClientManager() {
        return gameClientManager;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public BaseItemManager getItemManager() {
        return itemManager;
    }


}
