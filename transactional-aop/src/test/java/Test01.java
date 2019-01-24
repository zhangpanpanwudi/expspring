import com.wonders.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zph
 * @data: 2018/12/09 14:57
 */
public class Test01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.add();
    }
}
