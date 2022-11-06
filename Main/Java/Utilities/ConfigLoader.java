package worldOfZuul.Main.Java.Utilities;

import worldOfZuul.Main.Java.Classes.*;
import worldOfZuul.Main.Java.Room;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigLoader {
    private static final String NONE_KEYWORD = "none";
    private static final String EXTENSION = ".properties";
    private static final String GAME_CONFIG_PATH = "game" + ConfigLoader.EXTENSION;

    //Sub folders
    private static final String ITEMS_DIR_PATH = "items";
    private static final String ACTIVITIES_DIR_PATH = "activities";
    private static final String APPLIANCES_DIR_PATH = "appliances";
    private static final String ROOMS_DIR_PATH = "rooms";


    private final String configDirPath;
    private Properties gameProperties = new Properties();


    private HashMap<String, Room> roomsHashMap = new HashMap<String, Room>();
    private HashMap<String, Appliance> appliancesHashMap = new HashMap<String, Appliance>();
    private Inventory itemsStore = new Inventory();
    private ActivityManager activityManager = new ActivityManager();


    public static String getDefaultConfigDirPath() {
        StringBuilder path = new StringBuilder();
        path.append(System.getProperty("user.dir"));

        path.append(File.separatorChar);
        path.append("gameConfigs");
        return path.toString();
    }

    public static String stripExtension(String path) {
        if (path.endsWith(ConfigLoader.EXTENSION)) {
            return path.substring(0, path.length() - ConfigLoader.EXTENSION.length());
        } else {
            return path;
        }
    }

    /*
     *      ** Constructors **
     */

    public ConfigLoader(String configDirPath) throws IOException {
        this.configDirPath = configDirPath;
        this.gameProperties = this.readProperties(ConfigLoader.GAME_CONFIG_PATH);

        this.initAllRooms();
        this.loadActivities();
        this.loadAppliances();
        this.loadItems();

        //Needs to configure rooms after items and appliances have been loaded.
        this.configureAllRooms();
    }

    public ConfigLoader() throws IOException {
        this(ConfigLoader.getDefaultConfigDirPath());
    }


    /*public static void main(String[] args) throws IOException {
        ConfigLoader loader = new ConfigLoader(new ActivityManager());


    }*/


    private String getFullPathFromPathArray(boolean appendExtension, String... pathArray) {
        //Using variable arguments (...varargs)
        //https://www.geeksforgeeks.org/variable-arguments-varargs-in-java/

        StringBuilder filePath = new StringBuilder(this.configDirPath);
        /*
        Constructing file path from pathArray, using the proper file separators for the system.
        This enables better compatibility between DOS and UNIX systems.
         */
        for (String section : pathArray) {
            filePath.append(File.separatorChar);
            filePath.append(section);
        }

        String filePathString = filePath.toString();

        if (!filePathString.endsWith(ConfigLoader.EXTENSION) && appendExtension) {
            filePathString += ConfigLoader.EXTENSION;
        }

        return filePathString;
    }

    private String getFullPathFromPathArray(String... pathArray) {
        return this.getFullPathFromPathArray(true, pathArray);
    }

    private Properties readProperties(String... pathArray) throws IOException {
        //Using variable arguments (...varargs)
        //https://www.geeksforgeeks.org/variable-arguments-varargs-in-java/

        Properties properties = new Properties(this.loadDefaultProperties(pathArray.clone()));

        File file = new File(this.getFullPathFromPathArray(pathArray));
        //Using the try-with-resource construct, so that it auto closes the FileReader on IOException.
        try (FileReader fileReader = new FileReader(file)) {
            properties.load(fileReader);
        }

        return properties;
    }


    private Properties loadDefaultProperties(String... pathArray) throws IOException {
        pathArray[pathArray.length - 1] = "default";
        Properties properties = new Properties();

        File file = new File(this.getFullPathFromPathArray(pathArray));


        try (FileReader fileReader = new FileReader(file)) {
            properties.load(fileReader);
        } catch (FileNotFoundException exception) {
            //ignore.Pass empty properties instead.
            //System.out.println("No default properties found, continuing with empty properties");
        }
        return properties;
    }

    private String[] getContentsOfDir(String... pathArray) {
        String path = this.getFullPathFromPathArray(false, pathArray);
        FilenameFilter ignoreFilter = new IgnoreFilenamesFilter(true, "default.properties");
        return new File(path).list(ignoreFilter);
    }



    private void loadAppliances() throws IOException {
        String[] fileNames = this.getContentsOfDir(ConfigLoader.APPLIANCES_DIR_PATH);
        for (String n : fileNames) {
            String id = ConfigLoader.stripExtension(n);
            Properties properties = this.readProperties(ConfigLoader.APPLIANCES_DIR_PATH, n);
            String displayName = properties.getProperty("displayName");
            String activityId = properties.getProperty("activityId");
            String itemId = properties.getProperty("itemId");

            Appliance object = new Appliance(id, displayName, activityId, itemId);
            this.appliancesHashMap.put(id, object);
        }
    }

    private void loadItems() throws IOException {
        String[] fileNames = this.getContentsOfDir(ConfigLoader.ITEMS_DIR_PATH);
        for (String n : fileNames) {
            String id = ConfigLoader.stripExtension(n);
            Properties properties = this.readProperties(ConfigLoader.ITEMS_DIR_PATH, n);
            String displayName = properties.getProperty("displayName");

            Item object = new Item(id, displayName);
            this.itemsStore.add(object);
        }
    }

    private void loadActivities() throws IOException {
        String[] fileNames = this.getContentsOfDir(ConfigLoader.ACTIVITIES_DIR_PATH);
        for (String n : fileNames) {
            String id = ConfigLoader.stripExtension(n);
            Properties properties = this.readProperties(ConfigLoader.ACTIVITIES_DIR_PATH, n);
            String displayName = properties.getProperty("displayName");
            int successPoints = Integer.parseInt(properties.getProperty("successPoints"));
            int failurePoints = Integer.parseInt(properties.getProperty("failurePoints"));
            int powerCost = Integer.parseInt(properties.getProperty("powerCost"));
            boolean daily = Boolean.parseBoolean(properties.getProperty("daily"));

            Activity object = new Activity(id, displayName, successPoints, failurePoints, powerCost, daily);
            this.activityManager.add(object);
        }
    }


    private void initAllRooms() throws IOException {
        String[] roomFiles = this.getContentsOfDir(ConfigLoader.ROOMS_DIR_PATH);
        for (String roomFile : roomFiles) {
            Properties roomProperties = this.readProperties(ConfigLoader.ROOMS_DIR_PATH, roomFile);


            String roomId = ConfigLoader.stripExtension(roomFile);

            //System.out.println(roomProperties);


            String displayName = roomProperties.getProperty("displayName");
            String description = roomProperties.getProperty("description");

            Room roomObject = new Room(roomId, displayName, description);
            this.roomsHashMap.put(roomId, roomObject);
        }
    }

    private void configureAllRooms() throws IOException {
        for (Map.Entry<String, Room> entry : this.roomsHashMap.entrySet()) {
            Room roomObject = entry.getValue();
            Properties roomProperties = this.readProperties(ConfigLoader.ROOMS_DIR_PATH, entry.getKey());
            this.setExitsInRoom(roomObject, roomProperties);

            String itemId = roomProperties.getProperty("itemId");

            if (!itemId.equals(ConfigLoader.NONE_KEYWORD)) {
                Item item = this.itemsStore.getByAlias(itemId);
                roomObject.addItem(item);
            }

            String applianceId = roomProperties.getProperty("applianceId");

            if (!applianceId.equals(ConfigLoader.NONE_KEYWORD)) {
                Appliance appliance = this.appliancesHashMap.get(applianceId);
                roomObject.addAppliance(appliance);
            }

            //System.out.println(roomObject.getLongDescription());
        }
        //System.out.println(this.roomsHashMap);
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

}