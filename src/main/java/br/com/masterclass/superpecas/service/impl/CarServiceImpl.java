package br.com.masterclass.superpecas.service.impl;

import br.com.masterclass.superpecas.model.Car;
import br.com.masterclass.superpecas.model.DTO.CarDTO;
import br.com.masterclass.superpecas.repository.CarRepository;
import br.com.masterclass.superpecas.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service("CarService")
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public Page<CarDTO> getAllByPage(String modelName, int page, int size) {
        return carRepository.getAllByPage(modelName, PageRequest.of(page, size)).map(CarDTO::new);
    }

    @Override
    public CarDTO findById(Long id) {
        return new CarDTO(carRepository.findById(id).orElse(null));
    }

    @Override
    public List<String> getManufacturerList() {
        return carRepository.getManufacturerList();
    }

    @Override
    public List<CarDTO> getCarList() {
        return carRepository.getCarList().stream().map(CarDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CarDTO save(CarDTO carDTO) {
        Car car = new Car();

        car = validateAndSetFields(car, carDTO);

        return new CarDTO(carRepository.save(car));
    }

    @Transactional
    @Override
    public CarDTO update(CarDTO carDTO) {
        Car car = carRepository.findById(carDTO.getId()).orElse(null);

        if (car == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing or invalid required field: Id");

        car = validateAndSetFields(car, carDTO);

        return new CarDTO(carRepository.save(car));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteMultiple(List<Long> ids) {
        carRepository.deleteAllById(ids);
    }

    private Car validateAndSetFields(Car car, CarDTO carDTO){
        if (StringUtils.isEmpty(carDTO.getModelName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: ModelName");
        else
            car.setModelName(carDTO.getModelName());

        if (StringUtils.isEmpty(carDTO.getManufacturer()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: Manufacturer");
        else
            car.setManufacturer(carDTO.getManufacturer());

        if (StringUtils.isEmpty(carDTO.getUniqueCode()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: UniqueCode");
        else
            car.setUniqueCode(carDTO.getUniqueCode());

        return car;
    }
}
