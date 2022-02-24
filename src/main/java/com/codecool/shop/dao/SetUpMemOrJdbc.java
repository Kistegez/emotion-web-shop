package com.codecool.shop.dao;


import java.io.FileReader;
import java.util.Objects;
import java.util.Properties;

public class SetUpMemOrJdbc {

    public boolean readResource() {
        try {
            Properties props = new Properties();
            String dbSettingsPropertyFile = "src/main/resources/connection.properties";
            FileReader fReader = new FileReader(dbSettingsPropertyFile);
            props.load(fReader);
            String dbKeyWord = props.getProperty("db.dao");

            if (Objects.equals(dbKeyWord, "jdbc")) {
                return true;
            }
            else if(Objects.equals(dbKeyWord, "memory")){
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}


