package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.DTO.CarDTO;
import br.com.masterclass.superpecas.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<CarDTO> getAllByPage(@RequestParam int page, @RequestParam int size) {
        return carService.getAllByPage(page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CarDTO findById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @RequestMapping(value = "/modelName", method = RequestMethod.GET)
    public Page<CarDTO> findByModelNameByPage(@RequestParam String modelName, @RequestParam int page, @RequestParam int size) {
        return  carService.findByModelNameByPage(modelName, page, size);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public CarDTO save(@RequestBody CarDTO carDTO) {
        return carService.save(carDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public CarDTO update(@RequestBody CarDTO carDTO) {
        return carService.update(carDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        carService.delete(id);
        return "Deletado com sucesso!";
    }

    @RequestMapping(value = "/multiple/{ids}", method = RequestMethod.DELETE)
    public String deleteMultiple(@PathVariable List<Long> ids) {
        carService.deleteMultiple(ids);
        return "Deletados com sucesso!";
    }
}
