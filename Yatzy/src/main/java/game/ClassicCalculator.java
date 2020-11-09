/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Tapio Salonen
 */
public class ClassicCalculator{
    public int upperComplete;
    public ClassicCalculator(){
        this.upperComplete = 0;
    }
    public int getPoints(Objective objective, int k){
        return k;
    }
    public int getPoints(Objective objective, String dices){
        Classic classic = new Classic();
        int points = 0;
        List<Integer> diceList = new ArrayList<>();
        for (int i=0;i<dices.length();i++){
            diceList.add(Integer.valueOf(dices.charAt(i)));
        }
        Collections.sort(diceList,Collections.reverseOrder());
        String requirement = objective.getRequirement();
        if(requirement.charAt(0)==121){
            points = upperSection(requirement,diceList);
        }else if(requirement.charAt(0)==100){
            points = straight(requirement,diceList);
        }else if(requirement.charAt(0)>48&&requirement.charAt(0)<58){
            points = counter(requirement,diceList);
        }
        return points;
    }
    public int upperSection(String givenRequirement, List<Integer> diceList){
        int count = 0;
        int diceFace = Integer.valueOf(givenRequirement.charAt(1));
        for (int i=0;i<diceList.size();i++){
            if (diceFace==diceList.get(i)){
                count += diceFace-48;
            }
        }
        return count;
    }
    public int straight(String givenRequirement, List<Integer> diceList){
        int count = 0;
        int startFace = Integer.valueOf(givenRequirement.charAt(1));
        boolean coming = true;
        for(int i=0;i<diceList.size();i++){
            if(diceList.get(i)==startFace){
                count+=startFace-48;
                startFace--;
            } else{
                coming = false;
            }
        }
        if (coming){
            return count;
        }
        return 0;
    }
    public int counter(String givenRequirement, List<Integer> diceList){
        int count = 0;
        int amount = givenRequirement.charAt(0)-48;
        boolean sessionEnd = true;
        HashSet<Integer> calculated = new HashSet<>();
        for(int i=1;i<givenRequirement.length();i++){
            if(givenRequirement.charAt(i)==120){
                int numberCount = 1;
                for(int j=0;j<diceList.size()-1;j++){
                    if (diceList.get(j)==diceList.get(j+1)){
                        numberCount++;
                    } else{
                        numberCount = 1;
                    }
                    if (numberCount==amount && calculated.contains(diceList.get(j))==false){
                        count += amount*(diceList.get(j)-48);
                        calculated.add(diceList.get(j));
                        break;
                    }
                    if (j==diceList.size()-2){
                        sessionEnd = false;
                    }
                }
            } else if(givenRequirement.charAt(i)==114){
                for(int j=0;j<amount;j++){
                    count += diceList.get(j)-48;
                }
            } else if(givenRequirement.charAt(i)>48&&givenRequirement.charAt(i)<58){
                amount = givenRequirement.charAt(i)-48;
            } else if(givenRequirement.charAt(i)==109 && sessionEnd==true){
                return count;
            } else if(givenRequirement.charAt(i)==99 && sessionEnd==true){
                String[] customPoints = givenRequirement.split("/");
                return Integer.valueOf(customPoints[1]);
            }
        }
        return 0;
    }
}

