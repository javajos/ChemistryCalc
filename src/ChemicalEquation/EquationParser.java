package ChemicalEquation;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class EquationParser {

    public static String[] ParseReactants(EquationString equation) {
        String reactantSide = equation
                .getEquation()
                .strip()
                .split(">")[0];
        String[] reactants = reactantSide.split("\\+");
        String[] result = new String[reactants.length];
        for (int i = 0; i < reactants.length; i++) {
            result[i] = reactants[i].strip();
        }
        return result;
    }

    public static String[] ParseProducts(EquationString equation) {
        String productSide = equation
                .getEquation()
                .strip()
                .split(">")[1];
        String[] products = productSide.split("\\+");
        String[] result = new String[products.length];
        for (int i = 0; i < products.length; i++) {
            result[i] = products[i].strip();
        }
        return result;
    }

    public static HashMap<String, Integer> getCount(String[] parsedEquationHalf) {
        HashMap<String, Integer> reactantCount = new HashMap<>();
        int[] Coefficients = new int[parsedEquationHalf.length];
        String coeff;
        for (int i = 0; i < parsedEquationHalf.length; i++) {
            coeff = parsedEquationHalf[i].split("[A-Z]")[0];

            if (coeff.isEmpty()) {
                Coefficients[i] = 1;
            } else {
                Coefficients[i] = Integer.parseInt(coeff);
            }

            String formula = parsedEquationHalf[i].substring(coeff.length());
            Pattern countPairPattern = Pattern.compile("([A-Z][a-z]*)(\\d*)");
            Matcher findPairPattern = countPairPattern.matcher(formula);

            while (findPairPattern.find()) {
                String element = findPairPattern.group(1);
                String countString = findPairPattern.group(2);

                int subscript = countString.isEmpty() ? 1 : Integer.parseInt(countString);

                int totalCount = Coefficients[i] * subscript;

                reactantCount.put(element, reactantCount.getOrDefault(element, 0) + totalCount);
            }
        }
        return reactantCount;

    }

}
