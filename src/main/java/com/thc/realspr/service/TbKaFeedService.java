package com.thc.realspr.service;

import com.thc.realspr.dto.TbmessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TbKaFeedService {
    public Map<String, Object> create(Map<String, Object> param);

    public Map<String, Object> getLastKaFeed();

    //    public Map<String, Object> update(Map<String, Object> param);
//    public Map<String, Object> get(String id);
//    public void delete(String id);
    public List<Map<String, Object>> getAll();

    List<TbmessageDto.Detail> scrollList(String userId);

    List<TbmessageDto.Detail> scrollList(int afterSentAt, String userId);
}
