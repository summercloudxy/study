package com.xy.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.mybatis.dao.TbCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xy.mybatis.pojo.TbCard;
import com.xy.mybatis.pojo.TbCardExample;

import java.util.List;

@Service
public class MybatisService {
    @Autowired
    private TbCardMapper tbCardMapper;

    public PageInfo<TbCard> selectCardByPage(int currentPage, int pageSize) {
        TbCardExample example = new TbCardExample();
        TbCardExample.Criteria criteria = example.createCriteria();
        criteria.andCardStyleIdBetween(1,3);
        PageHelper.startPage(currentPage, pageSize);
        List<TbCard> cards = tbCardMapper.selectByExample(example);
        PageInfo<TbCard> pageInfo = new PageInfo<>(cards);
        return pageInfo;
    }


}
