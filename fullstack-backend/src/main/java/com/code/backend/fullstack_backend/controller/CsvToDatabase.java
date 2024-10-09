package com.code.backend.fullstack_backend.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CsvToDatabase {
    public static void main(String[] args) {
        String csvFile = "E:/news_data.csv";
        String jdbcURL = "jdbc:mysql://localhost:3306/fullstack";
        String username = "root";
        String password = "tmkhoa";

        String line;
        String csvSplitBy = ",";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String sql = "INSERT INTO news (title, summary, content) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                String[] data = line.split(csvSplitBy);
                statement.setString(1, data[0]); // title
                statement.setString(2, data[1]); // summary
                statement.setString(3, data[2]); // content
                statement.addBatch();
            }
            statement.executeBatch();
            System.out.println("Data was imported successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

