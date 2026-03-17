package com.example.shopping.services;

import com.example.shopping.model.furniture;
import com.example.shopping.repositories.furnitureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class furnitureService {

    private static final Logger logger = LoggerFactory.getLogger(furnitureService.class);

    @Autowired
    furnitureRepo furnitureRepo;


    public void addfurniture(furniture furniture) {
        logger.info("CUSTOMER MADE WITH ID OF : " + furniture.getId());
        furnitureRepo.save(furniture);
    }


    public List<furniture> getAllfurniture() {
        if (!(furnitureRepo.findAll().iterator().hasNext())) {
            logger.info("FAILED TO RETRIEVE ALL furniture");
            throw new RuntimeException("Resource Not Found");
        } else {
            List<furniture> listOffurniture = new ArrayList<furniture>();
            furnitureRepo.findAll()
                    .forEach(listOffurniture::add);
            logger.info("SUCCESSFULLY RETRIEVED ALL furniture");
            return listOffurniture;
        }
    }


    public furniture getfurnitureById(String id){
       if (!(furnitureRepo.existsById(id))) {
           logger.info("furniture WITH ID OF " + id + " DOESNT EXIST");
            throw new RuntimeException("Resource Not Found");
       }else
        return furnitureRepo.findById(id).get();
        }


    public void updatefurniture(furniture furniture, String id){
        if(!(furnitureRepo.existsById(furniture.getId()))){
           logger.info("furniture ID DOESNT EXIST");
        }
        logger.info("SUCCESSFULLY UPDATED furniture");
        furnitureRepo.save(furniture);
    }

    public void deletefurniture(furniture furniture){
        if(!(furnitureRepo.existsById((furniture.getId())))){
            logger.info("furniture WITH ID OF " + furniture.getId() + " DOESNT EXIST");
            throw new RuntimeException("Resource Not Found");
            }else furnitureRepo.delete(furniture);
        }


    public void deleteAllfurniture() {
        furnitureRepo.deleteAll();
    }
}