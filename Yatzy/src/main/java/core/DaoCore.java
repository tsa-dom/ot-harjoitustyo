/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import dao.*;

/**
 *
 * @author Tapio Salonen
 */
public class DaoCore {
    protected final DatabaseAccess dbAccess;
    protected final PropertiesLoader propertiesLoader;
    protected final SQLLoader sqlLoader;
    
    protected DaoCore() {
        dbAccess = new DatabaseAccess();
        propertiesLoader = new PropertiesLoader();
        sqlLoader = new SQLLoader();
    }
}
