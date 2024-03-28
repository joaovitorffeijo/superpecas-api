package br.com.masterclass.superpecas.service.impl;

import br.com.masterclass.superpecas.model.Car;
import br.com.masterclass.superpecas.model.DTO.PartDTO;
import br.com.masterclass.superpecas.model.Part;
import br.com.masterclass.superpecas.repository.CarRepository;
import br.com.masterclass.superpecas.repository.PartRespository;
import br.com.masterclass.superpecas.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service("PartService")
public class PartServiceImpl implements PartService {

    @Autowired
    PartRespository partRespository;

    @Autowired
    CarRepository carRepository;


    @Override
    public Page<PartDTO> getAllByPage(int page, int size) {
        return partRespository.getAllByPage(PageRequest.of(page, size)).map(PartDTO::new);
    }

    @Override
    public PartDTO findById(Long id) {
        return new PartDTO(partRespository.findById(id).orElse(null));
    }

    @Override
    public Page<PartDTO> findByNameByPage(String name, int page, int size) {
        return partRespository.findByNameByPage(name, PageRequest.of(page, size)).map(PartDTO::new);
    }

    @Transactional
    @Override
    public PartDTO save(PartDTO partDTO) {
        Part part = new Part();

        part = validateAndSetFields(part, partDTO);

        return new PartDTO(partRespository.save(part));
    }

    @Transactional
    @Override
    public PartDTO update(PartDTO partDTO) {
        Part part = partRespository.findById(partDTO.getId()).orElse(null);

        if (part == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing or invalid required field: Id");

        part = validateAndSetFields(part, partDTO);

        return new PartDTO(partRespository.save(part));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        partRespository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteMultiple(List<Long> ids) {
        partRespository.deleteAllById(ids);
    }

    private Part validateAndSetFields(Part part, PartDTO partDTO) {
        if (StringUtils.isEmpty(partDTO.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: Name");
        else
            part.setName(partDTO.getName());

        part.setDescription(partDTO.getDescription());

        if (StringUtils.isEmpty(partDTO.getSerialNumber()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: SerialNumber");
        else
            part.setSerialNumber(partDTO.getSerialNumber());

        if (StringUtils.isEmpty(partDTO.getManufacturer()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: Manufacturer");
        else
            part.setManufacturer(partDTO.getManufacturer());

        if (StringUtils.isEmpty(partDTO.getCarModel()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: CarModel");
        else
            part.setCarModel(partDTO.getCarModel());

        if (partDTO.getCarId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: CarId");
        else {
            Car car = carRepository.findById(partDTO.getCarId()).orElse(null);

            if (car == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CarId inválido");

            part.setCar(car);
        }

        return part;
    }
}
