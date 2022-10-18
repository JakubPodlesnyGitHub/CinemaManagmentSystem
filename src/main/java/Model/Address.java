package Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private int buildingNumber;
    private Integer apartmentNumber;
    private String province;
    private String zipCode;
    private String city;
    private String country;

    public Address(String street, int buildingNumber, Integer apartmentNumber, String province, String zipCode, String city, String country) {
        setStreet(street);
        setBuildingNumber(buildingNumber);
        setApartmentNumber(apartmentNumber);
        setProvince(province);
        setZipCode(zipCode);
        setCity(city);
        setCountry(country);
    }

    public Address() {

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
