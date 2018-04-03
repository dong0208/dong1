import com.dong.entity.Movie;
import com.dong.mapper.MovieMapper;
import com.dong.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieMapperTestCase {
    private SqlSession sqlSession;
    private MovieMapper movieMapper;
    @Before
    public void init(){
        //自动提交事物
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //动态代理 动态生成
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }
    @After
    public void destroy(){
        //关闭sqlsession
        sqlSession.close();
    }
    @Test
    public void testFindList() {
        String title = "%大话西游%";
        List<Movie> movieList = movieMapper.findList(null);
                for(Movie movie : movieList) {
                        System.out.println(movie);
                   }
            }
   @Test
    public void testFindByParmas(){
        String title = "";
        String director = "%詹姆斯·卡梅隆%";
        Map<String,Object> parmas = new HashMap<>();
        parmas.put("title",title);
        parmas.put("director",director);

       List<Movie> movieList = movieMapper.findByParams( parmas);
            for (Movie movie : movieList){
                System.out.println(movie);
            }
    }
}
