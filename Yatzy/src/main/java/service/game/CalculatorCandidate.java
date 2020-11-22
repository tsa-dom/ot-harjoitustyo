/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import java.util.*;

/**
 *
 * @author Tapio Salonen
 */
public class CalculatorCandidate {
    private static List<Integer> dices;
    private static HashSet<Integer> calculated;
    private static boolean ready;
            
    public CalculatorCandidate() {
        
    }
    
    public int getPoints(Objective objective, List<Integer> diceList) {
        int points = 0;
        dices = diceList;
        calculated = new HashSet<>();
        ready = true;
        String[] requirement = objective.getRequirement().split("Z");
        boolean ready = true;
        for (int i = 0; i < requirement.length; i++) {
            points = calculate(requirement[i], points);
            
            if (customOrNot(requirement[i], points) != -1) {
                return customOrNot(requirement[i], points);
            }
        }
        return 0;
    }
    public int customOrNot(String requirement, int points) {
        if (requirement.equals("m") && ready) {
            return points;
        } else if (requirement.equals("c") && ready) {
            return Integer.valueOf(requirement.split("/")[1]);
        }
        return -1;
    }
    
    public int calculate(String requirement, int points) {
        if (requirement.substring(0, 1).equals("x")) {
            points += times(requirement.substring(1));
        } else if (requirement.substring(0, 1).equals("y")) {
            points += upperSection(requirement.substring(1));
        } else if (requirement.substring(0, 1).equals("r")) {
            points += random(requirement.substring(1));
        } else if (requirement.substring(0, 1).equals("d")) {
            points += straight(requirement.substring(1));
        }
        return points;
    }
    
    public int enough(int times, int diceFace, int count) {
        for (int i = 1; i < dices.size(); i++) {
            if (calculated.contains(diceFace)) {
                diceFace = dices.get(i);
                continue;
            } else if (diceFace == dices.get(i)) {
                count++;
            } else {
                diceFace = dices.get(i);
                count = 1;
            }
            if (count == times) {
                calculated.add(diceFace);
                return times * diceFace;
            }
        }
        return 0;
    }
    
    public int times(String requirement) {
        int times = Integer.valueOf(requirement);
        return  enough(times, dices.get(0), 1);
    }
    
    public int upperSection(String requirement) {
        int value = Integer.valueOf(requirement);
        int sum = 0;
        for (int dice : dices) {
            if (dice == value) {
                sum += value;
            }
        }
        return sum;
    }
    
    public int random(String requirement) {
        int times = Integer.valueOf(requirement);
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum += dices.get(0);
            dices.remove(0);
        }
        return sum;
    }
    
    public int straight(String requirement) {
        String[] interval = requirement.split("A");
        int highest = Integer.valueOf(interval[0]);
        int lowest = Integer.valueOf(interval[1]);
        int sum = 0;
        for (int dice : dices) {
            if (highest == dice) {
                sum += highest;
                highest--;
            }
            if (highest == lowest - 1) {
                return sum;
            }
        }
        return 0;
    }
}

