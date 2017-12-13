package com.msd.team33.sourcereader;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the ArchiveFileExtractor class.
 */
public class ArchiveFileExtractorTest {

    private ArchiveFileExtractor extractor = new ArchiveFileExtractor();

    /**
     * Test if zip file extractor extracts file in destination folder.
     *
     * @throws Exception
     */
    @Test
    public void unzip() throws Exception {
        File testFile = new File("src/test/java/com/msd/team33/models/response/testDataFiles/submission.zip");
        String UPLOAD_FOLDER = "src/main/resources/uploaded/";
        extractor.unzip(testFile.toPath().toString(), UPLOAD_FOLDER);
        assertTrue(new File(UPLOAD_FOLDER).listFiles().length > 0);

        FileUtils.cleanDirectory(new File(UPLOAD_FOLDER));
    }
}