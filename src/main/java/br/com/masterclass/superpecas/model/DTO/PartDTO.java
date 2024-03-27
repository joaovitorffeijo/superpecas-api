package br.com.masterclass.superpecas.model.DTO;

import br.com.masterclass.superpecas.model.Part;

public class PartDTO {

    Long id;
    String name;
    String description;
    String serialNumber;
    String manufacturer;
    String carModel;
    Long carId;

    public PartDTO() {}

    public PartDTO(Part part) {
        this.id = part.getId();
        this.name = part.getName();
        this.description = part.getDescription();
        this.serialNumber = part.getSerialNumber();
        this.manufacturer = part.getManufacturer();
        this.carModel = part.getCarModel();
        this.carId = part.getCar().getId();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}