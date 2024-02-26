package tqs.geo;

import java.util.Objects;

public class Address {
    private String city, zipCode, road, houseNumber;

    public Address(String city, String zipCode, String road, String houseNumber){
        this.city = city;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.road = road;
    }

     public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zipCode;
    }

    public void setZip(String zio) {
        this.zipCode = zio;
    }

 
    public String getHouseNumber() {
        return houseNumber;
    }

    
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "road=" + road + ", city=" + city + ", zip=" + zipCode + ", houseNumber=" + houseNumber + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!road.equals(address.road)) return false;
        if (!Objects.equals(city, address.city)) return false;
        if (!Objects.equals(zipCode, address.zipCode)) return false;
        return Objects.equals(houseNumber, address.houseNumber);
    }

    @Override
    public int hashCode() {
        int result = road.hashCode();
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        return result;
    }
}



