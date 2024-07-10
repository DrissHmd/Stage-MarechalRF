package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.dto.DashboardDto;
import com.marechalrf.marechalrfback.dto.mapper.DashboardMapper;
import com.marechalrf.marechalrfback.model.Dashboard;
import com.marechalrf.marechalrfback.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private DashboardMapper dashboardMapper;

    public List<DashboardDto> getAllDashboards() {
        return dashboardRepository.findAll().stream()
                .map(dashboardMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DashboardDto getDashboardById(Long id) {
        return dashboardRepository.findById(id)
                .map(dashboardMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Dashboard not found"));
    }

    public DashboardDto createDashboard(DashboardDto dashboardDTO) {
        Dashboard dashboard = dashboardMapper.toEntity(dashboardDTO);
        dashboard = dashboardRepository.save(dashboard);
        return dashboardMapper.toDTO(dashboard);
    }

    public DashboardDto updateDashboard(Long id, DashboardDto dashboardDTO) {
        Dashboard dashboard = dashboardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dashboard not found"));
        dashboard.setName(dashboardDTO.getName());
        dashboard.setDescription(dashboardDTO.getDescription());
        dashboard.setWidgets(dashboardMapper.toEntity(dashboardDTO).getWidgets());
        dashboard = dashboardRepository.save(dashboard);
        return dashboardMapper.toDTO(dashboard);
    }

    public void deleteDashboard(Long id) {
        dashboardRepository.deleteById(id);
    }
}