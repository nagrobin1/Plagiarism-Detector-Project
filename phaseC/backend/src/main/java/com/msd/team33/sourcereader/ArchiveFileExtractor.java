package com.msd.team33.sourcereader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * A class to extract the contents of a zip file in a folder,
 * recursively extracting all files and sub-folders contained therein.
 */
public class ArchiveFileExtractor {

    private static final int BUFFER_SIZE = 2048;

    /***
     *
     *  Extracts contents of a zip file in a folder.
     *  Recursively extracts all files and sub folders
     *
     * @param zipFilePath
     * @param destinationDir
     */
    public void unzip(final String zipFilePath, final String destinationDir) {
        File destination = new File(destinationDir);
        ZipInputStream zipInputStream = null;
        ZipEntry zipEntry = null;

        if (!destination.exists())
            destination.mkdir();

        try {
            zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
            zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String nestedFilePath = destinationDir + zipEntry.getName();
                if (!nestedFilePath.contains("__MACOSX") && !nestedFilePath.contains(".DS_Store")) {
                    if (!zipEntry.isDirectory()) {
                        extractZipFile(zipInputStream, nestedFilePath);
                    } else {
                        File dir = new File(nestedFilePath);
                        dir.mkdir();
                    }
                }
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursive extract function
     *
     * @param zipInputStream
     * @param nestedFilePath
     */
    private void extractZipFile(final ZipInputStream zipInputStream, final String nestedFilePath) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(nestedFilePath));
            byte[] bytes = new byte[BUFFER_SIZE];
            int line = 0;
            while ((line = zipInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, line);
            }
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
