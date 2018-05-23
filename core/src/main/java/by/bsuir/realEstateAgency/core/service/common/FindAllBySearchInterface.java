package by.bsuir.realEstateAgency.core.service.common;

import by.bsuir.realEstateAgency.core.bean.SearchForm;

import java.util.List;

public interface FindAllBySearchInterface<T> {

    List<T> findAllBySearch(int offset, int limit, SearchForm searchForm);

    long countBySearch(SearchForm searchForm);
}
