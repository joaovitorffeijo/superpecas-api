package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.ApiResponse;
import br.com.masterclass.superpecas.model.DTO.CarDTO;
import br.com.masterclass.superpecas.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ApiResponse<CarDTO> getAllByPage(@RequestParam int page, @RequestParam int size) {
        return new ApiResponse<>(HttpStatus.OK.value(), "All cars returned successfully", carService.getAllByPage(page, size));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse<CarDTO> findById(@PathVariable Long id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Cars returned successfully", carService.findById(id));
    }

    @RequestMapping(value = "/modelName", method = RequestMethod.GET)
    public ApiResponse<CarDTO> findByModelNameByPage(@RequestParam String modelName, @RequestParam int page, @RequestParam int size) {
        return  new ApiResponse<>(HttpStatus.OK.value(), "All cars returned successfully", carService.findByModelNameByPage(modelName, page, size));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ApiResponse<CarDTO> save(@RequestBody CarDTO carDTO) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Car saved successfully", carService.save(carDTO));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ApiResponse<CarDTO> update(@RequestBody CarDTO carDTO) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Car updated successfully", carService.update(carDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Void> delete(@PathVariable Long id) {
        carService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Car deleted successfully", null);
    }

    @RequestMapping(value = "/multiple/{ids}", method = RequestMethod.DELETE)
    public ApiResponse<Void> deleteMultiple(@PathVariable List<Long> ids) {
        carService.deleteMultiple(ids);
        return new ApiResponse<>(HttpStatus.OK.value(), "Cars deleted successfully", null);
    }
}
