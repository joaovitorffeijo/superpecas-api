package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.DTO.CarDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

    Page<CarDTO> getAllByPage(String modelName, int page, int size);

    CarDTO findById(Long id);

    List<String> getManufacturerList();

    List<CarDTO> getCarList();

    CarDTO save(CarDTO carDTO);

    CarDTO update(CarDTO carDTO);

    void delete(Long id);

    void deleteMultiple(List<Long> ids);
}
