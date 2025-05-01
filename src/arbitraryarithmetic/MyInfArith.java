package arbitraryarithmetic;
public class MyInfArith {
    public static void main(String arg[]){
        String type=arg[0];
        String operation=arg[1];
        String n1=arg[2];
        String n2=arg[3];
        //use switch cases for both int type and flost type 
        if(type.equals("int")){
            AInteger num1=new AInteger(n1);
            AInteger num2=new AInteger(n2);
            AInteger ans=null;
            switch(operation){
                case "add":
                    ans=num1.add(num2);
                    break;
                case "sub":
                    ans=num1.sub(num2);
                    break;
                case "mul":
                    ans=num1.mul(num2);
                    break;
                case "div":
                    ans=num1.div(num2);
                    break;
                default:
                    System.out.println("Invalid input");
            }System.out.println(ans.getValue());
        }
        else if(type.equals("float")){
            AFloat num1=new AFloat(n1);
            AFloat num2=new AFloat(n2);
            AFloat ans=null;
            switch(operation){
                case "add":
                    ans=num1.add(num2);
                    break;
                case "sub":
                    ans=num1.sub(num2);
                    break;
                case "mul":
                    ans=num1.mul(num2);
                    break;
                case "div":
                    ans=num1.div(num2);
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println(ans.getValue());
        }
        else{
            System.out.println("Invalid input for type");
        }
    }
}
