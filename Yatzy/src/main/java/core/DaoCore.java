/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.dao.DatabaseAccess;
import core.dao.PropertiesLoader;
import core.dao.SQLLoader;
import core.domain.DataIF;
import core.domain.PropertiesIF;
import core.domain.SQLIF;

/** Software dao class references are here.
 */
public class DaoCore {
    protected final DataIF dbAccess;
    protected final PropertiesIF propertiesLoader;
    protected final SQLIF sqlLoader;
    
    protected DaoCore() {
        dbAccess = new DatabaseAccess();
        propertiesLoader = new PropertiesLoader();
        sqlLoader = new SQLLoader();
    }
}
