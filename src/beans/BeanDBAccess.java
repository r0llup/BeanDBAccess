/**
 * BeanDBAccess
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import utils.PropertiesLauncher;

/**
 * Manage a {@link BeanDBAccess}
 * @author Sh1fT
 */
public class BeanDBAccess implements interfaces.BeanDBAccess {
    private Connection dbConnection;
    private Statement dbStatement;
    private PropertiesLauncher propertiesLauncher;
    private Boolean working;

    /**
     * Initialize the bean
     */
    @Override
    public void init() {
        this.setWorking(true);
    }

    /**
     * Stop the bean
     */
    @Override
    public void stop() {
        this.shutdown();
        this.setWorking(false);
    }

    /** 
     * Close the connection with the database
     */
    @Override
    public void shutdown() {
        try {
            if (this.getDBStatement() != null)
                this.getDBStatement().close();
            if (this.getDBConnection() != null)
                this.getDBConnection().close();
        } catch (SQLException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
            System.exit(1);
        }
    }

    @Override
    public Connection getDBConnection() {
        return dbConnection;
    }

    public void setDBConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Statement getDBStatement() {
        return dbStatement;
    }

    public void setDBStatement(Statement dbStatement) {
        this.dbStatement = dbStatement;
    }

    public PropertiesLauncher getPropertiesLauncher() {
        return propertiesLauncher;
    }

    public void setPropertiesLauncher(PropertiesLauncher propertiesLauncher) {
        this.propertiesLauncher = propertiesLauncher;
    }

    public void setPropertiesPath(String propertiesPath) {
        this.setPropertiesLauncher(new PropertiesLauncher(propertiesPath));
    }

    @Override
    public Boolean isWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }

    public Properties getDBProperties() {
        return this.getPropertiesLauncher().getProperties();
    }

    public String getDBQuery() {
        return this.getDBProperties().getProperty("DBQuery");
    }

    /**
     * Execute a query (SELECT)
     * @param query
     * @return 
     */
    public ResultSet executeQuery(String query) {
        try {
            ResultSet rs = this.getDBStatement().executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
            this.shutdown();
            System.exit(1);
        }
        return null;
    }

    /**
     * Execute a query (SELECT)
     * @param ps
     * @return 
     */
    public ResultSet executeQuery(PreparedStatement ps) {
        try {
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
            this.shutdown();
            System.exit(1);
        }
        return null;
    }

    /**
     * Execute a query (SELECT)
     * @return 
     */
    public ResultSet executeQuery() {
        return this.executeQuery(this.getDBQuery());
    }

    /**
     * Execute an update (INSERT, UPDATE, DELETE)
     * @param query
     * @return 
     */
    public Integer executeUpdate(String query) {
        try {
            Integer rs = this.getDBStatement().executeUpdate(query);
            return rs;
        } catch (SQLException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
//            this.shutdown();
//            System.exit(1);
        }
        return null;
    }

    /**
     * Execute an update (INSERT, UPDATE, DELETE)
     * @param ps
     * @return 
     */
    public Integer executeUpdate(PreparedStatement ps) {
        try {
            Integer rs = ps.executeUpdate();
            return rs;
        } catch (SQLException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
//            this.shutdown();
//            System.exit(1);
        }
        return null;
    }

    /**
     * Execute an update (INSERT, UPDATE, DELETE)
     * @return 
     */
    public Integer executeUpdate() {
        return this.executeUpdate(this.getDBQuery());
    }
}