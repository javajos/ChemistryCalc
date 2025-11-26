package BalancingMath;

@SuppressWarnings("unused")
public class BalancingMath {

  public static RemainderDivisionOut eDivL(int dividend, int divisor){
    return new RemainderDivisionOut(dividend / divisor, dividend % divisor);
  }

  public static int gcd(int numOne, int numTwo){
    while (numTwo != 0){
      int storage = numTwo;
      numTwo = numOne % numTwo;
      numOne = storage;
    }
    return numTwo;
  }

  public static int lcm(int numOne, int numTwo){
    return (numOne * numTwo) / gcd(numOne, numTwo);
  }

  public static int lcm(int[] numbers) throws BMathException{
    if (!(numbers.length > 1)){
      throw new BMathException("must have at least two numbers to do this");
    }
    int storage = lcm(numbers[0],numbers[1]);
    for (int i = 2; i < numbers.length; i++){
      storage = lcm(storage, numbers[i]);
    }

    return storage;
  }
}
