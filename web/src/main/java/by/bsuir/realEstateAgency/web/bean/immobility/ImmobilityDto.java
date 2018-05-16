package by.bsuir.realEstateAgency.web.bean.immobility;

import by.bsuir.realEstateAgency.core.model.TypeImmobility;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ImmobilityDto {
    private Long id;

    @NotEmpty
    @Size(max = 255)
    private String name;

    private String ownerLogin;

    @NotEmpty
    @Size(max = 10240)
    private String description;

    @NotNull
    @Min(1L)
    private BigDecimal price;

    @NotEmpty
    private String cityName;

    @NotNull
    @Min(1L)
    private Integer numberOfRooms;

    @NotNull
    @Min(0L)
    private Double square;

    @NotEmpty
    @Size(max=1024)
    private String address;

    private TypeImmobility typeImmobility;

    List<MultipartFile> uploadedFiles;

    List<PhotoDto> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TypeImmobility getTypeImmobility() {
        return typeImmobility;
    }

    public void setTypeImmobility(TypeImmobility typeImmobility) {
        this.typeImmobility = typeImmobility;
    }

    public List<MultipartFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<MultipartFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
    }
}
