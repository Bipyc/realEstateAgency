package by.bsuir.realEstateAgency.core.service.common;

import java.util.List;

public interface FindAllInterface {
    List findAll(int offset, int limit);

    long count();
}
