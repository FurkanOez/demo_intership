package com.github.fukanoez.demo_intership.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class HelloWorldService {
    private Connection conn = null;
    private Statement stmt = null;

    @BeforeAll
    static void setupOnce() throws Exception {
        Class.forName("org.duckdb.DuckDBDriver");
    }

    @BeforeEach
    void setup() throws SQLException {
        conn = DriverManager.getConnection("jdbc:duckdb:");
        stmt = conn.createStatement();
    }

    @Test
    void whenQueryCurrentDate_thenReturnToday() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT current_date");
        Date currentDate = rs.next() ? rs.getDate(1) : null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date expectedDate = (Date) calendar.getTime();

        assertThat(currentDate).isEqualTo(expectedDate);
    }


    @AfterEach
    void tearDown() throws SQLException {
        stmt.close();
        conn.close();
    }
}
