package ChemicalEquation;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class EquationString{
    public String equation;

    private EquationString(String equation){
        this.equation = equation;
    }

    /**
     * Takes a chemical equation as a string and turns it into an {@link EquationString}
     * @param equation A string formatted like in the following: "SO2 + H2O > H2SO3"
     * @return returns an {@link EquationString} equivalent of the equation
     * @throws EquationException if the equation contains an illegal character
     */

   public static EquationString fromString(String equation) throws EquationException{
       if (equation == null || equation.isBlank()) {
           throw new EquationException("Equation can not be blank or null");
       }

       final Pattern ILLEGAL_CHARACTERS = Pattern.compile("[^A-Za-z0-9+>() ]");
       Matcher matcher = ILLEGAL_CHARACTERS.matcher(equation);

       if (matcher.find()){
           throw new EquationException("Equation contains one or more illegal Characters");
       }
       else return new EquationString(equation);
   }

   public String getEquation(){
       return equation;
   }

   public static String getEquation(EquationString equationString) {
       return equationString.getEquation();
   }
}
