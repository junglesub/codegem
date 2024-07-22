package com.thc.realspr.service.impl;

import com.thc.realspr.domain.TbKaFeed;
import com.thc.realspr.domain.Tbfeed;
import com.thc.realspr.repository.TbKaFeedRepository;
import com.thc.realspr.service.TbKaFeedService;
import com.thc.realspr.service.TbfeedService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbKaFeedServiceImpl implements TbKaFeedService {

    private final TbKaFeedRepository tbKaFeedRepository;

    public TbKaFeedServiceImpl(
            TbKaFeedRepository tbKaFeedRepository
    ) {
        this.tbKaFeedRepository = tbKaFeedRepository;
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
        List<TbKaFeed> allFeed = tbKaFeedRepository.findByDeletedNot("Y");

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
}
