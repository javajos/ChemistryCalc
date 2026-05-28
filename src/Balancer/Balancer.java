package Balancer;

import ChemicalEquation.ChemicalEquation;
import ChemicalEquation.EquationParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
            if (!(Objects.equals(reactantCount.get(element), productCount.get(element)))) {
                return false;
            }
        }
        return true;
    }

    public Object balance() {
        if (isBalanced()){
            return equation;
        }
        HashMap<String, HashMap<Character, Integer>> alg = assignAlgebra();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for (String s:alg.keySet()){
            matrix.add(new ArrayList<>(alg.get(s).values()));
        }
        for (ArrayList<Integer> l:matrix){
            l.set(l.size() - 1,-l.getLast());
        }
        return matrix;
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

    public HashMap<String, HashMap<Character, Integer>> assignAlgebra() {
        // This will store: Element -> (Variable -> Coefficient)
        HashMap<String, HashMap<Character, Integer>> elementEquations = new HashMap<>();

        HashMap<Character, String> variables = assignVariables();
        HashMap<Character, HashMap<String, Integer>> algCounts = getAlgCounts();

        // We need to know where the reactants end so we can make products negative.
        // Since 'A' starts at reactants, the last reactant character is:
        char lastReactantChar = (char) ('A' + equation.reactants.length - 1);

        // 1. Loop through our Variable-based map
        for (HashMap.Entry<Character, HashMap<String, Integer>> entry : algCounts.entrySet()) {
            char var = entry.getKey();
            HashMap<String, Integer> atomCounts = entry.getValue();

            // If it's past the last reactant char, it's a product!
            boolean isReactant = (var <= lastReactantChar);

            // 2. Loop through each element inside this compound
            for (HashMap.Entry<String, Integer> atomEntry : atomCounts.entrySet()) {
                String element = atomEntry.getKey();
                int count = atomEntry.getValue();

                // Reactants are positive, Products are negative (e.g., 2A - 2C = 0)
                int coefficient = isReactant ? count : -count;

                // 3. Pivot the data: Initialize the inner map for the element if it doesn't exist
                elementEquations.putIfAbsent(element, new HashMap<>());

                // Assign the coefficient to this variable under this element
                elementEquations.get(element).put(var, coefficient);
            }
        }

        // 4. Fill in the gaps with zeros
        // If Element 'O' isn't in variable 'A', we want 'A' to map to 0, not be missing.
        for (String element : elementEquations.keySet()) {
            for (Character var : variables.keySet()) {
                elementEquations.get(element).putIfAbsent(var, 0);
            }
        }

        return elementEquations;
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