package com.mkyong.jdbc;

import com.mkyong.jdbc.model.Employee;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample2 {

    public static void main(String[] args) {

        System.out.println("MySQL JDBC Connection Testing ~");

        List<Employee> result = new ArrayList<>();

        String SQL_SELECT = "Select * from User";

        // auto close connection and preparedStatement
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/springsecurity?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&verifyServerCertificate=true&useSSL=true&requireSSL=true&clientCertificateKeyStoreUrl=file:/opt/keystore.jks&clientCertificateKeyStorePassword=securekeymysql&trustCertificateKeyStoreUrl=file:/opt/truststore.jks&trustCertificateKeyStorePassword=securekeymysql", "root", "Hakase-labs123@");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String roles = resultSet.getString("roles");

                Employee obj = new Employee();
                obj.setUserName(userName);
                obj.setPassword(password);
                obj.setRoles(roles);
                result.add(obj);

            }
            result.forEach(x -> System.out.println(x));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
