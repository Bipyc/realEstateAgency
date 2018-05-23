package by.bsuir.realEstateAgency.web.service.page;

import by.bsuir.realEstateAgency.core.bean.SearchForm;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.common.FindAllBySearchInterface;
import by.bsuir.realEstateAgency.core.service.common.FindAllByUserService;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;
import by.bsuir.realEstateAgency.web.bean.pagedList.PagedListPage;
import by.bsuir.realEstateAgency.web.bean.pagedList.Pagination;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {

    private static final int FIRST_PAGE_NUMBER = 1;

    private static final int AROUND_PAGES_COUNT = 3;

    @Override
    public Pagination getPagination(int pageNumber, int countOnPage, int itemsCount) {
        int normalizedPageNumber = normalizePageNumber(pageNumber, countOnPage, itemsCount);
        int pagesCount = getPagesCount(itemsCount, countOnPage);

        int startPaginationNumber = Math.max(normalizedPageNumber - AROUND_PAGES_COUNT, FIRST_PAGE_NUMBER);
        int finishPaginationNumber = Math.min(normalizedPageNumber + AROUND_PAGES_COUNT, pagesCount);

        return new Pagination(normalizedPageNumber, startPaginationNumber, finishPaginationNumber, countOnPage);
    }

    @Override
    public PagedListPage getPagedList(int pageNumber, int countOnPage, FindAllBySearchInterface objectService, SearchForm searchForm) {
        int itemsCount = (int) objectService.countBySearch(searchForm);
        int normalizedPageNumber = normalizePageNumber(pageNumber, countOnPage, itemsCount);
        int offset = ((normalizedPageNumber - 1) * countOnPage);
        int limit = countOnPage;

        return new PagedListPage(objectService.findAllBySearch(offset, limit,searchForm), getPagination(normalizedPageNumber, countOnPage, itemsCount));
    }

    @Override
    public PagedListPage getPagedList(int pageNumber, int countOnPage, FindAllInterface objectService) {
        int itemsCount = (int) objectService.count();
        int normalizedPageNumber = normalizePageNumber(pageNumber, countOnPage, itemsCount);
        int offset = ((normalizedPageNumber - 1) * countOnPage);
        int limit = countOnPage;

        return new PagedListPage(objectService.findAll(offset, limit), getPagination(normalizedPageNumber, countOnPage, itemsCount));
    }

    @Override
    public PagedListPage getPagedList(int pageNumber, int countOnPage, FindAllByUserService objectService, User user) {
        int itemsCount = (int) objectService.countByUser(user);
        int normalizedPageNumber = normalizePageNumber(pageNumber, countOnPage, itemsCount);
        int offset = ((normalizedPageNumber - 1) * countOnPage);
        int limit = countOnPage;

        return new PagedListPage(objectService.findAllByUser(offset, limit, user), getPagination(normalizedPageNumber, countOnPage, itemsCount));
    }

    @Override
    public int normalizePageNumber(int pageNumber, int countOnPage, int itemsCount) {
        int normalizedPageNumber = Math.max(pageNumber, FIRST_PAGE_NUMBER);
        int lastPageNumber = getPagesCount(itemsCount, countOnPage);
        normalizedPageNumber = Math.min(normalizedPageNumber, lastPageNumber);
        return normalizedPageNumber;
    }

    @Override
    public int getPagesCount(int itemsCount,int countOnPage) {
        if (itemsCount == 0) {
            return 1;
        }
        countOnPage = Math.max(countOnPage,1);
        return (int) Math.ceil((double) itemsCount / countOnPage);
    }
}

