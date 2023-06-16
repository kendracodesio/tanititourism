package com.kendrareynolds.tanititourism.service;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.repository.ThingToDoRepository;
import com.kendrareynolds.tanititourism.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AdminThingToDoService {

    public final ThingToDoRepository thingToDoRepository;
    public final ActionReportService actionReportService;
    public final UserRepository userRepository;

    @Autowired
    public AdminThingToDoService(ThingToDoRepository thingToDoRepository, ActionReportService actionReportService,
                                 UserRepository userRepository) {
        this.thingToDoRepository = thingToDoRepository;
        this.actionReportService = actionReportService;
        this.userRepository = userRepository;
    }

    public void addThingToDo(ThingToDo thingToDo, String username) {
        ThingToDo savedThingToDo = thingToDoRepository.save(thingToDo);
        actionReportService.recordAction(username, "CREATE", savedThingToDo);
    }

    public void updateThingToDo(ThingToDo thingToDo, String username) {
        thingToDoRepository.findById(thingToDo.getId())
                .orElseThrow(() -> new EntityNotFoundException("ThingToDo not found with id: " + thingToDo.getId()));
        ThingToDo updatedThingToDo = thingToDoRepository.save(thingToDo);
        actionReportService.recordAction(username, "UPDATE", updatedThingToDo);
    }

    public List<ThingToDo> getAllThingsToDo() {
        return thingToDoRepository.findAll();
    }

    public Optional<ThingToDo> getThingToDo(Long id) {
        return thingToDoRepository.findById(id);
    }

}

