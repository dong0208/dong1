import com.dong.entity.User;
import com.dong.mapper.UserMapper;
import com.dong.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserMapperTestCase {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init(){
        //自动提交事物
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //动态代理 动态生成
        userMapper = sqlSession.getMapper(UserMapper.class);
    }
    @After
    public void destroy(){
        //关闭sqlsession
        sqlSession.close();
    }
    @Test
    public void testFindById()throws Exception{

        User user = userMapper.findById(2);
        System.out.println(user);



    }
}
