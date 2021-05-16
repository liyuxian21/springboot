package cn.com.countrygarden;

import com.user.entity.User;
import com.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
public class UniyTest {
     @Autowired UserService userService;
        @Test
        public void test(){
         userService.updateNew();
        }

}
