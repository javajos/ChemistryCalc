package ChemicalEquation;
@SuppressWarnings("unused")
public class ChemicalEquation {
    public EquationString equation;
    public String[] reactants;
    public String[] products;

    public ChemicalEquation(EquationString equation) {
        this.equation = equation;
        reactants = EquationParser.ParseReactants(equation);
        products = EquationParser.ParseProducts(equation);
    }


}
