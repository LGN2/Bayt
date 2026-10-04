package com.codevictims.bayt.tenancy.service;

import com.codevictims.bayt.common.exception.ApiException;
import com.codevictims.bayt.tenancy.entity.Lead;
import com.codevictims.bayt.tenancy.repository.LeadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public List<Lead> getAll() {
        return leadRepository.findAll();
    }

    public Lead getById(Long id) {
        return leadRepository.findById(id)
                .orElseThrow(ApiException::missing);
    }

    public Lead create(Lead lead) {
        return leadRepository.save(lead);
    }

    public Lead update(Long id, Lead updatedLead) {

        Lead existing = getById(id);

        existing.setBuildingId(updatedLead.getBuildingId());
        existing.setUnitId(updatedLead.getUnitId());
        existing.setName(updatedLead.getName());
        existing.setPhone(updatedLead.getPhone());
        existing.setStatus(updatedLead.getStatus());
        existing.setViewingAt(updatedLead.getViewingAt());
        existing.setFollowUpDate(updatedLead.getFollowUpDate());
        existing.setNotes(updatedLead.getNotes());

        return leadRepository.save(existing);
    }

    public void delete(Long id) {
        Lead lead = getById(id);
        leadRepository.delete(lead);
    }
}