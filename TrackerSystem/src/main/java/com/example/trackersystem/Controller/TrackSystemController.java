package com.example.trackersystem.Controller;

import com.example.trackersystem.ApiResponse.ApiResponse;
import com.example.trackersystem.Model.TrackSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tasktracker")
public class TrackSystemController {
    ArrayList<TrackSystem> tasksTracker = new ArrayList<>();

    @PostMapping("/create")
    public ApiResponse createTask(@RequestBody TrackSystem task) {
        tasksTracker.add(task);
        return new ApiResponse("Task created");
    }

    @GetMapping("/display")
    public ArrayList<TrackSystem> displayTasks() {
        return tasksTracker;
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody TrackSystem task) {
        tasksTracker.set(index, task);
        return new ApiResponse("Task updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasksTracker.remove(index);
        return new ApiResponse("Task deleted");
    }

    @PutMapping("/status")
    public ApiResponse changeStatus(){

        for(TrackSystem t : tasksTracker){
            if(!t.isStatus()){ // if status false/not done
                t.setStatus(true); // change it to true
            }
        }
        return new ApiResponse("Status updated");
    }

    @GetMapping("/search/{title}")
    public ApiResponse searchTask(@PathVariable String title){
        for(TrackSystem task : tasksTracker){
            if(task.getTitle().equals(title)){
                return new ApiResponse("Task Found");
            }
        }
        return new ApiResponse("Task Not Found");
    }

}
