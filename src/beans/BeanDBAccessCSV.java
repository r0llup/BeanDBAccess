/**
 * BeanDBAccessCSV
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
 * Manage a {@link BeanDBAccessCSV}
 * @author Sh1fT
 */
public class BeanDBAccessCSV extends BeanDBAccess{
    /**
     * Create a new {@link BeanDBAccessCSV} instance
     */
    public BeanDBAccessCSV() {
        this.setDBConnection(null);
        this.setDBStatement(null);
        this.setPropertiesLauncher(null);
        this.setWorking(false);
    }

    /**
     * Create a new {@link BeanDBAccessCSV} instance
     * @param propertiesPath 
     */
    public BeanDBAccessCSV(String propertiesPath) {
        this();
        this.setPropertiesLauncher(new PropertiesLauncher(propertiesPath));
        this.initialize(this.getDBPath(), this.getDBSeparator(),
                this.getDBSuppressHeaders(), this.getDBFileExtension());
    }

    /**
     * Create a new {@link BeanDBAccessCSV} instance
     * @param dbPath
     * @param dbSeparator
     * @param dbSuppressHeaders
     * @param dbExtension 
     */
    public BeanDBAccessCSV(String dbPath, String dbSeparator, String dbSuppressHeaders,
            String dbExtension) {
        this();
        this.initialize(dbPath, dbSeparator, dbSuppressHeaders, dbExtension);
    }

    /**
     * Initialize a CSV Database
     * @param dbPath
     * @param dbSeparator
     * @param dbSuppressHeaders
     * @param dbExtension 
     */
    public void initialize(String dbPath, String dbSeparator, String dbSuppressHeaders,
            String dbExtension) {
        try {
            String url = "jdbc:relique:csv:" + dbPath;
            Properties p = new Properties();
            p.put("separator", dbSeparator);
            p.put("suppressHeaders", dbSuppressHeaders);
            p.put("fileExtension", dbExtension);
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            this.setDBConnection(DriverManager.getConnection(url, p));
            this.setDBStatement(this.getDBConnection().createStatement(
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE));
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
            this.shutdown();
            System.exit(1);
        }
    }

    /**
     * Initialize a CSV Database
     */
    public void initialize() {
        this.initialize(this.getDBPath(), this.getDBSeparator(),
                this.getDBSuppressHeaders(), this.getDBFileExtension());
    }

    public String getDBPath() {
        return this.getDBProperties().getProperty("DBPath");
    }

    public String getDBSeparator() {
        return this.getDBProperties().getProperty("DBSeparator");
    }

    public String getDBSuppressHeaders() {
        return this.getDBProperties().getProperty("DBSuppressHeaders");
    }

    public String getDBFileExtension() {
        return this.getDBProperties().getProperty("DBFileExtension");
    }
}