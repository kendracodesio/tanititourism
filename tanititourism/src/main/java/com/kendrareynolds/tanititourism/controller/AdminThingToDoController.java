package com.kendrareynolds.tanititourism.controller;
import com.kendrareynolds.tanititourism.entity.Cost;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.service.AdminThingToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/things-to-do")
public class AdminThingToDoController {

    private final AdminThingToDoService adminThingToDoService;

    @Autowired
    AdminThingToDoController(AdminThingToDoService adminThingToDoService) {
        this.adminThingToDoService = adminThingToDoService;
    }

    @PostMapping("/new-listing")
    public ResponseEntity<Void> addThingToDo(@RequestBody Map<String, Object> payload) {
        System.out.println("Payload " + payload);
        ThingToDo thingToDo = convertPayloadToThingToDo(payload);
        Long regionId = Long.valueOf((Integer) payload.get("regionId"));
        Set<Long> doTypesIds = extractDoTypeIdsFromPayload(payload);
        adminThingToDoService.addThingToDo(thingToDo, regionId, doTypesIds);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<Void> updateThingToDo(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        System.out.println("Payload " + payload);
        ThingToDo updatedThingToDo = convertPayloadToThingToDo(payload);
        Long regionId = Long.valueOf((Integer) payload.get("regionId"));
        Set<Long> doTypesIds = extractDoTypeIdsFromPayload(payload);
        adminThingToDoService.updateThingToDo(updatedThingToDo, id, regionId, doTypesIds);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deleteThingToDo(@PathVariable Long id) {
        adminThingToDoService.deleteThingToDo(id);
        return ResponseEntity.ok().build();
    }

    private Set<Long> extractDoTypeIdsFromPayload(Map<String, Object> payload) {
        Set<Long> doTypesIds = ((List<?>) payload.get("doTypesIds")).stream()
                .map(o -> {
                    if (o instanceof Integer) {
                        return Long.valueOf((Integer) o);
                    } else if (o instanceof Double) {
                        return Long.valueOf(((Double) o).intValue());
                    } else {
                        throw new IllegalArgumentException("doTypesIds must contain only integer or double values");
                    }
                })
                .collect(Collectors.toSet());
        return doTypesIds;
    }

    private ThingToDo convertPayloadToThingToDo(Map<String, Object> payload) {
        ThingToDo thingToDo = new ThingToDo();
        thingToDo.setName((String) payload.get("name"));
        thingToDo.setCost(Cost.valueOf((String) payload.get("cost")));
        thingToDo.setPhone((String) payload.get("phone"));
        thingToDo.setDescription((String) payload.get("description"));
        thingToDo.setImageUrl((String) payload.get("imageUrl"));
        thingToDo.setImageAltText((String) payload.get("imageAltText"));
        return thingToDo;
    }


//    @PutMapping
//    public ResponseEntity<Void> updateThingToDo(@Valid @RequestBody ThingToDo thingToDo) {
//        adminThingToDoService.updateThingToDo(thingToDo);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }

    @GetMapping("/list")
    public List<ThingToDo> getAllThingsToDo() {
        return adminThingToDoService.getAllThingsToDo();
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<ThingToDo> getThingToDo(@PathVariable("id") Long id) {
            return adminThingToDoService.getThingToDo(id);
            }
}



//        Set<Long> doTypesIds = ((List<Integer>) payload.get("doTypeIds")).stream()
//                .map(Long::valueOf)
//                .collect(Collectors.toSet());