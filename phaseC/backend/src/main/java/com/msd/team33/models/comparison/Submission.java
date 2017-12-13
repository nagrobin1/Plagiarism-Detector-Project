package com.msd.team33.models.comparison;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A Submission is the path to the student's folder.
 */
public class Submission {

    /**
     * The path to the student submission folder.
     */
    private Path path;

    /**
     * A constructor to create a Submission from the given path string.
     *
     * @param pathName the string representation of the path directory of the submission
     */
    public Submission(String pathName) {
        this.path = Paths.get(pathName);
    }

    /**
     * A method to get the path of this Submission as a Path object.
     *
     * @return the Path for this submission
     */
    public Path getPath() {
        return path;
    }

    /**
     * A method to get a File object for this Submission from this Submission's Path.
     *
     * @return a File for this Submission's Path
     */
    public File getFile() {
        return path.toFile();
    }

    /**
     * A method to get the path name for this Submission as a String.
     *
     * @return a String representation of this Submisson's path name
     */
    public String getPathNameAsString() {
        return path.toString();
    }
}
