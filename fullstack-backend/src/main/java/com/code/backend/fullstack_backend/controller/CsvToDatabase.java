package com.code.backend.fullstack_backend.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CsvToDatabase {
    public static void main(String[] args) {
        String csvFile = "E:/news_data.csv";
        String jdbcURL = "jdbc:mysql://localhost:3306/fullstack"; // Thay đổi với tên cơ sở dữ liệu của bạn
        String username = "root"; // Thay đổi với tên người dùng của bạn
        String password = "tmkhoa"; // Thay đổi với mật khẩu của bạn

        String line;
        String csvSplitBy = ",";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String sql = "INSERT INTO news (title, summary, content) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Bỏ qua tiêu đề
            br.readLine();

            while ((line = br.readLine()) != null) {
                line = line.replace("\"", ""); // Loại bỏ dấu ngoặc kép
                String[] data = line.split(csvSplitBy);
                statement.setString(1, data[0]); // title
                statement.setString(2, data[1]); // summary
                statement.setString(3, data[2]); // content
                statement.addBatch();
            }
            statement.executeBatch(); // Thực hiện batch insert
            System.out.println("Dữ liệu đã được nhập thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

