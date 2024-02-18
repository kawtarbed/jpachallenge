package com.example.demo.Controller;

import com.example.demo.Entity.DTO.TaskRequestDto;
import com.example.demo.Entity.Task;
import com.example.demo.Service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;


    @GetMapping("/")
    public ResponseEntity<?> getAllTasks()
    {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskRequestDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequestDto task){
        try {
            return ResponseEntity.ok(taskService.updateTask(task));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
