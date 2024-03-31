package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.ApiResponse;
import br.com.masterclass.superpecas.model.DTO.PartDTO;
import br.com.masterclass.superpecas.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/part")
public class PartController {

    @Autowired
    PartService partService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ApiResponse<Page<PartDTO>> getAllByPage(@RequestParam int page, @RequestParam int size) {
        return new ApiResponse<>(HttpStatus.OK.value(), "All parts returned successfully", partService.getAllByPage(page, size));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse<PartDTO> findById(@PathVariable Long id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Part returned successfully", partService.findById(id));
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public ApiResponse<Page<PartDTO>> findByNameByPage(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
        return new ApiResponse<>(HttpStatus.OK.value(), "All parts returned successfully", partService.findByNameByPage(name, page, size));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ApiResponse<PartDTO> save(@RequestBody PartDTO partDTO) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Part saved successfully", partService.save(partDTO));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ApiResponse<PartDTO> update(@RequestBody PartDTO partDTO) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Part updated successfully", partService.update(partDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Void> delete(@PathVariable Long id) {
        partService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Part deleted successfully", null);
    }

    @RequestMapping(value = "/multiple/{ids}", method = RequestMethod.DELETE)
    public ApiResponse<Void> deleteMultiple(@PathVariable List<Long> ids) {
        partService.deleteMultiple(ids);
        return new ApiResponse<>(HttpStatus.OK.value(), "Parts deleted successfully", null);
    }
}
