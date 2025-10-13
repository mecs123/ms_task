package com.task.api.task001.infrastructure.entry_points;

import com.task.api.task001.infrastructure.entry_points.handler.ManagerTaskHandler;
import com.task.api.task001.infrastructure.entry_points.handler.UserTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> route(
            UserTaskHandler userTaskHandler,
            ManagerTaskHandler managerTaskHandler

    ) {
        return RouterFunctions.route()
                .route(RequestPredicates.POST("/user-task"), userTaskHandler::createUser)

                .route(RequestPredicates.POST("/manager-task"), managerTaskHandler::createTask)
                .build();
    }
}
