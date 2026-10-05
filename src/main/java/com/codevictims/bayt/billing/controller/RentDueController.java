package com.property.billing.controller;

import com.property.billing.response.RentDueResponse;
import com.property.billing.service.RentScheduleService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/billing/rent-dues")
public class RentDueController {
    private final RentScheduleService rentScheduleService;

    public RentDueController(RentScheduleService rentScheduleService) {
        this.rentScheduleService = rentScheduleService;
    }

    @PostMapping
    public RentDueResponse create(@RequestParam Long leaseId,
                                  @RequestParam LocalDate dueDate,
                                  @RequestParam BigDecimal amount) {
        return rentScheduleService.createRentDue(leaseId, dueDate, amount);
    }

    @GetMapping
    public List<RentDueResponse> getAll() {
        return rentScheduleService.getAll();
    }

    @GetMapping("/lease/{leaseId}")
    public List<RentDueResponse> getByLease(@PathVariable Long leaseId) {
        return rentScheduleService.getByLease(leaseId);
    }
}
