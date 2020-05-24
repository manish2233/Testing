package com.giovannottix.recipe.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/22/20, Fri
 */
public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
