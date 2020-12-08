/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

/** Contains game dice methods.
 */
public interface DiceLogicIF {
    /** Gives new value to the dice as string.
     * @return New dice value
     */
    String newDiceValue();
    /** Gives button statuses as a String array.
     * @param buttonText Current button text
     * @return Return button statuses as a String array
     */
    String[] getSelectStatus(String buttonText);

}