import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;

class Test{
    public static void main(String args[]){
        // Integer example
        AInteger num1 = new AInteger("123456789012345678901234567890");
        AInteger num2 = new AInteger("987654321098765432109876543210");

        AInteger sum = num1.add(num2);
        AInteger diff = num1.sub(num2);
        AInteger prod = num1.mul(num2);
        AInteger quot = num2.div(num1); // assuming non-zero division

        System.out.println("AInteger Operations:");
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + prod);
        System.out.println("Quotient: " + quot);

        // Float example
        AFloat f1 = new AFloat("12345.678901234567890123456789");
        AFloat f2 = new AFloat("1.0");

        AFloat fsum = f1.add(f2);
        AFloat fdiff = f1.sub(f2);
        AFloat fprod = f1.mul(f2);
        AFloat fquot = f1.div(f2);

        System.out.println("\nAFloat Operations:");
        System.out.println("Sum: " + fsum);
        System.out.println("Difference: " + fdiff);
        System.out.println("Product: " + fprod);
        System.out.println("Quotient: " + fquot);
    }
}