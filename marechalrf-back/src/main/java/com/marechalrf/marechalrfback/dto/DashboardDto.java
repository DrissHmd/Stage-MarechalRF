package com.marechalrf.marechalrfback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DashboardDto {
    private Long id;
    private String name;
    private String description;
    private List<WidgetDto> widgets;
}
