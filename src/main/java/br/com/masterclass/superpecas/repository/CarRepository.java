package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("CarRepository")
public interface CarRepository extends PagingAndSortingRepository<Car, Long>, CrudRepository<Car, Long> {

    @Query("SELECT car FROM Car car")
    Page<Car> getAllByPage(Pageable pageable);

    @Query("SELECT car FROM Car car " +
            "WHERE car.id = ?1 ")
    Car findById(long id);

    @Query("SELECT car FROM Car car " +
            "WHERE car.modelName like concat ('%',?1,'%') " +
            "OR ?1 IS NULL ")
    Page<Car> findByModelNameByPage(String modelName, Pageable pageable);
}
