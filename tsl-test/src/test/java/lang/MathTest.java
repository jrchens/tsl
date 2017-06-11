package lang;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.Format;

/**
 * Created by chensheng on 17/6/10.
 */
public class MathTest {
    @Test
    public void power(){
        DecimalFormat format = new DecimalFormat("#0.##");
        double number = Math.pow(2,0);
        int max = Integer.MAX_VALUE;
        System.out
                .println(String.format("%s,\r\n%s",format.format(number),format.format(max)));
    }
}
