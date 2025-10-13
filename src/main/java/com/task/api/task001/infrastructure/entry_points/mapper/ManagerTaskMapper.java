package com.task.api.task001.infrastructure.entry_points.mapper;

import com.task.api.task001.aplication.dto.ManagerTaskDTO;
import com.task.api.task001.domain.model.ManagerTask;
import com.task.api.task001.domain.model.UserTask;
import com.task.api.task001.infrastructure.entry_points.dto.Request.RequestManagerTask;
import com.task.api.task001.infrastructure.entry_points.dto.Request.RequestUserTask;
import com.task.api.task001.infrastructure.entry_points.dto.Response.ResponseManagerTask;
import com.task.api.task001.infrastructure.entry_points.dto.Response.ResponseUserTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerTaskMapper {

    ManagerTaskMapper INSTANCE = Mappers.getMapper(ManagerTaskMapper.class);

    ManagerTaskDTO toDomain(RequestManagerTask requestManagerTask);

    ResponseManagerTask toResponse(ManagerTask createdManagerTask);
}
