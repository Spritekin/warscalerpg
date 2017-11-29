package com.spritekin.warscale.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TestWriteFile {

    public static void main(String[] args) {
        BufferedWriter writer = null;
        try {
            File logFile = new File("filewritetest.txt");

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Hello world!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}