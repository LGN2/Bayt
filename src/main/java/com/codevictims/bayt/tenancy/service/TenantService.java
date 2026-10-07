package com.codevictims.bayt.tenancy.service;

import com.codevictims.bayt.common.dto.Input;
import com.codevictims.bayt.common.exception.ApiException;
import com.codevictims.bayt.common.repository.PersistenceSupport;
import com.codevictims.bayt.common.service.AuditService;
import com.codevictims.bayt.property.service.PropertyService;
import com.codevictims.bayt.security.service.Access;
import com.codevictims.bayt.tenancy.entity.Tenant;
import com.codevictims.bayt.tenancy.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class TenantService {

    private final TenantRepository tenantRepository;
    private final PersistenceSupport db;
    private final Access access;
    private final AuditService audit;
    private final PropertyService properties;
    public TenantService(
            PersistenceSupport db,
            Access access,
            AuditService audit,
            PropertyService properties,
            TenantRepository tenantRepository) {

        this.tenantRepository = tenantRepository;
        this.db = db;
        this.access = access;
        this.audit = audit;
        this.properties = properties;
    }

    public Tenant get(Long id) {
        return access.tenant(id);
    }

    public List<Tenant> tenants(Long building) {
        access.role("OWNER", "MANAGER", "TENANT");

        var ids = properties.scope(building);

        if (ids.isEmpty()) {
            return List.of();
        }

        if (access.user().role.equals("TENANT")) {
            return tenantRepository.findForAccountInBuildings(
                    access.user().id,
                    ids
            );
        }

        return tenantRepository.findInBuildings(ids);
    }

    public Tenant tenant(Input in, Long id) {
        Long buildingId = in.id("buildingId");

        access.manage(buildingId);

        Tenant tenant = id == null
                ? new Tenant()
                : db.get(Tenant.class, id);

        if (id != null
                && !Objects.equals(tenant.getBuildingId(), buildingId)) {
            throw ApiException.invalid("INVALID_INPUT");
        }

        tenant.setBuildingId(buildingId);
        tenant.setName(in.text("name", 255));
        tenant.setKind(in.choice("kind", "PERSON", "COMPANY"));
        tenant.setEmail(in.optional("email"));
        tenant.setPhone(in.text("phone", 80));
        tenant.setEmergencyContact(in.optional("emergencyContact"));

        Long accountId = in.nullableId("accountId");

        if (accountId != null) {
            access.portfolioUser(accountId, buildingId, "TENANT");
        }

        if (id != null
                && !Objects.equals(accountId, tenant.getAccountId())) {
            access.owner(buildingId);
        }

        tenant.setAccountId(accountId);

        db.save(tenant);

        audit.add(
                buildingId,
                "TENANT",
                tenant.getId(),
                id == null ? "CREATED" : "UPDATED",
                ""
        );

        return tenant;
    }
}