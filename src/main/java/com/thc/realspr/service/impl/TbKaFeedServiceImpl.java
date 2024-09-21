package com.thc.realspr.service.impl;

import com.thc.realspr.domain.TbKaFeed;
import com.thc.realspr.dto.TbmessageDto;
import com.thc.realspr.mapper.TbmessageMapper;
import com.thc.realspr.repository.TbKaFeedRepository;
import com.thc.realspr.service.FirebaseService;
import com.thc.realspr.service.TbKaFeedService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbKaFeedServiceImpl implements TbKaFeedService {

    private final TbKaFeedRepository tbKaFeedRepository;
    private final TbmessageMapper tbmessageMapper;
    private final FirebaseService firebaseService;

    public TbKaFeedServiceImpl(
            TbKaFeedRepository tbKaFeedRepository,
            TbmessageMapper tbmessageMapper,
            FirebaseService firebaseService
    ) {
        this.tbKaFeedRepository = tbKaFeedRepository;
        this.tbmessageMapper = tbmessageMapper;
        this.firebaseService = firebaseService;
    }

    public Map<String, Object> create(Map<String, Object> param) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
//        Tbuser tbuser = Tbuser.of(param.get("username") + "", param.get("password") + "");
//        tbuserRepository.save(tbuser);
        System.out.println(param);
        TbKaFeed tbKaFeed = TbKaFeed.of(param.get("id") + "", param.get("chat_id") + "", param.get("user_id") + "", param.get("message") + "", param.get("sent_at") + "", param.get("client_message_id") + "");
        tbKaFeedRepository.save(tbKaFeed);
        returnMap.put("id", tbKaFeed.getId());
        return returnMap;
    }

    public Map<String, Object> getLastKaFeed() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        TbKaFeed tbKaFeed = tbKaFeedRepository.findTopByOrderBySentAtDesc();
        if (tbKaFeed != null) {
            returnMap.put("id", tbKaFeed.getId());
            returnMap.put("sentAt", tbKaFeed.getSentAt());
        } else {
            returnMap.put("id", 0);
            returnMap.put("sentAt", 0);
        }
        return returnMap;
    }

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<TbKaFeed> allFeed = tbKaFeedRepository.findAllByDeletedNotOrderBySentAtDesc("Y");

        for (TbKaFeed entry : allFeed) {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("id", entry.getId());
            returnMap.put("message", entry.getMessage());
            returnMap.put("sentAt", entry.getSentAt());
            returnMap.put("createdAt", entry.getCreatedDate());
            returnMap.put("modifiedAt", entry.getModifiedDate());
            result.add(returnMap);
        }

        return result;
    }

    public List<TbmessageDto.Detail> scrollList() {
        List<TbmessageDto.Detail> result = tbmessageMapper.scrollList();

        for (TbmessageDto.Detail message : result) {
            List<String> files = new ArrayList<>();
            for (TbmessageDto.FileDetail file : tbmessageMapper.fileDetails(message.getId())) {
                files.add(firebaseService.getSignedUrl("KaFile/" + file.getHash() + "." + file.getExt()));
            }
            message.setFiles(files);
        }
        return result;
    }
}
