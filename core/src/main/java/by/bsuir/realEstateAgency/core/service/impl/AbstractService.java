package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.common.CheckUserInterface;
import by.bsuir.realEstateAgency.core.model.Admin;
import by.bsuir.realEstateAgency.core.model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class AbstractService<T> {

    static Logger log = Logger.getLogger(AbstractService.class.getName());

    protected void checkUser(List<Long> keys, User user, CheckUserInterface checkUserInterface){
        if (!(user instanceof Admin)) {
            if (!checkUserInterface.checkUser(keys, user.getId())) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying remove immobility by not the owner or admin", e);
                throw e;
            }
        }
    }
}
