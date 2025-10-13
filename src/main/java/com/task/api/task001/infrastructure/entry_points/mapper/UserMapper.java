package com.task.api.task001.infrastructure.entry_points.mapper;

import com.task.api.task001.domain.model.UserTask;
import com.task.api.task001.infrastructure.entry_points.dto.Request.RequestUserTask;
import com.task.api.task001.infrastructure.entry_points.dto.Response.ResponseUserTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Mono;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserTask toDomain(RequestUserTask requestUserTask);


    ResponseUserTask toResponse(UserTask createdUser);
}
