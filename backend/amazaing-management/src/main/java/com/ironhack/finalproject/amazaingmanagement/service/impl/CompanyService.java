package com.ironhack.finalproject.amazaingmanagement.service.impl;

import com.ironhack.finalproject.amazaingmanagement.controller.dto.CompanyDTO;
import com.ironhack.finalproject.amazaingmanagement.model.Company;
import com.ironhack.finalproject.amazaingmanagement.repository.BusinessModelRepository;
import com.ironhack.finalproject.amazaingmanagement.repository.CompanyRepository;
import com.ironhack.finalproject.amazaingmanagement.repository.PlayerRepository;
import com.ironhack.finalproject.amazaingmanagement.service.interfaces.IBusinessModelService;
import com.ironhack.finalproject.amazaingmanagement.service.interfaces.ICompanyService;
import com.ironhack.finalproject.amazaingmanagement.service.interfaces.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private IBusinessModelService businessModelService;

	@Autowired
	private IPlayerService playerService;

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyById(Long id) {
		Optional<Company> company = companyRepository.findById(id);

		if(company.isPresent()) {
			return company.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not Found");
		}
	}

	@Override
	public Company storeCompany(CompanyDTO companyDTO) {
		Company company = new Company(
				companyDTO.getCompanyName(),
				companyDTO.getRevenue(),
				companyDTO.getMaintenance(),
				companyDTO.getEmployeesNumber(),
				companyDTO.getAccidentRiskIndex(),
				businessModelService.getBusinessModelById(companyDTO.getBusinessModel()),
				playerService.getPlayerById(companyDTO.getPlayer())
		);

		return companyRepository.save(company);
	}

	@Override
	public void updateCompany(Long id, CompanyDTO companyDTO) {
		Company company;

		getCompanyById(id);
		company = new Company(
				companyDTO.getCompanyName(),
				companyDTO.getRevenue(),
				companyDTO.getMaintenance(),
				companyDTO.getEmployeesNumber(),
				companyDTO.getAccidentRiskIndex(),
				businessModelService.getBusinessModelById(companyDTO.getBusinessModel()),
				playerService.getPlayerById(companyDTO.getPlayer())
		);
		company.setCompanyId(id);
		companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id) {

		getCompanyById(id);
		companyRepository.deleteById(id);
	}

}