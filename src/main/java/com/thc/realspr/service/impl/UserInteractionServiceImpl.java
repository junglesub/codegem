package com.thc.realspr.service.impl;

import com.thc.realspr.domain.TbUserInteraction;
import com.thc.realspr.dto.TbuserDto;
import com.thc.realspr.dto.UserInteractionDto;
import com.thc.realspr.exception.DuplicateEntityException;
import com.thc.realspr.repository.TbUserInteractionRepository;
import com.thc.realspr.service.UserInteractionService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserInteractionServiceImpl implements UserInteractionService {


    private final TbUserInteractionRepository tbUserInteractionRepository;

    public UserInteractionServiceImpl(TbUserInteractionRepository tbUserInteractionRepository) {
        this.tbUserInteractionRepository = tbUserInteractionRepository;
    }

    @Override
    public String seen(UserInteractionDto.SeenSubjectReqDto param, String userId) {
        try {
            tbUserInteractionRepository.save(TbUserInteraction.of(userId, param.getSubjectId()));
        } catch (DuplicateKeyException e) {
            throw new DuplicateEntityException("User with id " + userId + " already seen " + param.getSubjectId());
        }

        return "{}";
    }
}
