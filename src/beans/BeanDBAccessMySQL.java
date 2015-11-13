/**
 * BeanDBAccessMySQL
 *
 * Copyright (C) 2012 Sh1fT
 *
 * This file is part of BeanDBAccess.
 *
 * BeanDBAccess is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * BeanDBAccess is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BeanDBAccess; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package beans;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import utils.PropertiesLauncher;

/**
 * Manage a {@link BeanDBAccessMySQL}
 * @author Sh1fT
 */
public class BeanDBAccessMySQL extends BeanDBAccessClassical {
    /**
     * Create a new {@link BeanDBAccessMySQL} instance
     */
    public BeanDBAccessMySQL() {
        this.setDBConnection(null);
        this.setDBStatement(null);
        this.setPropertiesLauncher(null);
        this.setWorking(false);
    }

    /**
     * Create a new {@link BeanDBAccessMySQL} instance
     * @param propertiesPath 
     */
    public BeanDBAccessMySQL(String propertiesPath) {
        this();
        this.setPropertiesLauncher(new PropertiesLauncher(propertiesPath));
        this.initialize(this.getDBHost(), this.getDBPort(), this.getDBName(),
                this.getDBUsername(), this.getDBPassword(), this.getDBAutoReconnect(),
                this.getDBAutoCommit());
    }

    /**
     * Create a new {@link BeanDBAccessMySQL} instance
     * @param dbHost
     * @param dbPort
     * @param dbName
     * @param dbUsername
     * @param dbPassword
     * @param dbAutoreconnect
     * @param dbAutocommit 
     */
    public BeanDBAccessMySQL(String dbHost, Integer dbPort, String dbName,
            String dbUsername, String dbPassword, String dbAutoreconnect,
            Boolean dbAutocommit) {
        this();
        this.initialize(dbHost, dbPort, dbName, dbUsername, dbPassword,
                dbAutoreconnect, dbAutocommit);
    }

    /**
     * Initialize a MySQL Database
     * @param dbHost
     * @param dbPort
     * @param dbName
     * @param dbUsername
     * @param dbPassword
     * @param dbAutoreconnect
     * @param dbAutocommit 
     */
    public void initialize(String dbHost, Integer dbPort, String dbName,
            String dbUsername, String dbPassword, String dbAutoreconnect,
            Boolean dbAutocommit) {
        String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("autoReconnect", dbAutoreconnect);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.setDBConnection(DriverManager.getConnection(url, p));
            this.getDBConnection().setAutoCommit(dbAutocommit);
            this.setDBStatement(this.getDBConnection().createStatement(
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE));
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
            this.shutdown();
            System.exit(1);
        }
    }

    /**
     * Initialize a MySQL Database
     */
    public void initialize() {
        this.initialize(this.getDBHost(), this.getDBPort(), this.getDBName(),
                this.getDBUsername(), this.getDBPassword(),
                this.getDBAutoReconnect(), this.getDBAutoCommit());
    }
}