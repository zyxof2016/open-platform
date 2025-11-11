package com.openplatform.auth.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openplatform.auth.infrastructure.dataobject.AuthTokenDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 认证令牌Mapper接口
 */
@Mapper
public interface AuthTokenMapper extends BaseMapper<AuthTokenDO> {
}