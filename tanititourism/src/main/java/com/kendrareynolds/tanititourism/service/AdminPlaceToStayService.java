package com.kendrareynolds.tanititourism.service;
import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import com.kendrareynolds.tanititourism.repository.PlaceToStayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminPlaceToStayService {

    public final PlaceToStayRepository placeToStayRepository;


    @Autowired
    public AdminPlaceToStayService(PlaceToStayRepository placeToStayRepository) {
        this.placeToStayRepository = placeToStayRepository;
    }

    public Page<PlaceToStay> getAllPlacesToStay(int page, int size) {
        return placeToStayRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Optional<PlaceToStay> getPlaceToStay(Long id) {
        return placeToStayRepository.findById(id);
    }

    public void deleteThingToDo(Long id) {
        placeToStayRepository.deleteById(id);
    }
}
