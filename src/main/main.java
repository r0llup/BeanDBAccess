/**
 * main
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

package main;

import beans.BeanDBAccessCSV;
import beans.BeanDBAccessMySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manage a {@link Main}
 * @author Sh1fT
 */
public class main {
    public static void main (String[] args) {
        BeanDBAccessMySQL bdbam = null;
        BeanDBAccessCSV bdbac = null;
        try {
            System.out.println("_____________________________________________");
            bdbam = new BeanDBAccessMySQL(System.getProperty("file.separator") +
                    "properties" + System.getProperty("file.separator") +
                    "BeanDBAccessMySQL.properties");
            String query = "SELECT COUNT(*) AS cnt FROM voyageursaccompagnants WHERE voyageurTitulaire LIKE ?;";
            PreparedStatement ps = bdbam.getDBConnection().prepareStatement(query);
            ps.setString(1, "1");
            ResultSet rs = bdbam.executeQuery(ps);
            while (rs.next())
                System.out.println("\nresults: " + rs.getInt("cnt"));
            rs.close();
            System.out.println("_____________________________________________");
            query = "SELECT * FROM voyageurs WHERE prenom LIKE 'Jean';";
            rs = bdbam.executeQuery(query);
            while (rs.next()) {
                System.out.println("\nidVoyageur: " + rs.getString("idVoyageur"));
                System.out.println("nom: " + rs.getString("nom"));
                System.out.println("prenom: " + rs.getString("prenom"));
                System.out.println("adresseDomicile: " + rs.getString("adresseDomicile"));
                System.out.println("adresseEmail: " + rs.getString("adresseEmail"));
            }
            rs.close();
            bdbam.stop();
            System.out.println("_____________________________________________");
            bdbac = new BeanDBAccessCSV("csv", ";", "false", ".csv");
            query = "SELECT COUNT(*) AS cnt FROM F_AGENTS";
            rs = bdbac.executeQuery(query);
            while (rs.next())
                System.out.println("\nresults: " + rs.getInt("cnt"));
            rs.close();
            System.out.println("_____________________________________________");
            query = "SELECT * FROM F_AGENTS WHERE niveau LIKE '1'";
            rs = bdbac.executeQuery(query);
            while (rs.next()) {
                System.out.println("\nnom: " + rs.getString("nom"));
                System.out.println("prenom: " + rs.getString("prenom"));
                System.out.println("niveau: " + rs.getString("niveau"));
                System.out.println("password: " + rs.getString("password"));
            }
            rs.close();
            bdbac.stop();
        } catch (SQLException ex) {
            System.out.printf("Error: " + ex.getLocalizedMessage());
            bdbam.shutdown();
            bdbac.shutdown();
            System.exit(1);
        }
    }
}