package by.bsuir.realEstateAgency.core.dao.user;

import by.bsuir.realEstateAgency.core.dao.UserDao;
import by.bsuir.realEstateAgency.core.model.Realtor;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration("classpath:context/applicationContext-core.xml")
public class UserDaoImplTest {

    static Logger log = Logger.getLogger(UserDaoImplTest.class.getName());

    @Resource
    private UserDao userDao;

    @Resource
    private SessionFactory sessionFactory;

    @Test
    public void startTest() {
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
        userDao.save(user);

        long var = user.getId();

        Realtor user2 = (Realtor) userDao.get(var);
        assertTrue(user.getSalary().compareTo(user2.getSalary()) == 0);
    }
}
