package com.task.api.task001.infrastructure.entryPoints.mapper;

import com.task.api.task001.domain.model.UserTask;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestUserTask;
import com.task.api.task001.infrastructure.entryPoints.dto.Response.ResponseUserTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserTask toDomain(RequestUserTask requestUserTask);


    ResponseUserTask toResponse(UserTask createdUser);
}
