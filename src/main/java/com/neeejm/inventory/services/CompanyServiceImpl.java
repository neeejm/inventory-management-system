package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Company;
import com.neeejm.inventory.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends CrudServiceImpl<CompanyRepository, Company>
        implements CompanyService {

    @Autowired
    protected CompanyServiceImpl(CompanyRepository repository) {
        super(repository);
    }
}
