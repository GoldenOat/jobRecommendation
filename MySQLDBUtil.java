package com.jobRecommendation.job.db;

public class MySQLDBUtil {
    private static final String INSTANCE = "laiproject-instance.cgxyljeej7as.us-east-1.rds.amazonaws.com";
    private static final String PORT_NUM = "3306";
    public static final String DB_NAME = "laiproject";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "Sara697094~";
    public static final String URL = "jdbc:mysql://"
            + INSTANCE + ":" + PORT_NUM + "/" + DB_NAME
            + "?user=" + USERNAME + "&password=" + PASSWORD
            + "&autoReconnect=true&serverTimezone=UTC";
}

