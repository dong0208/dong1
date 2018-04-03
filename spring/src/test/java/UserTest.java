import com.dong.dao.StudentDao;
import com.dong.dao.UserDao;
import com.dong.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void saveTest(){
        //获取spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器获取userDao对象
        UserDao userDao = (UserDao)applicationContext.getBean("userDao");
        userDao.save();
    }
    @Test
    public void findTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService =(UserService)applicationContext.getBean("userService");
        userService.save();
    }
    @Test
    public void showTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentDao studentDao = (StudentDao) applicationContext.getBean("studentDao");
        studentDao.show();


    }
}
