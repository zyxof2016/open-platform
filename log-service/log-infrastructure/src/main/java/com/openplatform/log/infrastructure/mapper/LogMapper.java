package com.openplatform.log.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openplatform.log.infrastructure.dataobject.LogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志Mapper接口
 */
@Mapper
public interface LogMapper extends BaseMapper<LogDO> {
}