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
    public Pagination getPagination(int pageNumber, int itemsCount) {
        int normalizedPageNumber = normalizePageNumber(pageNumber, itemsCount);
        int pagesCount = getPagesCount(itemsCount);

        int startPaginationNumber = Math.max(normalizedPageNumber - AROUND_PAGES_COUNT, FIRST_PAGE_NUMBER);
        int finishPaginationNumber = Math.min(normalizedPageNumber + AROUND_PAGES_COUNT, pagesCount);

        return new Pagination(normalizedPageNumber, startPaginationNumber, finishPaginationNumber);
    }

    @Override
    public PagedListPage getPagedList(int pageNumber, FindAllBySearchInterface objectService, SearchForm searchForm) {
        int itemsCount = (int) objectService.countBySearch(searchForm);
        int normalizedPageNumber = normalizePageNumber(pageNumber, itemsCount);
        int offset = ((normalizedPageNumber - 1) * AMOUNT_PHONES_ON_PAGE);
        int limit = AMOUNT_PHONES_ON_PAGE;

        return new PagedListPage(objectService.findAllBySearch(offset, limit,searchForm), getPagination(normalizedPageNumber, itemsCount));
    }

    @Override
    public PagedListPage getPagedList(int pageNumber, FindAllInterface objectService) {
        int itemsCount = (int) objectService.count();
        int normalizedPageNumber = normalizePageNumber(pageNumber, itemsCount);
        int offset = ((normalizedPageNumber - 1) * AMOUNT_PHONES_ON_PAGE);
        int limit = AMOUNT_PHONES_ON_PAGE;

        return new PagedListPage(objectService.findAll(offset, limit), getPagination(normalizedPageNumber, itemsCount));
    }

    @Override
    public PagedListPage getPagedList(int pageNumber, FindAllByUserService objectService, User user) {
        int itemsCount = (int) objectService.countByUser(user);
        int normalizedPageNumber = normalizePageNumber(pageNumber, itemsCount);
        int offset = ((normalizedPageNumber - 1) * AMOUNT_PHONES_ON_PAGE);
        int limit = AMOUNT_PHONES_ON_PAGE;

        return new PagedListPage(objectService.findAllByUser(offset, limit, user), getPagination(normalizedPageNumber, itemsCount));
    }

    @Override
    public int normalizePageNumber(int pageNumber, int itemsCount) {
        int normalizedPageNumber = Math.max(pageNumber, FIRST_PAGE_NUMBER);
        int lastPageNumber = getPagesCount(itemsCount);
        normalizedPageNumber = Math.min(normalizedPageNumber, lastPageNumber);
        return normalizedPageNumber;
    }

    @Override
    public int getPagesCount(int itemsCount) {
        if (itemsCount == 0) {
            return 1;
        }
        return (int) Math.ceil((double) itemsCount / AMOUNT_PHONES_ON_PAGE);
    }
}

