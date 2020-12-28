import com.card.FakaApplication;
import com.card.service.RoleService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = FakaApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private RoleService roleService;

    @org.junit.Test
    public void test() {
        System.out.println(roleService.selectRoles(1L, true));
    }
}
