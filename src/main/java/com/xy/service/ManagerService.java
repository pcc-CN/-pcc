package com.xy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.ManagerMapper;
import com.xy.pojo.SystemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {


    @Autowired
    ManagerMapper managerMapper;
    public SystemManager queryByID(String id){
        QueryWrapper<SystemManager> wrapper = new QueryWrapper<>();
        wrapper.eq("ID",id);

       return managerMapper.selectOne(wrapper);
    }
}
