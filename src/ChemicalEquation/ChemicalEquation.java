package ChemicalEquation;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("unused")
public class ChemicalEquation {
    public EquationString equation;
    public String[] reactants;
    public String[] products;
    HashMap<String, Integer> reactantCount;
    HashMap<String, Integer> productCount;

    public ChemicalEquation(EquationString equation) throws EquationException{
        this.equation = equation;
        reactants = EquationParser.ParseReactants(equation);
        products = EquationParser.ParseProducts(equation);
        reactantCount = EquationParser.getCount(reactants);
        productCount = EquationParser.getCount(products);

        for (String element: reactantCount.keySet()){
            if (!productCount.containsKey(element)){
                throw new EquationException("Elements must appear on both sides. Element: " + element);
            }
        }

        for (String element: productCount.keySet()){
            if (!reactantCount.containsKey(element)){
                throw new EquationException("Elements must appear on both sides. Element: " + element);
            }
        }
    }


}
