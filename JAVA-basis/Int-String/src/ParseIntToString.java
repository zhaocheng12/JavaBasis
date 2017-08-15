import org.junit.Test;

/**
 * Created by admin on 2017/8/13.
 */
public class ParseIntToString {

    final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };

    //final static char [] intToChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    // Requires positive x

    @Test
    public void Test(){
        System.out.println(parseIntToString(5468978));
        System.out.println(parseIntToString(-4587));
    }

    public String judge(int num){
        if(num<0){
            String s = parseIntToString(-num);
            StringBuffer result = new StringBuffer("-");
            return result.append(s).toString();
        }else{
            return parseIntToString(num);
        }
    }

    static int stringSize(int x) {
        for (int i=0; ; i++)
            if (x <= sizeTable[i])
                return i+1;
    }

    public String parseIntToString(int num){
        int a = 10;
        int b = 1;
        if(num>=0){
            int size = stringSize(num);
            char[] buf = new char[size];

            for (int i = size-1; i >=0; i--) {
                buf[i] = (char)(num/b%a+'0');
                b*=10;
            }
            return new String(buf);
        }else {
            num = -num;
            int size = stringSize(num)+1;
            char[] buf = new char[size];
            for (int i = size-1; i >0; i--) {
                buf[i] = (char)(num/b%a+'0');
                b*=10;
            }
            buf[0]='-';
            return new String(buf);
        }
        //System.out.println(s.toString());
        //System.out.println(s.getClass().getName());
    }
}
