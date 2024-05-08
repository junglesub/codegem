package com.thc.realspr.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TbuserService {
    public Map<String, Object> create(Map<String, Object> param);
    public Map<String, Object> update(Map<String, Object> param);
    public Map<String, Object> get(String id);
}
