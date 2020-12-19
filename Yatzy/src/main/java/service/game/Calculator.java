/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import service.domain.CalculatorIF;
import service.domain.ObjectiveIF;

public class Calculator implements CalculatorIF {
    // Keeps count for calculated dices marked as "x"
    private static Set<Integer> calculated;
    // Have to be true so player could receive points. Variable is only affected by using "times" method.
    private static boolean ready;
    // True if upper section is copletely calculated, otherwise false.
    private boolean upperCalculated;
    // Points player should receive if she/he reaches upper section requirement.
    private int upperPoints;
    // Keeps in track all dices used by any method.
    private Set<Integer> used;
    
    /** Creates new calculator.
     */
    public Calculator() {
        calculated = new HashSet<>();
        upperPoints = 0;
        upperCalculated = false;
        used = new HashSet<>();
    }
    @Override
    public int getPoints(ObjectiveIF objective, List<Integer> dices) {
        int points = 0;
        calculated.clear();
        used.clear();
        ready = true;
        String[] requirement = objective.getRequirement().split("Z");
        for (int i = 0; i < requirement.length; i++) {
            points = calculate(requirement[i], points, dices);
            if (customOrNot(requirement[i], points, ready) != -1) {
                return customOrNot(requirement[i], points, ready);
            }
        }
        return 0;
    }
    @Override
    public int customOrNot(String requirement, int points, boolean ready) {
        if (requirement.equals("m") && ready) {
            return points;
        } else if (requirement.split("/")[0].equals("c") && ready) {
            return Integer.valueOf(requirement.split("/")[1]);
        }
        return -1;
    }
    
    @Override
    public int calculate(String requirement, int points, List<Integer> dices) {
        if (requirement.substring(0, 1).equals("x")) {
            points += times(requirement.substring(1), dices);
        } else if (requirement.substring(0, 1).equals("y")) {
            points += upperSection(requirement.substring(1), dices);
            upperCountCheck(upperSection(requirement.substring(1), dices));
        } else if (requirement.substring(0, 1).equals("r")) {
            points += random(requirement.substring(1), dices);
        } else if (requirement.substring(0, 1).equals("d")) {
            points += straight(requirement.substring(1), dices);
        }
        return points;
    }
    
    @Override
    public int times(String requirement, List<Integer> dices) {
        Set<Integer> memory = new HashSet<>();
        int diceFace = -1;
        for (int i = 0; i < dices.size(); i++) {
            if (diceFace == dices.get(i) && !memory.contains(i)) {
            } else {
                diceFace = dices.get(i);
                memory.clear();
            }
            memory.add(i);
            if (memory.size() >= Integer.valueOf(requirement) && !calculated.contains(diceFace)) {
                calculated.add(diceFace);
                Stream.of(used, memory).forEach(used::addAll);
                return Integer.valueOf(requirement) * diceFace;
            }
        }
        ready = false;
        return 0;
    }
    
    @Override
    public int upperSection(String requirement, List<Integer> dices) {
        int value = Integer.valueOf(requirement);
        int sum = 0;
        for (int i = 0; i < dices.size(); i++) {
            if (dices.get(i) == value && !used.contains(i)) {
                used.add(i);
                sum += dices.get(i);
            } 
        }
        return sum;
    }
    
    @Override
    public int random(String requirement, List<Integer> dices) {
        int times = Integer.valueOf(requirement);
        int sum = 0;
        int count = 0;
        int i = 0;
        while (count < times && i < dices.size()) {
            if (!used.contains(i)) {
                sum += dices.get(i);
                count++;
                used.add(i);
            }
            i++;
        }
        return sum;
    }
    
    @Override
    public int straight(String requirement, List<Integer> dices) {
        String[] interval = requirement.split("A");
        int highest = Integer.valueOf(interval[0]);
        int lowest = Integer.valueOf(interval[1]);
        int sum = 0;
        for (int i = 0; i < dices.size(); i++) {
            if (highest < dices.get(i)) {
                continue;
            } else if (highest == dices.get(i)) {
                used.add(i);
                sum += highest;
                highest--;
            } 
            if (highest == lowest - 1) {
                return sum;
            }
        }
        return 0;
    }
    
    @Override
    public void upperCountCheck(int points) {
        upperPoints += points;
        if (upperPoints >= Core.getGameMode().getBonusRequirement()) {
            upperCalculated = true;
        }
    }
    @Override
    public boolean getUpperStatus() {
        return upperCalculated;
    }
}

