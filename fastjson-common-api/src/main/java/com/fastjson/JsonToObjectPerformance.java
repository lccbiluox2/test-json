package com.fastjson;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.fastjson.entity.UserDO;
import com.fastjson.entity.UserVO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JsonToObjectPerformance {

    private List<UserDO> list = new LinkedList<>();
    private static Integer count = 10000;

    public void before() {
        for (int i = 0; i < count; i++) {
            UserDO userDO = new UserDO();
            userDO.setAge(i);
            userDO.setName("name-" + i);
            userDO.setNicName("name-" + i);

            list.add(userDO);
        }
    }


    public void jsonToObj() {
        Long start = System.currentTimeMillis();
        List<UserVO> userVOList = JSON.parseArray(JSON.toJSONString(list), UserVO.class);
        Long end = System.currentTimeMillis();
        System.out.println("jsonToObj耗时:" + (end - start));
    }

    public void setObj() {
        Long start = System.currentTimeMillis();
        List<UserVO> userVOList = new ArrayList<>(list.size());
        for (UserDO userDO : list) {
            UserVO userVO = new UserVO();
            userVO.setAge(userDO.getAge());
            userVO.setName(userDO.getName());
            userVO.setNicName(userDO.getNicName());
            userVOList.add(userVO);
        }
        Long end = System.currentTimeMillis();
        System.out.println("setObj耗时:" + (end - start));
    }

    public void copyProperties() {
        Long start = System.currentTimeMillis();
        List<UserVO> userVOList = new ArrayList<>(list.size());
        for (UserDO userDO : list) {
            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(userDO, userVO);
            userVOList.add(userVO);
        }
        Long end = System.currentTimeMillis();
        System.out.println("BeanUtil耗时:" + (end - start));
    }
}
