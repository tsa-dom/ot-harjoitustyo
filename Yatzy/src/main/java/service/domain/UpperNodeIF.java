/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

public interface UpperNodeIF {
    /** Resets upper section completed count.
     */
    void clearUpperCount();
    /** Adds upper section completed count by one.
     */
    void addUpperCount();
    /** Sets bonus points.
     * @param points Given points
     */
    void setBonusPoints(int points);
    /** Gets bonus points.
     * @return Returns bonus points
     */
    int getBonusPoints();
    
}
