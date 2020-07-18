package com.card.service;

import org.springframework.stereotype.Service;

@Service
public interface CardService {
    Integer countByproductId(Integer productId);
}
