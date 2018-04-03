import com.dong.pro.Sales;
import com.dong.pro.SalesProty;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SalesTest {
    @Test
    public void tsst(){
        SalesProty salesProty = new SalesProty();
        salesProty.Sales();
    }
@Test
    public void testcglib(){
        Enhancer enhancer = new Enhancer();
        //设置目标对象
        enhancer.setSuperclass(Sales.class);
        //设置代理对象的代理行为
        enhancer.setCallback(new MethodInterceptor() {
            /**
             *添加代理行为
             * @param target 目标对象
             * @param method  目标的方法一般不用
             * @param args  方法的参数
             * @param methodProxy 产生的子类方法的代理
             * @return
             * @throws Throwable
             */

            @Override
            public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                try{
                    System.out.println("-------");
                    Object result = methodProxy.invokeSuper(target,args);
                    System.out.println("+++++++");
                    return result;
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("******");
                    return null;
                }finally{
                    System.out.println("%%%%%%%");
                }
            }
        });
        //产生代理对象
        Sales sales = (Sales) enhancer.create();
        sales.Sales();

    }
}
