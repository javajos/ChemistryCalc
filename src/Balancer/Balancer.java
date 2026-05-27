package Balancer;

import ChemicalEquation.ChemicalEquation;
import ChemicalEquation.EquationParser;

import java.util.HashMap;

@SuppressWarnings("unused")
public class Balancer {
    ChemicalEquation equation;
    HashMap<String, Integer> reactantCount;
    HashMap<String, Integer> productCount;


    public Balancer(ChemicalEquation equation) {
        this.equation = equation;
        reactantCount = EquationParser.getCount(equation.reactants);
        productCount = EquationParser.getCount(equation.products);
    }

    public boolean isBalanced() {
        for (String element : reactantCount.keySet()) {
            if (!(reactantCount.get(element) == productCount.get(element))) {
                return false;
            }
        }
        return true;
    }

    public Object balance() {
        if (isBalanced()){
            return equation;
        }
        HashMap<Character, String> letters = assignVariables();
        return null;
    }


    public HashMap<Character,String> assignVariables() {
        HashMap<Character, String> variables = new HashMap<>();
        char i = (int) 'A';
        for (String compound : equation.reactants) {
            variables.put(i, compound);
            i++;
        }
        for (String compound : equation.products) {
            variables.put(i, compound);
            i++;
        }
        return variables;
    }

    private HashMap<String, HashMap<String,String>> assignAlgebra(){
        return null;
    }

    public HashMap<Character, HashMap<String, Integer>> getAlgCounts(){
        HashMap<Character, String> variables = assignVariables();
        HashMap<Character,HashMap<String,Integer>> elementCounts = new HashMap<>();
        for (HashMap.Entry<Character,String> entry:variables.entrySet()){
            HashMap<String, Integer> parsed = EquationParser.getCount(new String[]{entry.getValue()});
            elementCounts.put(entry.getKey(),parsed);
        }
        return elementCounts;
    }

}