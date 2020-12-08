/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

import java.util.List;

public interface SQLIF {
    /** Search statements inside jar sql files.
     * @param path Given path
     * @return Return statements as a list.
     */
    List<String> searchStatements(String path);
    
}
