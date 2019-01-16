package com.example.router;

import com.example.handle.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouterConfig {

    @Autowired
    private TimeHandler timeHandler;

    /**
     * RouterFunction: 路由
     * 相当于@RequestMapping。将不同的请求路由到不同的方法服务上
     *
     * 可以存在多个router和handler。但是bean的名称需不同（这里bean的名称使用的是方法名）
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return RouterFunctions.route(GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(GET("/getStus"), timeHandler::getStus);
    }
}