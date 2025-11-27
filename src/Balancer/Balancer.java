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
        HashMap<String, Character> letters = assignVariables();
        return null;
    }


    private HashMap<String, Character> assignVariables() {
        HashMap<String, Character> variables = new HashMap<>();
        char i = (int) 'A';
        for (String compound : equation.products) {
            variables.put(compound, i);
            i++;
        }
        for (String compound : equation.reactants) {
            variables.put(compound, i);
            i++;
        }
        return variables;
    }

    private HashMap<String, HashMap<String,String>> assignAlgebra(){
        return null;
    }


}