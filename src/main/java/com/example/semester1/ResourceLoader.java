package com.example.semester1;

import java.io.InputStream;

/*
 * Provides a tool set for loading assets from class resources
 * https://docs.oracle.com/javase/8/docs/technotes/guides/lang/resources.html
 */
public class ResourceLoader {
    public static final String BASE_PATH = "/com/example/semester1/";

    public static InputStream loadAsInputStream(String path, String fallBack) {
        if (!path.startsWith("/")) {
            path = ResourceLoader.BASE_PATH + path;
        }
        //System.out.println(path);
        if (ResourceLoader.class.getResource(path) == null) {
            return ResourceLoader.class.getResourceAsStream(fallBack);
        } else {
            return ResourceLoader.class.getResourceAsStream(path);
        }
    }

    public static InputStream loadAsInputStream(String path) {
        return ResourceLoader.loadAsInputStream(path, null);
    }
    public static InputStream loadGameAssetAsInputStream(String subDir, String id) {
        String path = ResourceLoader.BASE_PATH + "assets/game/" + subDir;
        return ResourceLoader.loadAsInputStream(String.format("%s/%s.png", path, id), path + "/default.png");
    }

    public static InputStream loadUIAssetAsInputStream(String id) {
        String path = ResourceLoader.BASE_PATH + "assets/UI";
        return ResourceLoader.loadAsInputStream(String.format("%s/%s.png", path, id), path + "/placeholderImage.png");
    }
}