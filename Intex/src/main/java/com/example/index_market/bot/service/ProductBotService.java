package com.example.index_market.bot.service;

import com.example.index_market.bot.payload.ProductDto;
import com.example.index_market.bot.payload.SendOwnPhoto;
import com.example.index_market.bot.utils.BotConstant;
import com.example.index_market.bot.utils.CommandUtils;
import com.example.index_market.bot.utils.PageSizeException;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.entity.product.Product;
import com.example.index_market.entity.user.BotUser;
import com.example.index_market.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Service
@Component
@RequiredArgsConstructor
public class ProductBotService {

    private final ProductRepository productRepository;


    public ProductDto getUsersPageble(BotUser user) throws PageSizeException {
        ProductDto productDto = new ProductDto();
        Page<Product> all = productRepository.findAll(CommandUtils.simplePageable(user.getCurrentlyPage() - 1, 5));
        Map<String, SendOwnPhoto> sendOwnPhotoMap = new HashMap<>();
        for (Product product : all) {
            SendOwnPhoto sendOwnPhoto = new SendOwnPhoto();
            StringBuilder detail = new StringBuilder();
            for (Detail d : product.getDetailList()) {
                detail.append(d.getNameRu()).append("/").append(d.getNameUz()).append(",");
            }
            String caption = "Price: " + product.getDisPrice() +
                    "\nDescription: " + product.getDescription() +
                    "\nSize: " + product.getSize() +
                    "\nHeight: " + product.getHeight() +
                    "\nFrame: " + product.getFrameRu() + "/" + product.getFrameUz() +
                    "\nCategory: " + product.getCategory().getNameRu() + "/" + product.getCategory().getNameRu() +
                    "\nDetails: " + detail;
            String fileName = BotConstant.MY_URL_NGROK + "/api/attachment?name=" + product.getFileName();

            sendOwnPhoto.setChat_id(String.valueOf(user.getChatId()));
            sendOwnPhoto.setPhoto(fileName);
            sendOwnPhoto.setCaption(caption);
            sendOwnPhotoMap.put(product.getId(), sendOwnPhoto);
        }
        productDto.setSendOwnPhotoMap(sendOwnPhotoMap);
        productDto.setTotalPage(all.getTotalPages());
        productDto.setCurrentlyPage(user.getCurrentlyPage());
        return productDto;

    }

}
