package com.msd.team33.controllers;

import com.msd.team33.models.SubmissionComparator;
import com.msd.team33.models.SubmissionComparatorInterface;
import com.msd.team33.models.response.ComparisonDetails;
import com.msd.team33.sourcereader.ArchiveFileExtractor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class to handle incoming Restful HTTP request
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private static final String UPLOAD_FOLDER = "src/main/resources/uploaded/";
    private static Map<String, String> allowedInstructors = new HashMap<>();
    private static Set<String> ARCHIVE_FILE_TYPES = new HashSet<>();

    static {
        allowedInstructors.put("Abhishek", "Abhi123");
        allowedInstructors.put("Robin", "Robin123");
        allowedInstructors.put("Piyush", "Piyush123");
        allowedInstructors.put("Daniel", "Daniel123");
        allowedInstructors.put("Tester", "Test123");
        allowedInstructors.put("user", "password");

        ARCHIVE_FILE_TYPES.add("zip");
        ARCHIVE_FILE_TYPES.add("rar");
        ARCHIVE_FILE_TYPES.add("tar");
    }

    private SubmissionComparatorInterface model;

    /**
     * Initiates comparison of uploaded submission
     *
     * @return Final comparison details of all submissions
     */

    @CrossOrigin(origins = "*")
    @RequestMapping("/compare")
    public ResponseEntity<?> compare() {
        File dir = new File(UPLOAD_FOLDER);
        File[] files = dir.listFiles();

        if (!dir.exists() || files == null || files.length == 0) {
            return new ResponseEntity<>(" Please upload files for comparison", HttpStatus.BAD_REQUEST);
        }

        // there should be only one dir in UPLOAD folder
        for (File child : dir.listFiles()) {
            if (child.isDirectory())
                files = child.listFiles();
        }

        SubmissionComparator comparator = new SubmissionComparator();
        List<ComparisonDetails> details = null;
        try {
            List<String> filePaths = new ArrayList<>();
            for (File file : files) {
                if (shouldIgnoreFile(file))
                    continue;
                filePaths.add(file.getAbsolutePath());
            }

            details = comparator.compare(filePaths.toArray(new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.cleanDirectory(new File(UPLOAD_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(details, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Ignores default Mac files
     *
     * @param f
     * @return boolean to check whether file should be ignored or not
     */
    private boolean shouldIgnoreFile(File f) {
        String[] ignoreFiles = {".DS_Store", "__MACOSX"};
        for (String ignore : ignoreFiles) {
            if (f.getAbsolutePath().contains(ignore)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checking for login credentials to log user in
     *
     * @param loginCredentials
     * @return login status
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> loginCredentials) {
        String username = "";
        if (loginCredentials != null) {
            username = loginCredentials.getOrDefault("username", "");
            String password = loginCredentials.getOrDefault("password", "");

            if (allowedInstructors.containsKey(username)) {
                if (allowedInstructors.get(username).equals(password)) {
                    LoginResponse success = new LoginResponse(username, true, "Successfully logged in", HttpStatus.OK);
                    return new ResponseEntity<>(success, HttpStatus.OK);
                }
            }
        }
        // invalid credentials
        LoginResponse failure = new LoginResponse(username, false, "Invalid credentials", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(failure, HttpStatus.BAD_REQUEST);
    }

    /**
     * Uploads assignment submissions of a class
     *
     * @param uploadedFile
     * @return upload status
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile uploadedFile) {

        if (!new File(UPLOAD_FOLDER).exists())
            new File(UPLOAD_FOLDER).mkdir();

        JSONObject obj = new JSONObject();

        // handle erroneous input
        if (uploadedFile == null || uploadedFile.isEmpty()) {
            obj.put("message", "please select a file!");
            obj.put("code", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }

        String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename().toLowerCase());
        // only allow archive files
        if (!ARCHIVE_FILE_TYPES.contains(extension)) {
            obj.put("message", uploadedFile.getOriginalFilename() + " is not a valid archive file. Please upload a valid archive file.");
            obj.put("code", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the zip file and save it
            byte[] bytes = uploadedFile.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + uploadedFile.getOriginalFilename());
            Files.write(path, bytes);

            // extract zip file
            ArchiveFileExtractor extractor = new ArchiveFileExtractor();
            extractor.unzip(path.toString(), UPLOAD_FOLDER);

        } catch (IOException e) {
            e.printStackTrace();
        }

        obj.put("message", "Successfully uploaded: " + uploadedFile.getOriginalFilename());
        obj.put("code", HttpStatus.OK);

        // all went fine. send back success.
        return new ResponseEntity<>(obj, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Class for login response
     */
    private class LoginResponse {
        String username;
        boolean success;
        String message;
        HttpStatus status;

        /**
         * Constructor for loginResponse
         *
         * @param username
         * @param success
         * @param message
         * @param status
         */
        public LoginResponse(String username, boolean success, String message, HttpStatus status) {
            this.username = username;
            this.success = success;
            this.message = message;
            this.status = status;
        }

        /**
         * @return login success status
         */
        public boolean getSuccess() {
            return success;
        }

        /**
         * Sets login success status
         *
         * @param success
         */
        public void setSuccess(boolean success) {
            this.success = success;
        }

        /**
         * @return login message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets login message
         *
         * @param message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * @return HTTP status
         */
        public HttpStatus getStatus() {
            return status;
        }

        /**
         * Sets HTTP status
         *
         * @param status
         */
        public void setStatus(HttpStatus status) {
            this.status = status;
        }

        /**
         * @return logged in username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Sets logging in user
         *
         * @param username
         */
        public void setUsername(String username) {
            this.username = username;
        }
    }
}
