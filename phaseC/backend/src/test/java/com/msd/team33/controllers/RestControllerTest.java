package com.msd.team33.controllers;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

/**
 * Created by Abhishek Mulay on 11/21/17.
 */


/**
 * References: http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services
 * This class tests all REST endpoints of this application.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = RestController.class, secure = false)
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test if compare API  call triggers plagiarism detection
     *
     * @throws Exception
     */
    @Test
    public void compare() throws Exception {
        File testFile = new File("src/test/java/com/msd/team33/models/response/testDataFiles/submission.zip");
        MockMultipartFile file = new MockMultipartFile("file", "submission.zip", "application/zip", Files.readAllBytes(testFile.toPath()));
        RequestBuilder requestBuilder1 = MockMvcRequestBuilders.fileUpload("/upload").file(file);
        mockMvc.perform(requestBuilder1).andReturn();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/compare")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test if valid login credentials return successful response
     *
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        String loginCredentialsJSON = "{\"username\":\"Tester\",\"password\":\"Test123\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON).content(loginCredentialsJSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test if invalid login credentials return failure response
     *
     * @throws Exception
     */
    @Test
    public void loginInvalid() throws Exception {
        String loginCredentialsJSON = "{\"username\":\"Invalid\",\"password\":\"something\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON).content(loginCredentialsJSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }


    /**
     * Test if a zip file is uploaded
     *
     * @throws Exception
     */
    @Test
    public void upload() throws Exception {
        File testFile = new File("src/test/java/com/msd/team33/models/response/testDataFiles/submission.zip");
        MockMultipartFile file = new MockMultipartFile("file", "submission.zip", "application/zip", Files.readAllBytes(testFile.toPath()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload("/upload").file(file);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        String UPLOAD_FOLDER = "src/main/resources/uploaded/";
        FileUtils.cleanDirectory(new File(UPLOAD_FOLDER));
    }


    /**
     * Test if a API accepts only zip file
     *
     * @throws Exception
     */
    @Test
    public void uploadNonZip() throws Exception {
        File testFile = new File("src/test/java/com/msd/team33/controllers/RestControllerTest.java");
        MockMultipartFile file = new MockMultipartFile("file", "AddNumbers2.java", "text/plain", Files.readAllBytes(testFile.toPath()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload("/upload").file(file);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}