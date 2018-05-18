package by.bsuir.realEstateAgency.core.dao.common;

import java.util.List;

public interface CheckUserInterface {
    boolean checkUser(List<Long> keys, Long userId);
}
