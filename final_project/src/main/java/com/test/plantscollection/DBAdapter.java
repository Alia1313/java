package com.test.plantscollection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
    static Connection con;

    static void init(){
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:plants.sqlite");
                Statement stmt = con.createStatement();
                String sql="""
                            CREATE TABLE IF NOT EXISTS 'plants' 
                                ('plant_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                                'plant_name' TEXT NOT NULL,
                                'harmful' VARCHAR(8)
                                );
                            """;
                stmt.execute(sql);
                sql="""
                    CREATE TABLE IF NOT EXISTS 'properties' 
                        ('property_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        'plant_id' INT,
                        'property_text' TEXT NOT NULL
                        );
                    """;
                stmt.execute(sql);
                System.out.println("Tables created");
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    static ArrayList<Property> selectProperties(int plantId) throws SQLException {
        ArrayList<Property> properties = new ArrayList<Property>();
        String sql = "SELECT * FROM properties WHERE plant_id = " + plantId;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            properties.add(new Property(rs.getInt("property_id"), rs.getString("property_text")));
        }
        return properties;
    }

    static void insertProperty(int plantId, String propertyText) throws SQLException {
        String sql = "INSERT INTO properties(plant_id, property_text) VALUES('"+plantId+"','"+propertyText+"')";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
        System.out.println("Inserted data");
    }

    static void deleteProperty(int propertytId) throws SQLException {
        String sql = "DELETE FROM properties WHERE property_id = '"+propertytId+"'";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
        System.out.println("Deleted data");
    }


    static void insertPlant(String plantName, String harmful) throws SQLException {
        String sql = "INSERT INTO plants(plant_name, harmful) VALUES('"+plantName+"','"+harmful+"')";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
        System.out.println("Inserted data");
    }

    static void deletePlantProperties(int plantId) throws SQLException {
        String sql = "DELETE FROM properties WHERE plant_id = '"+plantId+"'";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
        System.out.println("Deleted data");
    }

    static void updatePlant(int plantId, String plantName, String harmful) throws SQLException {
        String sql = "UPDATE plants SET plant_name = '"+plantName+"', harmful = '"+harmful+"' WHERE plant_id='" +plantId +"'";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
        System.out.println("Updated data");
    }

    static ArrayList<Plant> selectPlants() throws SQLException {
        ArrayList<Plant> plants = new ArrayList<Plant>();

        String sql = "SELECT * FROM plants";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("plant_id");
            String name = rs.getString("plant_name");
            String harmful = rs.getString("harmful");
            plants.add(new Plant(id, name, harmful));
        }
        return plants;
    }

    static void deletePlant(Integer id) throws SQLException {
        String sql = "DELETE FROM plants WHERE plant_id='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Deleted data");
    }
}
