/**
 * BeanDBAccessManager
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
 * Manage a {@link BeanDBAccessManager}
 * @author Sh1fT
 */
public class BeanDBAccessManager {
    public static final int DB_CSV = 1;
    public static final int DB_MYSQL = 2;
    public static final int DB_ORACLE = 3;

    /**
     * Create a custom instance for a certain database
     * @param dbType
     * @return 
     */
    public static BeanDBAccess getInstance(Integer dbType) {
        switch (dbType) {
            case BeanDBAccessManager.DB_CSV:
                return new BeanDBAccessCSV();
            case BeanDBAccessManager.DB_MYSQL:
                return new BeanDBAccessMySQL();
            case BeanDBAccessManager.DB_ORACLE:
                return new BeanDBAccessOracle();
        }
        return null;
    }
}