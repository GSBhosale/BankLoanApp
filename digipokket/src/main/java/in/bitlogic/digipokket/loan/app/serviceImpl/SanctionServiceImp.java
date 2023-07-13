package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.SanctionLetter;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.repositary.SanctionRepository;
import in.bitlogic.digipokket.loan.app.service.SanctionService;

@Service
public class SanctionServiceImp implements SanctionService
{
    @Autowired
    SanctionRepository sanRepository;
    
    @Autowired
    CustomerRepository customerRepository;
	
	@Override
	public ByteArrayInputStream createPdf(int sanctionId) 
	{

	
     	
     	
	
	return null;
	}

	@Override
	public SanctionLetter sanctionLoan(SanctionLetter sanLetter, int customerId) {
		
	Optional<Customer> om=	customerRepository.findById(customerId);
	if(om.isPresent())
	{
		Customer customer=om.get();
		
		customer.setSanctionLetter(sanLetter);
	}
		
		return null;
	}

}
