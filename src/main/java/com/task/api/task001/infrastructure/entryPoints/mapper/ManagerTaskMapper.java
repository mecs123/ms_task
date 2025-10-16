package com.task.api.task001.infrastructure.entryPoints.mapper;

import com.task.api.task001.aplication.dto.ManagerTaskDTO;
import com.task.api.task001.domain.model.ManagerTask;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestManagerTask;
import com.task.api.task001.infrastructure.entryPoints.dto.Response.ResponseManagerTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerTaskMapper {

    ManagerTaskMapper INSTANCE = Mappers.getMapper(ManagerTaskMapper.class);

    ManagerTaskDTO toDomain(RequestManagerTask requestManagerTask);

    ResponseManagerTask toResponse(ManagerTask createdManagerTask);
}
