package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PartRepository")
public interface PartRespository extends PagingAndSortingRepository<Part, Long>, CrudRepository<Part, Long> {

    @Query("SELECT part FROM Part part " +
            "WHERE part.name like concat ('%',?1,'%') " +
            "OR ?1 IS NULL ")
    Page<Part> getAllByPage(String name, Pageable pageable);

    @Query("SELECT part FROM Part part " +
            "WHERE part.id = ?1 ")
    Part findById(long id);

    @Query("SELECT DISTINCT part.manufacturer FROM Part part")
    List<String> getManufacturerList();
}
