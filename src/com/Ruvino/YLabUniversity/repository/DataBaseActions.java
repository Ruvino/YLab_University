package com.Ruvino.YLabUniversity.repository;

import com.Ruvino.YLabUniversity.model.Player;
import com.Ruvino.YLabUniversity.utils.DataBaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.Ruvino.YLabUniversity.utils.MyWriter.winPlayer;

public abstract class DataBaseActions implements DataBaseUtils {

    static String createTable(String tableName, String primaryKey) {

        return String.format("CREATE TABLE %s (%s, PRIMARY KEY ( %s ))", tableName,
                columns("GameId:INTEGER:NotNull",
                        "player1Name:STRING",
                        "player2Name:STRING",
                        "winPlayer:String"),
                primaryKey);

    }

    static String readTable(String tableName) {

        return "SELECT * FROM " + tableName;

    }

    static String readTable(String tableName, String... name) {

        return "SELECT " + String.join(", ", name) + " FROM " + tableName;

    }

    static String columns(String... fieldName) {
        String resultString = "";

        ArrayList<Columns> columnsName = new ArrayList<>(fieldName.length);

        for (String strings : fieldName) {

            String[] s = strings.split(":");

            String type = "";

            if (s[1].equalsIgnoreCase("integer")) type = TYPESQLINTEGER;
            else if (s[1].equalsIgnoreCase("string")) type = TYPESQLSTRING;

            if (s.length == 3) columnsName.add(new Columns(s[0], type, true));
            else if (s.length == 2) columnsName.add(new Columns(s[0], type));

        }

        resultString = columnsName.stream().map(Columns::toString)
                .collect(Collectors.joining(", "));

        return resultString;
    }

    public static void initDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = DataBaseActions.createTable("GameResult", "GameId");
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void deleteTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "DROP TABLE GameResult";
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void addFieldToDataBase(int numId, ArrayList<Player> players) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = String.format("INSERT INTO GameResult VALUES (%d, '%s', '%s', '%s')", numId,
                    players.get(0).getName(),
                    players.get(1).getName(),
                    winPlayer != null ? winPlayer.getName() : "Ничья");
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }

    public static void readDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = DataBaseActions.readTable("GameResult");
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            int id = 0;
            String player1Name = null;
            String player2Name = null;
            String winPlayer = null;
            while (rs.next()) {
                // Retrieve by column name
                if (isExist(rs, "GameId")) id = rs.getInt("GameId");
                if (isExist(rs, "player1Name")) player1Name = rs.getString("player1Name");
                if (isExist(rs, "player2Name")) player2Name = rs.getString("player2Name");
                if (isExist(rs, "winPlayer")) winPlayer = rs.getString("winPlayer");

                // Display values
                System.out.print("GameId: " + id);
                System.out.print(", First player name: " + player1Name);
                System.out.print(", Second player name: " + player2Name);
                System.out.println(", Win player: " + winPlayer);
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }

    private static boolean isExist(ResultSet resultSet, String columnName) throws SQLException {
        if (columnName == null || (columnName = columnName.trim()).isEmpty())
            return false;

        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++)
            if (columnName.equalsIgnoreCase(metaData.getColumnName(i)))
                return true;

        return false;
    }

    static class Columns {

        String fieldName;
        String notNull = "";
        String type;

        Columns(String fieldName, String type) {
            this.fieldName = fieldName;
            this.type = type;
        }

        Columns(String fieldName, String type, boolean isNotNull) {
            this(fieldName, type);
            if (isNotNull) notNull = NOTNULL;
        }

        @Override
        public String toString() {
            return notNull == "" ? String.format("%s %s", fieldName, type)
                    : String.format("%s %s %s", fieldName, type, notNull);
        }
    }
}
