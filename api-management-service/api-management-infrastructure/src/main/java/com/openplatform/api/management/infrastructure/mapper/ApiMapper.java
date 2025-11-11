package com.openplatform.api.management.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openplatform.api.management.infrastructure.dataobject.ApiDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * API Mapper接口
 */
@Mapper
public interface ApiMapper extends BaseMapper<ApiDO> {
}