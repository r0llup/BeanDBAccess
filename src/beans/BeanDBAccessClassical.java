/**
 * BeanDBAccessClassical
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

/**
 * Manage a {@link BeanDBAccessClassical}
 * @author Sh1fT
 */
public class BeanDBAccessClassical extends BeanDBAccess {
    public String getDBHost() {
        return this.getDBProperties().getProperty("DBHost");
    }

    public Integer getDBPort() {
        return Integer.parseInt(this.getDBProperties().getProperty("DBPort"));
    }

    public String getDBName() {
        return this.getDBProperties().getProperty("DBName");
    }

    public String getDBUsername() {
        return this.getDBProperties().getProperty("DBUsername");
    }

    public String getDBPassword() {
        return this.getDBProperties().getProperty("DBPassword");
    }

    public String getDBAutoReconnect() {
        return this.getDBProperties().getProperty("DBAutoReconnect");
    }

    public Boolean getDBAutoCommit() {
        return Boolean.valueOf(this.getDBProperties().getProperty("DBAutoCommit"));
    }
}