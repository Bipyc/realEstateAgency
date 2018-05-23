package by.bsuir.realEstateAgency.web.bean.pagedList;

public class CheckedItem {
    private Long id;

    private boolean isChecked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
