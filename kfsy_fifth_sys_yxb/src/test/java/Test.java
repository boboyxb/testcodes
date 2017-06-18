import com.baizhi.fifth.dao.ProductDAO;
import com.baizhi.fifth.entity.Product;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class Test {
    @Autowired
    private ProductDAO productDAO;
    @org.junit.Test
    public void test(){
        List<Product> products = productDAO.queryAll();
        System.out.println(products);
    }
}
