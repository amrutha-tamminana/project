package arbitraryarithmetic;

public class AInteger{
    private String value;
    public AInteger(String value){
        this.value=value;
    }
    public AInteger(){
        this.value="0";
    }
    //convert string to an AInteger object
    public static AInteger parse(String s){
        return new AInteger(s);
    }
    //addition of large integers
    public AInteger add(AInteger next){
        String num1=this.value;
        String num2=next.value;
        if (num2.length()>num1.length()){
            String temp=num1;
            num1=num2;
            num2=temp;
        } 
        //cases where on of the numbers is negative
        boolean num1neg;
        if(num1.charAt(0)=='-'){num1neg=true;}
        else{num1neg=false;}
        boolean num2neg;
        if(num2.charAt(0)=='-'){num2neg=true;}
        else{num2neg=false;}
        if(num1neg=true&&num2neg){
            num1=num1.substring(1);//remove the negative sign
            num2=num2.substring(1);
            AInteger sum=new AInteger(addition(num1,num2));
            return new AInteger("-"+sum.value);
        }
        if(num1neg&&!num2neg){
            num1=num1.substring(1);
            return new AInteger(num2).sub( new AInteger(num1));
        }
        if(!num1neg&&num2neg){
            num2=num2.substring(1);
            return new AInteger(num1).sub( new AInteger(num2));
        }
        return new AInteger(addition(num1,num2));
    }
    //actual addition of two positive integers but made it as a helper to make sure that negative numbers are also added correctly
    //addition is done in manual way so that with loop it gives final answer
    private String addition(String num1,String num2){
        int len1= num1.length();
        int len2=num2.length();
        int carry=0;
        char result[]=new char[len1+1];
        int resultIndex=result.length-1;
        for(int i=0;i<len1;i++){
            int d1=num1.charAt(len1-1-i)-'0';
            int d2;
            if(i<len2){
                d2=num2.charAt(len2-i-1)-'0';
            }
            else{
                d2=0;
            }
            int sum=d1+d2+carry;
            result[resultIndex--]=(char)((sum%10)+'0');
            carry=sum/10;
        }
        if(carry>0){
            result[resultIndex]=(char)(carry+'0');
        }else{
            resultIndex++;
        }
        //construct string from the result array
        return new String(new String(result, resultIndex,result.length-resultIndex));
    }
    //subtraction of large integers
    public AInteger sub(AInteger next){
        String num1=this.value;
        String num2=next.value;
        boolean isneg=false;
        //if 2nd number is greater then swap the numbers
        if(compareStrings(num1,num2)<0){
            String temp=num1;
            num1=num2;
            num2=temp;
            isneg=true;
        }
        int len1=num1.length();
        int len2=num2.length();
        char result[]=new char[len1];
        int borrow=0;
        for(int i=0;i<len1;i++){
            int d1=num1.charAt(len1-i-1)-'0';
            int d2;
            if(i<len2){
                d2=num2.charAt(len2-i-1)-'0';
            }
            else{
                d2=0;
            }
            int diff=d1-d2-borrow;
            if(diff<0){
                diff+=10;
                borrow=1;
            }else{
                borrow=0;
            }
            result[len1-i-1]=(char)(diff+'0');
        }
        //if there are leading zeros then remove them
        int start=0;
        while(start<result.length-1&&result[start]=='0'){
            start++;
        }
        //construct result string
        String resultStr=new String(result,start,result.length-start);
        //if the numbers are swapped before and then subtracted then there should be a negative sign since we are subtracting num2 from num1
        if(isneg) resultStr="-"+resultStr;
        return new AInteger(resultStr);
    }
    //multiplication of large integers
    public AInteger mul(AInteger next){
        String num1=this.value;
        String num2=next.value;
        boolean negative=false;
        //check if either of the numbers are negative since we have to take care of negative sign as well and removing them and then multiply
        if(num1.charAt(0)=='-'&&num2.charAt(0)!='-'){
            negative=true;
            num1=num1.substring(1);
        }else if(num1.charAt(0)!='-' && num2.charAt(0)=='-'){
            negative=true;
            num2=num2.substring(1);
        }else if(num1.charAt(0)=='-'&&num2.charAt(0)=='-'){
            num1=num1.substring(1);
            num2=num2.substring(1);
        }
        int len1=num1.length();
        int len2=num2.length();
        int result[]=new int[len1 + len2];
        //multiply digit by digit in a manual way taking carries   
        for (int i=len1-1;i>=0;i--){
            int d1=num1.charAt(i)-'0';
            for (int j=len2-1;j>=0;j--){
                int d2=num2.charAt(j)-'0';
                int m=d1*d2;
                int p2=i+j+1;
                int sum=m+result[p2];
                result[p2]=sum%10;
                result[p2-1]+=sum/10;
            }
        }
        //now convert the final result obtained to string
        String product="";
        boolean leadingzero=true;
        //also remove all the leading zeros if there since we are taking result array of length len1+len2
        for(int i=0;i<result.length;i++){
            int digit=result[i];
            if(digit==0&&leadingzero){continue;}
            leadingzero=false;
            product+=(char)(digit + '0');
        }
        if(product.equals("")){
            product="0";
        }
        //add  negative sign if one of the numbers is negative
        if(negative&&!product.equals("0")) {
            product="-"+product;
        }
        return new AInteger(product);
    }
    //division of integers returning an integer
    public AInteger div(AInteger next){
        String dividend=this.value;
        String divisor=next.value;
        //if divisor is zero then quotient is undefined
        if(divisor.equals("0")){
            throw new ArithmeticException("Division by zero error");
        }
        if(compareStrings(dividend,divisor)<0){
            return new AInteger("0");
        }
        char quo[]=new char[dividend.length()];
        String remainder="";
        //following long division method
        for(int i=0;i<dividend.length();i++){
            remainder+=dividend.charAt(i);
            //remove leading zeros from remainder
            int start=0;
            while(start<remainder.length()-1&&remainder.charAt(start)=='0'){
                start++;
            }
            remainder=remainder.substring(start);
            int count=0;
            while(compareStrings(remainder,divisor)>=0){
                remainder=subtractStrings(remainder,divisor);
                count++;
            }
            quo[i]=(char)(count+'0');
        }
        //remove leading zeros in quotient
        int qstart=0;
        while(qstart<quo.length-1&&quo[qstart]=='0') {
            qstart++;
        }
        return new AInteger(new String(quo,qstart,quo.length-qstart));
    }
    private int compareStrings(String num1,String num2) {
        if(num1.length()>num2.length())return 1;
        if(num1.length()<num2.length())return -1;
        return num1.compareTo(num2);
    }
    private String subtractStrings(String num1,String num2){
        int len1=num1.length();
        int len2=num2.length();
        char result[]=new char[len1];
        int borrow=0;
        for(int i=0;i<len1;i++){
            int d1=num1.charAt(len1-i-1)-'0';
            int d2;
            if(i<len2){
                d2=num2.charAt(len2-i-1)-'0';
            }
            else{
                d2=0;
            }
            int diff=d1-d2-borrow;
            if(diff<0){
                diff+=10;
                borrow=1;
            }else{
                borrow=0;
            }
            result[len1-i-1]=(char) (diff+'0');
        }
        int start=0;
        while(start<result.length-1&&result[start]=='0'){
            start++;
        }
        return new String(result,start, result.length-start);
    }
    public String getValue(){
        return this.value;
    }
    public String toString(){
        return this.value;
    } 
}

