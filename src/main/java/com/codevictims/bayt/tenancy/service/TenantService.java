package com.codevictims.bayt.tenancy.service;

import com.codevictims.bayt.common.audit.service.AuditService;
import com.codevictims.bayt.common.dto.Input;
import com.codevictims.bayt.common.exception.ApiException;
import com.codevictims.bayt.common.repository.PersistenceSupport;
import com.codevictims.bayt.property.service.PropertyService;
import com.codevictims.bayt.security.authorization.AccessService;
import com.codevictims.bayt.tenancy.entity.Tenant;
import com.codevictims.bayt.tenancy.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(
        isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED
)
public class TenantService {

    private final TenantRepository tenantRepository;
    private final PersistenceSupport db;
    private final AccessService access;
    private final AuditService audit;
    private final PropertyService properties;

    public TenantService(
            PersistenceSupport db,
            AccessService access,
            AuditService audit,
            PropertyService properties,
            TenantRepository tenantRepository) {

        this.tenantRepository = tenantRepository;
        this.db = db;
        this.access = access;
        this.audit = audit;
        this.properties = properties;
    }

    // Tenant Listing
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

    // Tenant Create / Update
    public Tenant tenant(Input in, Long id) {

        Long buildingId = in.id("buildingId");

        access.manage(buildingId);

        Tenant tenant = id == null
                ? new Tenant()
                : db.get(Tenant.class, id);

        if (id != null && !tenant.buildingId.equals(buildingId)) {
            throw ApiException.invalid("INVALID_INPUT");
        }

        tenant.buildingId = buildingId;

        tenant.name = in.text("name", 255);

        tenant.kind = in.choice(
                "kind",
                "PERSON",
                "COMPANY"
        );

        tenant.email = in.optional("email");

        tenant.phone = in.text("phone", 80);

        tenant.emergencyContact =
                in.optional("emergencyContact");

        Long accountId = in.nullableId("accountId");

        if (accountId != null) {
            access.portfolioUser(
                    accountId,
                    buildingId,
                    "TENANT"
            );
        }

        /*
         * Changing the account assigned to an existing tenant
         * is allowed only for the owner.
         */
        if (id != null
                && !Objects.equals(accountId, tenant.accountId)) {

            access.owner(buildingId);
        }

        tenant.accountId = accountId;

        db.save(tenant);

        audit.add(
                buildingId,
                "TENANT",
                tenant.id,
                id == null ? "CREATED" : "UPDATED",
                ""
        );

        return tenant;
    }
}