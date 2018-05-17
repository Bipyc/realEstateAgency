package by.bsuir.realEstateAgency.core.service.common;

import java.util.List;

public interface FindAllInterface<T> {
    List<T> findAll(int offset, int limit);

    long count();
}
