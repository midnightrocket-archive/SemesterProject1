package worldOfZuul.Main.Java.Utilities;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public class IgnoreFilenamesFilter implements FilenameFilter {
    private final boolean ignoreDir;
    private List<String> ignoreList;

    public IgnoreFilenamesFilter(boolean ignoreDir, String ...ignoreList) {
        this(ignoreDir, Arrays.asList(ignoreList));
    }

    public IgnoreFilenamesFilter(boolean ignoreDir, List<String> ignoreList) {
        this.ignoreDir = ignoreDir;
        this.ignoreList = ignoreList;
    }

    @Override
    public boolean accept(File dir, String name) {
        if (this.ignoreList.contains(name)) return false;
        if (new File(dir, name).isDirectory() && this.ignoreDir) return false;
        return true;
    }
}