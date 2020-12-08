/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

import java.util.Properties;

public interface PropertiesIF {
    /** Loading properties inside jar file.
     * @param path Given path
     * @return Returns loaded properties
     */
    Properties loadProperties(String path);
    /** Loading properties outside jar file.
     * @param path Given path
     * @return Returns loaded properties
     */
    Properties loadFromPath(String path);
    
}
