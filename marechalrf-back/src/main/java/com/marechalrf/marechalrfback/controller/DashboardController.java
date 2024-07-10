package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.DashboardDto;
import com.marechalrf.marechalrfback.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public List<DashboardDto> getAllDashboards() {
        return dashboardService.getAllDashboards();
    }

    @GetMapping("/{id}")
    public DashboardDto getDashboardById(@PathVariable Long id) {
        return dashboardService.getDashboardById(id);
    }

    @PostMapping
    public DashboardDto createDashboard(@RequestBody DashboardDto dashboardDto) {
        return dashboardService.createDashboard(dashboardDto);
    }

    @PutMapping("/{id}")
    public DashboardDto updateDashboard(@PathVariable Long id, @RequestBody DashboardDto dashboardDto) {
        return dashboardService.updateDashboard(id, dashboardDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDashboard(@PathVariable Long id) {
        dashboardService.deleteDashboard(id);
    }
}