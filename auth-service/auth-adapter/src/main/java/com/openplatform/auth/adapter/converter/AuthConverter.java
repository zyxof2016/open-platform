package com.openplatform.auth.adapter.converter;

/**
 * 认证转换器
 */
public class AuthConverter {
    
    /**
     * 将应用层登录命令转换为领域层登录命令
     * @param appCommand 应用层登录命令
     * @return 领域层登录命令
     */
    public static com.openplatform.auth.domain.command.LoginCommand toDomain(com.openplatform.auth.app.command.LoginCommand appCommand) {
        com.openplatform.auth.domain.command.LoginCommand domainCommand = new com.openplatform.auth.domain.command.LoginCommand();
        domainCommand.setUsername(appCommand.getUsername());
        domainCommand.setPassword(appCommand.getPassword());
        return domainCommand;
    }
    
    /**
     * 将领域层认证响应转换为应用层认证响应
     * @param domainResponse 领域层认证响应
     * @return 应用层认证响应
     */
    public static com.openplatform.auth.app.dto.AuthResponseDTO toApp(com.openplatform.auth.domain.dto.AuthResponseDTO domainResponse) {
        com.openplatform.auth.app.dto.AuthResponseDTO appResponse = new com.openplatform.auth.app.dto.AuthResponseDTO();
        appResponse.setToken(domainResponse.getToken());
        appResponse.setRefreshToken(domainResponse.getRefreshToken());
        appResponse.setTokenType(domainResponse.getTokenType());
        appResponse.setExpiresIn(domainResponse.getExpiresIn());
        appResponse.setScope(domainResponse.getScope());
        appResponse.setUserId(domainResponse.getUserId());
        appResponse.setUsername(domainResponse.getUsername());
        return appResponse;
    }
}