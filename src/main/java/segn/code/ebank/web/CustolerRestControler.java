package segn.code.ebank.web;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//le controler utilise toujours la couche serve
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import segn.code.ebank.dtos.CustomerDTO;
import segn.code.ebank.entities.Customer;
import segn.code.ebank.exception.CustomerNotFoundException;
import segn.code.ebank.service.BankAccountService;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustolerRestControler {
private BankAccountService bankAccountService;

@PostMapping("customers")
public CustomerDTO saveCustomerDTO1(@RequestBody CustomerDTO customerDTO)
{
	return bankAccountService.saveCustomerDTO(customerDTO);
}


@GetMapping("/customers")
public List<CustomerDTO> customers(){
	
return bankAccountService.listCustomerDTO();

}

@GetMapping("/customers/search")
public List<CustomerDTO> searchCustomers(@RequestParam(name="keyword",defaultValue = "")String keyword ){
	
return bankAccountService.searchCustomers("%"+keyword+"%");

}



//trouve un client specifique 
@GetMapping("/customers/{id}")
public CustomerDTO getCustomerDTO(@PathVariable(name="id") Long customerId ) throws CustomerNotFoundException 
{
	return bankAccountService.getCustomerDTO(customerId);
}



@PutMapping("/customers/{customerId}")
public CustomerDTO updateCustomer(@PathVariable Long customerId, CustomerDTO customerDTO) 
{
	customerDTO.setId(customerId);
	
	return bankAccountService.updateCustomerDTO(customerDTO);
}

@DeleteMapping("/customers/{id}")
public void deleteCustomer(@PathVariable(name="id") Long Id)
{
	bankAccountService.deleteCustomer(Id);
}
}
