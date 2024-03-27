package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.DTO.PartDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PartService {
    Page<PartDTO> getAllByPage(int page, int size);

    PartDTO findById(Long id);

    Page<PartDTO> findByNameByPage(String modelName, int page, int size);

    PartDTO save(PartDTO PartDTO);

    PartDTO update(PartDTO PartDTO);

    void delete(Long id);

    void deleteMultiple(List<Long> ids);
}
