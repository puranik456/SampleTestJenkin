package com.qa.testrailmanger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRailManager {
    
    public static String Test_Run_ID= "20";
    
    public static String TEST_RAIL_USERNAME = "puranikprashant1@gmail.com";
    public static String TEST_RAIL_PASSWORD = "Mynameiskhan@123";
    
    public static String TEST_RAIL_ENGINE_URL = "https://prashant1.testrail.io/";
    
    public static int TEST_CASE_PASS_STATUS = 1;
    public static int TEST_CASE_FAIL_STATUS = 5;
    
    public static void addResultsForTestCase(String testcaseId, int status, String error) {
        
        String testRunID = Test_Run_ID;
        APIClient client = new APIClient(TEST_RAIL_ENGINE_URL);
        client.setUser(TEST_RAIL_USERNAME);
        client.setPassword(TEST_RAIL_PASSWORD);
        
        Map<String,Object>data = new HashMap<String, Object>();
        data.put("status_id",status);
        data.put("comment","this test execauted on demo application" + error);
        
        try {
            client.sendPost("add_result_for_case/"+testRunID +"/"+testcaseId , data);
        } catch (IOException | APIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
