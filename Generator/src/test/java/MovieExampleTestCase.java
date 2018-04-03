import com.dong.entity.Movie;
import com.dong.entity.MovieExample;
import com.dong.mapper.MovieMapper;
import com.dong.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MovieExampleTestCase {
    private SqlSession sqlSession;
    private MovieMapper movieMapper;
    @Before
    public void init(){
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }
    @After
    public void destroy(){
        sqlSession.close();
    }
    @Test
    public void insert(){
        Movie movie = new Movie();
        movie.setTitle("星际穿越");
        movie.setDirector("詹姆斯级");
        movie.setRate(9.0F);
        int num = movieMapper.insertSelective(movie);
        System.out.println("受影响行数：" + num);
    }
    @Test
    public void findById(){
        Movie movie = movieMapper.selectByPrimaryKey(777);
        System.out.println(movie+"在哪里");
    }
    @Test
    public void findAll(){
        MovieExample movieExample = new MovieExample();
        List<Movie> movieList = movieMapper.selectByExample(movieExample);
        for (Movie movie : movieList){
            System.out.println(movie);
        }
    }


}
