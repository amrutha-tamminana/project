package arbitraryarithmetic;

public class AFloat{
    private String value;
    public AFloat(){
        this.value="0.0";
    }
    public AFloat(String value){
        this.value=normalize(value);
    }
    public AFloat(AFloat next){
        this.value=next.value;
    }
    //convert string to an AFloat object
    public static AFloat parse(String s){
        return new AFloat(s);
    }
    //addition for large float numbers
    public AFloat add(AFloat next){
        //each float number contains two parts i.e integerpart and decimal part. separate them and make them an integer with no decimal points and it should be comfortable for us to add using AIntegers
        String part1[]=split(this.value);
        String part2[]=split(next.value);
        int maxdeciplaces=Math.max(part1[1].length(),part2[1].length());
        String intpar1=part1[0]+addright(part1[1],maxdeciplaces);
        String intpar2=part2[0]+addright(part2[1],maxdeciplaces);
        AInteger a1=new AInteger(intpar1);
        AInteger a2=new AInteger(intpar2);
        AInteger sum=a1.add(a2);
        String sumstr=sum.getValue();
        //checking if the sum is very small as we need to add some zeros and then insert decimal
        if(sumstr.length()<=maxdeciplaces){
            sumstr="0".repeat(maxdeciplaces-sumstr.length()+1)+sumstr;
        }
        String result=insertdeci(sumstr,maxdeciplaces);
        return new AFloat(result);
    }
    //subtraction of large float numbers
    //same as addition of large float numbers the only difference is subtract AIntegers
    public AFloat sub(AFloat next) {
        String part1[]=split(this.value);
        String part2[]=split(next.value);
        int maxdeciplaces=Math.max(part1[1].length(),part2[1].length());
        String intpar1=part1[0]+addright(part1[1],maxdeciplaces);
        String intpar2=part2[0]+addright(part2[1],maxdeciplaces);
        AInteger a1=new AInteger(intpar1);
        AInteger a2=new AInteger(intpar2);
        AInteger diff=a1.sub(a2);
        String diffstr=diff.getValue();
        if(diffstr.length()<=maxdeciplaces){
            diffstr="0".repeat(maxdeciplaces-diffstr.length()+1)+diffstr;
        }
        String result=insertdeci(diffstr,maxdeciplaces);
        return new AFloat(result);
    }
    //multiplication of large float numbers
    public AFloat mul(AFloat next) {
        String part1[]=split(this.value);
        String part2[]=split(next.value);
        int deciplaces=part1[1].length()+part2[1].length();
        String intpar1=part1[0]+part1[1];
        String intpar2=part2[0]+part2[1];
        AInteger a1=new AInteger(intpar1);
        AInteger a2=new AInteger(intpar2);
        AInteger product=a1.mul(a2);
        String prostr=product.getValue();
        if(prostr.length()<=deciplaces){
            prostr="0".repeat(deciplaces-prostr.length()+1)+prostr;
        }
        String result=insertdeci(prostr,deciplaces);
        return new AFloat(result);
    }
    //division of large float numbers and precision upto 1000 digits after decimal point
    public AFloat div(AFloat next) {
        String part1[]=split(this.value);
        String part2[]=split(next.value);
        //if the divisor is zero
        if(next.getValue().equals("0")||next.getValue().equals("0.0")){
            throw new ArithmeticException("Division by zero error");
            
        }
        //we need precision upto 30 digits after the decimal point
        int scale=30;
        int leftscale=scale+part2[1].length();
        String num1=part1[0]+part1[1]+"0".repeat(leftscale-part1[1].length());
        String num2=part2[0]+part2[1];
        AInteger num=new AInteger(num1);
        AInteger den=new AInteger(num2);
        AInteger quo=num.div(den);
        String qstr=quo.getValue();
        if(qstr.length()<=scale) {
            qstr="0".repeat(scale-qstr.length()+1)+qstr;
        }
        String result=insertdeci(qstr,scale);
        return new AFloat(result);
    }
    //make sure that your output is clear i.e remove all the unnecessary zeros and if the output is a perfect integer add one zero after the decimal
    private String normalize(String s) {
        if(!s.contains("."))return s+".0";
        String parts[]=s.split("\\.");
        String intpart=rmleadzeros(parts[0]);
        String fracpart=rmlastzeros(parts[1]);
        if(intpart.isEmpty()){
            intpart="0";
        }
        if(fracpart.isEmpty()){
            fracpart="0";
        }
        return intpart+"."+fracpart;
    }
    private String rmleadzeros(String s){
        int i=0;
        boolean negative=s.startsWith("-");
        if(negative){i++;}
        while(i<s.length()-1&&s.charAt(i)=='0'){i++;}
        String result;
        if(negative){
            result="-"+s.substring(i);
        }
        else{
            result=s.substring(i);
        }
        return result;
    }
    private String rmlastzeros(String s){
        int i=s.length()-1;
        while(i>0&&s.charAt(i)=='0'){i--;}
        return s.substring(0,i+1);
    }
    //add zeros in the right until the length of the string becomes len
    private String addright(String s,int len) {
        while(s.length()<len)s+="0";
        return s;
    }
    //since we have splitted the floating numbers into two parts and made it a big integer and then performed operations we need to insert decimal at the required place after doind subsequent operations
    private String insertdeci(String s,int index) {
        if(index>=s.length()){
            s="0".repeat(index-s.length()+1)+s;
        }
        int pos=s.length()-index;
        return s.substring(0,pos)+"."+s.substring(pos);
    }
    private String[] split(String s) {
        if(s.startsWith("-")){
            String parts[]=split(s.substring(1));
            parts[0]="-"+parts[0];
            return parts;
        }
        String parts[]=s.split("\\.");
        if(parts.length==1){return new String[]{parts[0],"0"};}
        return new String[]{parts[0],parts[1]};
    }
    public String getValue(){
        return this.value;
    }
    public String toString(){
        return this.value;
    }
}

