package br.com.masterclass.superpecas.model.DTO;

import br.com.masterclass.superpecas.model.Car;

public class CarDTO {

    Long id;
    String modelName;
    String manufacturer;
    String uniqueCode;

    public CarDTO() {}

    public CarDTO(Car car) {
        this.id = car.getId();
        this.modelName = car.getModelName();
        this.manufacturer = car.getManufacturer();
        this.uniqueCode = car.getUniqueCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
