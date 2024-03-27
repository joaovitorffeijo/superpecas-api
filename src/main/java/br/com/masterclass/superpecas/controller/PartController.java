package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.DTO.PartDTO;
import br.com.masterclass.superpecas.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/part")
public class PartController {

    @Autowired
    PartService partService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<PartDTO> getAllByPage(@RequestParam int page, @RequestParam int size) {
        return partService.getAllByPage(page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PartDTO findById(@PathVariable Long id) {
        return partService.findById(id);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public Page<PartDTO> findByNameByPage(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
        return  partService.findByNameByPage(name, page, size);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public PartDTO save(@RequestBody PartDTO partDTO) {
        return partService.save(partDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public PartDTO update(@RequestBody PartDTO partDTO) {
        return partService.update(partDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        partService.delete(id);
        return "Deletado com sucesso!";
    }

    @RequestMapping(value = "/multiple/{ids}", method = RequestMethod.DELETE)
    public String deleteMultiple(@PathVariable List<Long> ids) {
        partService.deleteMultiple(ids);
        return "Deletados com sucesso!";
    }
}
