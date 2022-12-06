package com.example.semester1.core.Utilities;

import com.example.semester1.core.Classes.*;
import com.example.semester1.core.Room;

import java.io.IOException;
import java.util.*;

public class ConfigLoader {
    private static final String NONE_KEYWORD = "none";
    private Properties gameProperties;

    private HashMap<String, Properties> allRoomProperties;
    private HashMap<String, Properties> allItemProperties;


    private HashMap<String, Room> roomsHashMap = new HashMap<>();
    private HashMap<String, Appliance> appliancesHashMap = new HashMap<>();
    private Inventory itemsStore = new Inventory();
    private ActivityManager activityManager = new ActivityManager();

    private PropertiesLoader propertiesLoader;


    /*
     *      ** Constructors **
     */
    public ConfigLoader(String configDirPath) throws IOException {
        this.propertiesLoader = new PropertiesLoader(configDirPath);
        this.gameProperties = propertiesLoader.loadGameProperties();

        this.initAllRooms();
        this.loadActivities();
        this.loadAppliances();
        this.loadItems();

        //Needs to configure rooms after items and appliances have been loaded.
        this.configureAllRooms();
    }

    public ConfigLoader() throws IOException {
        this(PropertiesLoader.getDefaultConfigDirPath());
    }


    private void loadAppliances() throws IOException {
        HashMap<String, Properties> allProperties = this.propertiesLoader.loadAppliancesProperties();
        for (Map.Entry<String, Properties> n : allProperties.entrySet()) {
            String id = n.getKey();
            Properties properties = n.getValue();
            String displayName = properties.getProperty("displayName");
            String activityId = properties.getProperty("activityId");


            Appliance object = new Appliance(id, displayName, activityId);

            object.setX(Integer.parseInt(properties.getProperty("x")));
            object.setY(Integer.parseInt(properties.getProperty("y")));

            this.appliancesHashMap.put(id, object);
        }
    }

    private void loadItems() throws IOException {
        HashMap<String, Properties> allProperties = this.propertiesLoader.loadItemProperties();

        this.allItemProperties = allProperties;

        for (Map.Entry<String, Properties> n : allProperties.entrySet()) {
            this.itemsStore.add(this.createItem(n.getKey()));
        }
    }

    private Item createItem(String id) {
        Properties properties = this.allItemProperties.get(id);
        Item object = new Item(id, properties.getProperty("displayName"));

        object.setX(Integer.parseInt(properties.getProperty("x")));
        object.setY(Integer.parseInt(properties.getProperty("y")));

        return object;
    }

    private void loadActivities() throws IOException {
        HashMap<String, Properties> allProperties = this.propertiesLoader.loadActivitiesProperties();
        for (Map.Entry<String, Properties> n : allProperties.entrySet()) {
            String id = n.getKey();
            Properties properties = n.getValue();

            String displayName = properties.getProperty("displayName");
            int successPoints = Integer.parseInt(properties.getProperty("successPoints"));
            int failurePoints = Integer.parseInt(properties.getProperty("failurePoints"));
            int powerCost = Integer.parseInt(properties.getProperty("powerCost"));
            boolean daily = Boolean.parseBoolean(properties.getProperty("daily"));
            String itemId = properties.getProperty("itemId");

            Activity object = new Activity(id, displayName, daily, itemId, successPoints, failurePoints, powerCost);
            this.activityManager.add(object);
        }
    }


    private void initAllRooms() throws IOException {
        HashMap<String, Properties> allProperties = this.propertiesLoader.loadRoomProperties();
        this.allRoomProperties = allProperties;

        for (Map.Entry<String, Properties> n : allProperties.entrySet()) {
            String id = n.getKey();
            Properties properties = n.getValue();


            String displayName = properties.getProperty("displayName");
            String description = properties.getProperty("description");

            Room roomObject = new Room(id, displayName, description);
            this.roomsHashMap.put(id, roomObject);
        }
    }

    private void configureAllRooms() {
        for (Map.Entry<String, Room> entry : this.roomsHashMap.entrySet()) {
            Room roomObject = entry.getValue();

            Properties roomProperties = this.allRoomProperties.get(entry.getKey());
            this.setExitsInRoom(roomObject, roomProperties);


            this.setItems(roomObject, roomProperties);


            this.setAppliances(roomObject, roomProperties);
        }
    }

    private void setAppliances(Room roomObject, Properties roomProperties) {
        String[] applianceIds = roomProperties.getProperty("applianceId").split(",");

        for (String applianceId : applianceIds) {
            if (!applianceId.equals(ConfigLoader.NONE_KEYWORD)) {
                Appliance appliance = this.appliancesHashMap.get(applianceId);
                roomObject.addAppliance(appliance);
            }
        }
    }

    private void setItems(Room roomObject, Properties roomProperties) {
        String[] itemIds = roomProperties.getProperty("itemId").split(",");

        for (String itemId : itemIds) {
            if (!itemId.equals(ConfigLoader.NONE_KEYWORD)) {
                Item item = this.itemsStore.getByAlias(itemId);
                roomObject.addItem(item);
            }
        }
    }

    private void setExitsInRoom(Room room, Properties properties) {
        for (Direction direction : Direction.values()) {
            String nextRoomString = properties.getProperty(direction.toString());
            if (!nextRoomString.equals(ConfigLoader.NONE_KEYWORD)) {
                room.setExit(direction, this.roomsHashMap.get(nextRoomString));
            }
        }
    }


    public Room getDefaultRoom() {
        String defaultRoomId = this.gameProperties.getProperty("defaultRoom");
        return this.roomsHashMap.get(defaultRoomId);
    }

    public ActivityManager getActivityManager() {
        return this.activityManager;
    }

    public Inventory getItemsStore() {
        return this.itemsStore;
    }





    private ArrayList<String> getDailyNeededItemIds() {
        ArrayList<String> ids = new ArrayList<>();

        for (Activity activity : this.activityManager.getAllDailyActivities()) {
            String itemId = activity.getItemId();
            ids.add(itemId);
        }

        return ids;
    }


//    private void repopulateRoom(Room room, Properties roomProperties, ArrayList<String> dailyNeededItems) {
//
//    }

    public void repopulateDailyNeededItems() {
        ArrayList<String> dailyNeededItems = this.getDailyNeededItemIds();

        for (Map.Entry<String, Properties> entry : this.allRoomProperties.entrySet()) {
            this.repopulateRoomWithItems(entry, dailyNeededItems);
        }
    }

    private void repopulateRoomWithItems(Map.Entry<String, Properties> entry, ArrayList<String> dailyNeededItems) {
        String roomId = entry.getKey();
        Properties roomProperties = entry.getValue();
        Room room = this.roomsHashMap.get(roomId);

        String[] itemIds = roomProperties.getProperty("itemId").split(",");


        for (String itemId : itemIds) {
            if (itemId.equals(ConfigLoader.NONE_KEYWORD)) continue;

            if (!room.hasItem(itemId) && dailyNeededItems.contains(itemId)) {
                Item item = this.createItem(itemId);
                room.addItem(item);
            }
        }
    }
}