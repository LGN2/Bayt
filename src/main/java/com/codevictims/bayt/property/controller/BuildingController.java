package com.codevictims.bayt.property.controller;

import com.codevictims.bayt.common.mapper.RequestMapper;
import com.codevictims.bayt.property.dto.request.BuildingRequest;
import com.codevictims.bayt.property.dto.response.BuildingResponse;
import com.codevictims.bayt.property.dto.response.BuildingSummaryResponse;
import com.codevictims.bayt.property.mapper.BuildingMapper;
import com.codevictims.bayt.property.service.PropertyService;
import com.codevictims.bayt.security.service.Access;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BuildingController {
    private final PropertyService s;
    private final Access access;

    public BuildingController(PropertyService s, Access access) {
        this.s = s;
        this.access = access;
    }

    @GetMapping("/buildings")
    public List<?> buildings() {
        var all = s.buildings();
        if (Set.of("OWNER", "MANAGER").contains(access.user().role))
            return all.stream().map(BuildingMapper::toResponse).toList();
        return all.stream()
                .map(b -> new BuildingSummaryResponse(b.id, b.name, b.wilayat, b.address))
                .toList();
    }

    @PostMapping("/buildings")
    public BuildingResponse building(@jakarta.validation.Valid @RequestBody BuildingRequest b) {
        return BuildingMapper.toResponse(s.building(RequestMapper.toInput(b), null));
    }

    @PutMapping("/buildings/{id}")
    public BuildingResponse building(
            @PathVariable Long id, @jakarta.validation.Valid @RequestBody BuildingRequest b) {
        return BuildingMapper.toResponse(s.building(RequestMapper.toInput(b), id));
    }
}
