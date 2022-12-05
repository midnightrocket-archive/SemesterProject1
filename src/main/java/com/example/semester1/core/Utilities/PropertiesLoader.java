package com.example.semester1.core.Utilities;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;


/*
 * Package private, as it is only used by the ConfigLoader
 */
class PropertiesLoader {

    private static final String EXTENSION = ".properties";
    private static final String GAME_CONFIG_PATH = "game" + PropertiesLoader.EXTENSION;

    //Sub folders
    private static final String ITEMS_DIR_PATH = "items";
    private static final String ACTIVITIES_DIR_PATH = "activities";
    private static final String APPLIANCES_DIR_PATH = "appliances";
    private static final String ROOMS_DIR_PATH = "rooms";


    private final String configDirPath;

    public static String getDefaultConfigDirPath() {
        StringBuilder path = new StringBuilder();
        path.append(System.getProperty("user.dir"));

        path.append(File.separatorChar);
        path.append("gameConfigs");
        return path.toString();
    }

    public static String stripExtension(String path) {
        if (path.endsWith(PropertiesLoader.EXTENSION)) {
            return path.substring(0, path.length() - PropertiesLoader.EXTENSION.length());
        } else {
            return path;
        }
    }


    PropertiesLoader(String path) {
        this.configDirPath = path;
    }


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

        if (!filePathString.endsWith(PropertiesLoader.EXTENSION) && appendExtension) {
            filePathString += PropertiesLoader.EXTENSION;
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

    private HashMap<String, Properties> loadPropertiesFromDir(String subdir) throws IOException {
        HashMap<String, Properties> output = new HashMap<>();

        String[] fileNames = this.getContentsOfDir(subdir);
        for (String f : fileNames) {
            Properties roomProperties = this.readProperties(subdir, f);

            String id = PropertiesLoader.stripExtension(f);

            output.put(id, roomProperties);
        }
        return output;
    }


    public Properties loadGameProperties() throws IOException {
        return this.readProperties(PropertiesLoader.GAME_CONFIG_PATH);
    }


    public HashMap<String, Properties> loadRoomProperties() throws IOException {
        return this.loadPropertiesFromDir(PropertiesLoader.ROOMS_DIR_PATH);
    }

    public HashMap<String, Properties> loadActivitiesProperties() throws IOException {
        return this.loadPropertiesFromDir(PropertiesLoader.ACTIVITIES_DIR_PATH);
    }

    public HashMap<String, Properties> loadAppliancesProperties() throws IOException {
        return this.loadPropertiesFromDir(PropertiesLoader.APPLIANCES_DIR_PATH);
    }

    public HashMap<String, Properties> loadItemProperties() throws IOException {
        return this.loadPropertiesFromDir(PropertiesLoader.ITEMS_DIR_PATH);
    }
}