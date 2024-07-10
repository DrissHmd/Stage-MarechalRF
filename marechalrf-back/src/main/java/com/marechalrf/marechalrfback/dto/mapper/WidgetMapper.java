package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.WidgetDto;
import com.marechalrf.marechalrfback.model.Widget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WidgetMapper {
    WidgetDto toDTO(Widget widget);
    Widget toEntity(WidgetDto widgetDTO);
}