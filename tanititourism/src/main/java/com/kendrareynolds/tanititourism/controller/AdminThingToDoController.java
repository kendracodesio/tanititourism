package com.kendrareynolds.tanititourism.controller;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.service.AdminThingToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/things-to-do")
public class AdminThingToDoController {

    private final AdminThingToDoService adminThingToDoService;

    @Autowired
    AdminThingToDoController(AdminThingToDoService adminThingToDoService) {
        this.adminThingToDoService = adminThingToDoService;
    }

    @PostMapping
    public ResponseEntity<Void> addThingToDo(@RequestBody ThingToDo thingToDo,
                                             @RequestParam("username") String username) {
        adminThingToDoService.addThingToDo(thingToDo, username);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping
    public ResponseEntity<Void> updateThingToDo(@Valid @RequestBody ThingToDo thingToDo,
                                                @RequestParam("username") String username) {
        adminThingToDoService.updateThingToDo(thingToDo, username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/list")
    public List<ThingToDo> getAllThingsToDo() {
        return adminThingToDoService.getAllThingsToDo();
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<ThingToDo> getThingToDo(@PathVariable("id") Long id) {
            return adminThingToDoService.getThingToDo(id);
            }
}
