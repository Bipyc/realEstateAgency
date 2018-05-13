package by.bsuir.realEstateAgency.web.bean.pagedList;

import java.util.List;

public class PagedListPage {

    private List list;

    private Pagination pagination;

    public PagedListPage() {
    }

    public PagedListPage(List list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
