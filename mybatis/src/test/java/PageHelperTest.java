import com.github.pagehelper.PageInfo;
import com.xy.mybatis.MybatisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xy.mybatis.pojo.TbCard;
import com.xy.mybatis.service.MybatisService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class PageHelperTest {
    @Autowired
    private MybatisService mybatisService;

    @Test
    public void testPageHelper(){

        PageInfo<TbCard> tbCardPageInfo = mybatisService.selectCardByPage(1, 10);
        System.out.println(tbCardPageInfo);
    }

}
