package by.bsuir.realEstateAgency.web.bean.pagedList;

public class Pagination {

    private int pageNumber;

    private int startPaginationNumber;

    private int finishPaginationNumber;

    private int countOnPage;

    public Pagination() {
    }

    public Pagination(int pageNumber, int startPaginationNumber, int finishPaginationNumber, int countOnPage) {
        this.pageNumber = pageNumber;
        this.startPaginationNumber = startPaginationNumber;
        this.finishPaginationNumber = finishPaginationNumber;
        this.countOnPage=countOnPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getStartPaginationNumber() {
        return startPaginationNumber;
    }

    public void setStartPaginationNumber(int startPaginationNumber) {
        this.startPaginationNumber = startPaginationNumber;
    }

    public int getFinishPaginationNumber() {
        return finishPaginationNumber;
    }

    public void setFinishPaginationNumber(int finishPaginationNumber) {
        this.finishPaginationNumber = finishPaginationNumber;
    }

    public int getCountOnPage() {
        return countOnPage;
    }

    public void setCountOnPage(int countOnPage) {
        this.countOnPage = countOnPage;
    }
}
