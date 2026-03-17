package com.example.shopping.controllers;


import com.example.shopping.model.furniture;
import com.example.shopping.services.furnitureService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = { 
    "http://localhost:3000",
    "http://k8s-furnitur-furnitur-d921d8d1db-1799175688.us-east-1.elb.amazonaws.com"
    },
    allowCredentials = "true")
public class FurnitureController {

    @Autowired
    furnitureService furnitureService;

    @PostMapping(path = "/furniture")
    public void addfurniture(@RequestBody furniture furniture){
        this.furnitureService.addfurniture(furniture);
    }
    @GetMapping(path = "/furniture")
    public ResponseEntity<List<furniture>> getAllFurniture() {
        List<furniture> all = furnitureService.getAllfurniture();
        return ResponseEntity.ok(all);
    }

    @GetMapping(path = "/furniture/{id}")
    public furniture getfurnitureById(@PathVariable String id){
        return furnitureService.getfurnitureById(id);
    }

    @PutMapping(path = "/furniture/{id}")
    public void updatefurniture(@RequestBody furniture furniture, @PathVariable String id){
        furnitureService.updatefurniture(furniture,id);
    }


    @DeleteMapping(path = "/furniture")
    public void deleteAllfurniture(){
    furnitureService.deleteAllfurniture();
    }

     @DeleteMapping(path = "/furniture/{id}")
    public void deletefurniture(@PathVariable String id){
        furniture furniture1 = furnitureService.getfurnitureById(id);     
        furnitureService.deletefurniture(furniture1);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    //   @GetMapping
    // public void redirectToFurniture(HttpServletResponse response) {
    //     response.setHeader("Location", "/api/v1/furniture");
    //     response.setStatus(HttpStatus.FOUND.value());
    // }
}
