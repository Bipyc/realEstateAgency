package by.bsuir.realEstateAgency.core.bean;

import by.bsuir.realEstateAgency.core.model.TypeImmobility;

public class SearchForm {
    private String cityName;

    private TypeImmobility typeImmobility;

    private Long typeApplication;

    private Double minPrice;

    private Double maxPrice;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public TypeImmobility getTypeImmobility() {
        return typeImmobility;
    }

    public void setTypeImmobility(TypeImmobility typeImmobility) {
        this.typeImmobility = typeImmobility;
    }

    public Long getTypeApplication() {
        return typeApplication;
    }

    public void setTypeApplication(Long typeApplication) {
        this.typeApplication = typeApplication;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
