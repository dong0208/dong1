import com.dong.pro.Player;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
        Player mp3 = (Player) applicationContext.getBean("mp3");

        mp3.play("凉城");
        System.out.println("-------");
        mp3.stop();
    }
}
