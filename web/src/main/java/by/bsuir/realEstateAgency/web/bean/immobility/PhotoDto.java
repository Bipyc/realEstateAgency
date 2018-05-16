package by.bsuir.realEstateAgency.web.bean.immobility;

public class PhotoDto {
    private Long id;

    private String path;

    public PhotoDto() {
    }

    public PhotoDto(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
