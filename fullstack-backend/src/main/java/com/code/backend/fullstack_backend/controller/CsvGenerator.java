package com.code.backend.fullstack_backend.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvGenerator {

    public static void main(String[] args) {
        String filePath = "E:\\news_data.csv";
        int numberOfEntries = 20000;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("title,summary,content");
            writer.newLine();
            for (int i = 1; i <= numberOfEntries; i++) {
                String title;
                String summary;
                String content;
                if (i <= 1000) {
                    title = "Harvard Business Review " + i;
                    summary = "Review " + i;
                    content = "Harvard Business Review Details " + i;
                } else if (i <= 5000) {
                    title = "The Daily Mail " + (i - 5000);
                    summary = "Daily Mail " + i;
                    content = "The Daily Mail Details " + i;
                } else {
                    title = "The New York Times " + i;
                    summary = "New York Times " + i;
                    content = "The New York Times " + i;
                }

                writer.write(String.format("\"%s\",\"%s\",\"%s\"", title, summary, content));
                writer.newLine();
            }

            System.out.println("File CSV " + filePath + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



