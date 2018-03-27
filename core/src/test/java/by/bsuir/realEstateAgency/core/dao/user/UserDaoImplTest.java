package by.bsuir.realEstateAgency.core.dao.user;

import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.model.TypeUser;
import by.bsuir.realEstateAgency.core.model.User;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration("classpath:context/applicationContext-core.xml")
public class UserDaoImplTest {

    static Logger log = Logger.getLogger(UserDaoImplTest.class.getName());

    @Resource
    private UserDao userDao;

    @Test
    public void startTest(){
        URL url = UserDaoImplTest.class.getClassLoader().getResource("log4j.properties");
        log.debug("LLLLLOOOOOOOOOLLLLLL");
        Realtor user = new Realtor();
        user.setLogin("admin");
        user.setPassword("password");
        user.setDateOfBirth(new Date());
        user.setEmail("admin@mail.ru");
        user.setFirstName("Dmitry");
        user.setLastName("Koshelev");
        user.setPatronymic("Michailovich");
        user.setEmploymentDate(new Date());
        user.setSalary(new BigDecimal(500));
        userDao.add(user);

        Realtor user2 = (Realtor)userDao.get(user.getId());
        assertEquals(user.getTypeUser(),user2.getTypeUser());
        assertTrue(user.getSalary().compareTo(user2.getSalary()) == 0);
    }
}
