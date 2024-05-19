package com.thc.realspr.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TbfeedService {
    public Map<String, Object> create(Map<String, Object> param);
    public Map<String, Object> update(Map<String, Object> param);
    public Map<String, Object> get(String id);
    public List<Map<String, Object>> getAll();

}
