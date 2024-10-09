package com.thc.realspr.service.impl;

import com.thc.realspr.domain.TbUserInteraction;
import com.thc.realspr.domain.TbUserLike;
import com.thc.realspr.dto.TbuserDto;
import com.thc.realspr.dto.UserInteractionDto;
import com.thc.realspr.exception.DuplicateEntityException;
import com.thc.realspr.id.UserSubjectId;
import com.thc.realspr.repository.TbUserInteractionRepository;
import com.thc.realspr.repository.TbUserLikeRepository;
import com.thc.realspr.service.UserInteractionService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserInteractionServiceImpl implements UserInteractionService {


    private final TbUserInteractionRepository tbUserInteractionRepository;
    private final TbUserLikeRepository tbUserLikeRepository;

    public UserInteractionServiceImpl(TbUserInteractionRepository tbUserInteractionRepository, TbUserLikeRepository tbUserLikeRepository) {
        this.tbUserInteractionRepository = tbUserInteractionRepository;
        this.tbUserLikeRepository = tbUserLikeRepository;
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

    @Override
    public String like(UserInteractionDto.SeenSubjectReqDto param, String userId) {
        try {
            tbUserLikeRepository.save(TbUserLike.of(userId, param.getSubjectId()));
        } catch (DuplicateKeyException e) {
            throw new DuplicateEntityException("User with id " + userId + " already liked " + param.getSubjectId());
        }

        return "{}";
    }

    @Override
    public String unLike(UserInteractionDto.SeenSubjectReqDto param, String userId) {
        tbUserLikeRepository.deleteById(new UserSubjectId(userId, param.getSubjectId()));
        return "{}";
    }
}
