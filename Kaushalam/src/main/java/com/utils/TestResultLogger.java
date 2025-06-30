package com.utils;


import java.io.FileWriter;
import java.io.IOException;

public class TestResultLogger {

    public static synchronized void logResult(String testCaseName, String result) {
        try (FileWriter writer = new FileWriter("testResults.csv", true)) {
            writer.append(testCaseName)
                  .append(",")
                  .append(result)
                  .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
