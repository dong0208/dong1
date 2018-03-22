import com.dong.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.Reader;

public class UserMapperTestCase {
    @Test
    public void testFindById()throws Exception{
        //加载配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //创建sqlSessionFactory
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //操作数据库
        User user = sqlSession.selectOne("com.dong.mapper.UserMapper.findById",2);
        System.out.println(user);
        sqlSession.close();
    }
}
