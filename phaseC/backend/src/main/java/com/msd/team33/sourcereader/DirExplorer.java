package com.msd.team33.sourcereader;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Opens a submission directory to fetch all the files in it.
 */
public class DirExplorer {
    private FileHandler fileHandler;
    private Filter filter;

    /**
     * Constructor
     *
     * @param filter
     * @param fileHandler
     */
    public DirExplorer(Filter filter, FileHandler fileHandler) {
        this.filter = filter;
        this.fileHandler = fileHandler;
    }

    /**
     * Explores the given directory.
     *
     * @param root
     */
    public void explore(File root) {
        explore(0, "", root);
    }

    /**
     * Visits inside every directory in a submission.
     *
     * @param level
     * @param path
     * @param file
     */
    private void explore(int level, String path, File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                explore(level + 1, path + "/" + child.getName(), child);
            }
        } else {
            if (filter.interested(level, path, file)) {
                fileHandler.handle(level, path, file);
            }
        }
    }

    /**
     * Recursively enters inside directories.
     */
    public interface FileHandler {
        void handle(int level, String path, File file);
    }

    /**
     * Recursively filters for directories.
     */
    public interface Filter {
        boolean interested(int level, String path, File file);
    }

}