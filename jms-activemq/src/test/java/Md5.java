import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Md5 {
    @Test
    public void passMd5(){
        String a = "QWSXZA$%$%((^%^FDEW))123";
        String i = "123123";
        System.out.println(DigestUtils.md5Hex(i+a));
    }


}
