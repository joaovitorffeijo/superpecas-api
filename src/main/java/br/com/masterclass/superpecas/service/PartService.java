package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.DTO.PartDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PartService {
    Page<PartDTO> getAllByPage(String name, int page, int size);

    PartDTO findById(Long id);

    List<String> getManufacturerList();

    PartDTO save(PartDTO PartDTO);

    PartDTO update(PartDTO PartDTO);

    void delete(Long id);

    void deleteMultiple(List<Long> ids);
}
