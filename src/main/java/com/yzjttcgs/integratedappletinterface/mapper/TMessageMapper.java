package com.yzjttcgs.integratedappletinterface.mapper;

/**
* @author Miaoyu
* @description 针对表【t_message(短信表)】的数据库操作Mapper
* @createDate 2025-02-19 16:41:21
* @Entity com.yzjttcgs.integratedappletinterface.domain.po.TMessage
*/
public interface TMessageMapper {
    String selectLatestContentByPhone(String mobile);
}




