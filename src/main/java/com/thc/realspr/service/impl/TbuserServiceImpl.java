package com.thc.realspr.service.impl;

import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.dto.DefaultDto;
import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.dto.TbuserDto;
import com.thc.realspr.mapper.TbuserMapper;
import com.thc.realspr.repository.TbuserRepository;
import com.thc.realspr.service.TbuserService;
import com.thc.realspr.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TbuserServiceImpl implements TbuserService {

    private final TbuserRepository tbuserRepository;


    @Autowired
    public TbuserServiceImpl(TbuserMapper tbuserMapper, TbuserRepository tbuserRepository, JwtTokenUtil jwtTokenUtil) {
        this.tbuserRepository = tbuserRepository;
    }

    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
    ) {
        this.tbuserRepository = tbuserRepository;
    }

    public Map<String, Object> create(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbuser tbuser = Tbuser.of(param.get("username") + "", param.get("password") + "");
        tbuserRepository.save(tbuser);
        returnMap.put("id", tbuser.getId());
        return returnMap;
    }
    public Map<String, Object> update(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbuser tbuser = tbuserRepository.findById(param.get("id") + "").orElseThrow(() -> new RuntimeException(""));
        if(param.get("name") != null) {
            tbuser.setName(param.get("name") + "");
        }
        if(param.get("nick") != null) {
            tbuser.setNick(param.get("nick") + "");
        }
        if(param.get("phone") != null) {
            tbuser.setPhone(param.get("phone") + "");
        }

        tbuserRepository.save(tbuser);
        returnMap.put("id", tbuser.getId());
        return returnMap;
    }
    public Map<String, Object> get(String id){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(id);
        Tbuser tbuser = tbuserRepository.findById(id).orElseThrow(() -> new RuntimeException(""));

        returnMap.put("id", tbuser.getId());
        returnMap.put("username", tbuser.getUsername());
        returnMap.put("name", tbuser.getName());
        returnMap.put("nick", tbuser.getNick());
        returnMap.put("phone", tbuser.getPhone());

        return returnMap;
    }

    @Override
    public TbuserDto.CreateResDto access(String param) throws Exception {
        return null;
    }

    /*
    * private final TbuserRepository tbuserRepository;

    public TbuserService(TbuserRepository tbuserRepository) {
        this.tbuserRepository = tbuserRepository;
    }

    public Tbuser findOrCreateUserByEmail(String email) {
        return tbuserRepository.findByEmail(email)
                .orElseGet(() -> {
                    Tbuser newUser = new Tbuser();
                    newUser.setUsername(email);
                    return tbuserRepository.save(newUser);
                });
    }*/


}
