/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

import java.util.List;

public interface DataIF {
    /** Executes given SQL statement.
     * @param content Given SQL statement
     * @param databaseName Given database name
     * @param folder Given database location folder
     * @return Returns true if the statement is successful, otherwise false
     */
    boolean executeStatement(String content, String databaseName, String folder);
    /** Executes list of SQL statements.
     * @param contentList Given list containing SQL statements
     * @param databaseName Given database name
     * @param folder Given database location folder
     * @return Returns true if the statement is successful, otherwise false
     */
    boolean executeStatements(List<String> contentList, String databaseName, String folder);
    /** Executes SQL statement in SELECT format.
     * @param content Given SQL statement
     * @param databaseName Given database name
     * @param columnLabel Given database table column name
     * @param folder Given database location folder
     * @return Return column items as a list
     */
    List<String> selectFrom(String content, String databaseName, String columnLabel, String folder);
}
