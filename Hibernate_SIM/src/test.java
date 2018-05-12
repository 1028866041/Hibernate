
import com.myself.model.Service;
import com.myself.model.User;
import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class test {

    @Test
    public void test() throws  Exception{

        User user = new User();
        user.setId(1);
        user.setName("zhangs");
        user.setAge(20);

        Service svc = new Service();
        svc.createSql();
        svc.save(user);

    }
}
