package com.factoria.coders.services;

import com.factoria.coders.auth.facade.IAuthenticationFacade;
import com.factoria.coders.exceptions.NotFoundException;
import com.factoria.coders.models.Like;
import com.factoria.coders.models.Product;
import com.factoria.coders.models.User;
import com.factoria.coders.repositories.ILikeRepository;
import com.factoria.coders.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService implements ILikeService{

    private final ILikeRepository likeRepository;
    private final IAuthenticationFacade authenticationFacade;

    private final IProductRepository productRepository;

    public LikeService(ILikeRepository likeRepository, IAuthenticationFacade authenticationFacade,IProductRepository productRepository) {
        this.likeRepository = likeRepository;
        this.authenticationFacade = authenticationFacade;
        this.productRepository = productRepository;

    }

    @Override
    public void toggleLike(Long productId) {
        Optional<User> authUser = authenticationFacade.getAuthUser();
        if (authUser.isEmpty()) throw new RuntimeException("Not Authorized");
        var productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) throw new NotFoundException("Product Not Found", "P404");
        var like = likeRepository.findByUser_IdAndProduct_Id(authUser.get().getId(), productId);
        if (like.isPresent()) {
            likeRepository.deleteById(like.get().getId());
            return;
        }
        if(like.isEmpty()) {
           System.out.println("no hay Like");
           var user = authUser.get();
           var product = productRepository.findById(productId).get();
           var likeToSave = new Like(user,product);
           likeRepository.save(likeToSave);
        }


    }
}
