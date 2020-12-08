/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

public interface ObjectiveIF {
    /** Gets objective name.
     * @return Returns objective name
     */
    String getName();
    /** Gets points stored to objective.
     * @return Returns points
     */
    String getPoints();
    /** Gets identifier of the object.
     * @return Returns identifier
     */
    int getId();
    /** Gets objective complete requirement.
     * @return Returns requirement as a String
     */
    String getRequirement();
    /** Sets points to the objective.
     * @param points Given points
     */
    void setPoints(String points);
    
}
