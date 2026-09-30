package com.codevictims.bayt.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class  AccountService {

    private final UserRepository userRepository;
    private final BuildingAccessRepository buildingAccessRepository;
    private final PersistenceSupport db;
    private final Access access;
    private final PasswordEncoder passwords;
    private final AuditService audit;

}
