import org.junit.Test;

/**
 * Created by admin on 2017/8/11.
 */
public class parseStringToInt {
    @Test
    public void Test() {
        System.out.println(parseInt("A12"));
        System.out.println(parseInt("12"));
        System.out.println(parseInt("-12"));
        System.out.println(parseInt("ABC"));
        System.out.println(parseInt("124567890123"));
    }

    public int parseInt(String s){
        //是否是空
        if(s == null||s.equals("")){
            throw new NumberFormatException();
        }
        int result = 0;
        //符号位是'-'
        if(s.charAt(0)=='-'){
            if(judgeFormat(s,1)){
                result =  -pasre(s,1);
            }
        }
        //符号位大于0，小于9
        else if(s.charAt(0)>=0&&s.charAt(0)<=9){
            if(judgeFormat(s,0)){
                result =  pasre(s,0);
            }
        }else {
            throw new NumberFormatException();
        }

        return result;
    }
    public boolean judgeFormat(String s,int begin){
        boolean flag = true;
        for (int i = begin; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                flag = false;
            }
        }
        if(flag == false){
            throw new NumberFormatException();
        }
        return flag;
    }


    public int pasre(String s,int begin) {
        int sum = 0;
        for (int i = begin; i < s.length(); i++) {
            int temp = s.charAt(i) - '0';
            if(sum>(Integer.MAX_VALUE-temp)/10){
                throw new NumberFormatException();
            }else {
                sum = sum * 10;
                sum += temp;
            }
        }
        return sum;
    }
}
