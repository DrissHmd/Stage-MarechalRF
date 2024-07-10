package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.DashboardDto;
import com.marechalrf.marechalrfback.model.Dashboard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WidgetMapper.class})
public interface DashboardMapper {
    @Mapping(target = "widgets", source = "widgets")
    DashboardDto toDTO(Dashboard dashboard);

    @Mapping(target = "widgets", source = "widgets")
    Dashboard toEntity(DashboardDto dashboardDTO);
}