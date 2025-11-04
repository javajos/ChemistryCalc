package ChemicalEquation;
@SuppressWarnings("unused")
public class EquationParser {

    public static String[] ParseReactants(EquationString equation) {
        String reactantSide = equation
                .getEquation()
                .strip()
                .split(">")[0];
        return reactantSide.split("\\+");
    }

    public static String[] ParseProducts(EquationString equation) {
        String productSide = equation
                .getEquation()
                .strip()
                .split(">")[1];
        return productSide.split("\\+");
    }

}
