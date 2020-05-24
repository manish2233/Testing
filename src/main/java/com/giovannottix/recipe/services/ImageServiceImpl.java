package com.giovannottix.recipe.services;

import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/22/20, Fri
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.setImage(getImageByteArray(file));
        recipeRepository.save(recipe);
    }

    private Byte[] getImageByteArray (MultipartFile file){
        Byte[] byteObjects = null;
        try {
            byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return byteObjects;
    }
}

