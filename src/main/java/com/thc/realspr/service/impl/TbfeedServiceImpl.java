package com.thc.realspr.service.impl;

import com.thc.realspr.domain.Tbfeed;
import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.repository.TbfeedRepository;
import com.thc.realspr.repository.TbuserRepository;
import com.thc.realspr.service.TbfeedService;
import com.thc.realspr.service.TbuserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbfeedServiceImpl implements TbfeedService {

    private final TbfeedRepository tbfeedRepository;

    public TbfeedServiceImpl(
            TbfeedRepository tbfeedRepository
    ) {
        this.tbfeedRepository = tbfeedRepository;
    }

    public Map<String, Object> create(Map<String, Object> param) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
//        Tbuser tbuser = Tbuser.of(param.get("username") + "", param.get("password") + "");
//        tbuserRepository.save(tbuser);
        System.out.println(param);
        Tbfeed tbfeed = Tbfeed.of(param.get("id") + "", param.get("title") + "", param.get("author") + "", param.get("content") + "");
        tbfeedRepository.save(tbfeed);
        returnMap.put("id", tbfeed.getId());
        returnMap.put("title", tbfeed.getTitle());
        returnMap.put("content", tbfeed.getContent());
        returnMap.put("author", tbfeed.getAuthor());
        returnMap.put("createdAt", tbfeed.getCreatedDate());
        returnMap.put("modifiedAt", tbfeed.getModifiedDate());
        return returnMap;
    }

    public void delete(String id) {
//        Tbfeed tbfeed = tbfeedRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
//        tbfeed.setDeleted("Y");
//        tbfeedRepository.save(tbfeed);
        tbfeedRepository.deleteById(id);

    }

    public Map<String, Object> update(Map<String, Object> param) {
//        Map<String, Object> returnMap = new HashMap<String, Object>();
//        System.out.println(param);
//        Tbuser tbuser = tbuserRepository.findById(param.get("id") + "").orElseThrow(() -> new RuntimeException(""));
//        if(param.get("name") != null) {
//            tbuser.setName(param.get("name") + "");
//        }
//        if(param.get("nick") != null) {
//            tbuser.setNick(param.get("nick") + "");
//        }
//        if(param.get("phone") != null) {
//            tbuser.setPhone(param.get("phone") + "");
//        }
//
//        tbuserRepository.save(tbuser);
//        returnMap.put("id", tbuser.getId());
//        return returnMap;
        return null;
    }

    public Map<String, Object> get(String id) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(id);
        Tbfeed tbfeed = tbfeedRepository.findById(id).orElseThrow(() -> new RuntimeException(""));

        returnMap.put("id", tbfeed.getId());
        returnMap.put("title", tbfeed.getTitle());
        returnMap.put("content", tbfeed.getContent());
        returnMap.put("author", tbfeed.getAuthor());
        returnMap.put("createdAt", tbfeed.getCreatedDate());
        returnMap.put("modifiedAt", tbfeed.getModifiedDate());

        return returnMap;
    }

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> result = new ArrayList<>();
//        List<Tbfeed> allFeed  = tbfeedRepository.findAll();
        List<Tbfeed> allFeed = tbfeedRepository.findByDeletedNot("Y");

        for (Tbfeed tbfeed : allFeed) {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("id", tbfeed.getId());
            returnMap.put("title", tbfeed.getTitle());
            returnMap.put("content", tbfeed.getContent());
            returnMap.put("author", tbfeed.getAuthor());
            returnMap.put("createdAt", tbfeed.getCreatedDate());
            returnMap.put("modifiedAt", tbfeed.getModifiedDate());
            result.add(returnMap);
        }

        return result;
    }
}
