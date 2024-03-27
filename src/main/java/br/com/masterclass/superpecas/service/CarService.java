package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.DTO.CarDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

    Page<CarDTO> getAllByPage(int page, int size);

    CarDTO findById(Long id);

    Page<CarDTO> findByModelNameByPage(String modelName, int page, int size);

    CarDTO save(CarDTO carDTO);

    CarDTO update(CarDTO carDTO);

    void delete(Long id);

    void deleteMultiple(List<Long> ids);
}
