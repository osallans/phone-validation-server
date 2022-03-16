package phone.validation.server.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import phone.validation.server.dto.CustomerSearchDto;
import phone.validation.server.models.Country;
import phone.validation.server.service.CountryService;
import phone.validation.server.service.CustomerService;
@CrossOrigin(origins = "*")
@RestController
public class APIController {
    
    private final String PROCESS_COMPLETED="Phone numbers are verified and ready to be queried";
    private final String SITE_UP="Server is up !";
    private final String SITE_DOWN="Server is down !";
    
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CountryService countryService;
    
    //////////////////////////////////////////////////////////////////
    //Check server health
    @CrossOrigin
    @GetMapping("/api/health")
    public String getUrlStatusMessage()
    {
        String returnMessage="";
        try {
           returnMessage=SITE_UP;
        } catch (Exception e) {
            returnMessage=SITE_DOWN;
            e.printStackTrace();
        }
        return returnMessage;
    }
    //////////////////////////////////////////////////////////////////
    @CrossOrigin
    @GetMapping("/api/customers/verifyphone")
    //Processing db records to verify and save country & state for customers phone numbers
    public ResponseEntity<Map<String, Object>> verify()
    {
        try {
            
                customerService.processData();
                Map<String, Object> response = new HashMap<>();
                response.put("message", PROCESS_COMPLETED);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //////////////////////////////////////////////////////////////////
    //Getting the list of customers filtered ,  paginated and sorted according to user input
    @CrossOrigin
    @PostMapping("/api/customers")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCustomers(@RequestBody CustomerSearchDto customerSearchDto) {
    try 
    {

        Map<String, Object> response = customerService.findAll(customerSearchDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
      } 
      catch (Exception e) 
      {
          e.printStackTrace();
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    /////////////////////////////////////////////////////////////////
    //Getting customers list to enable users filter with in the FE (This function is broken due to external issue in Pageable)
    @CrossOrigin
    @PostMapping("/api/customers/nopaging")
    public ResponseEntity<Map<String, Object>> getCustomersBroken(@RequestBody CustomerSearchDto customerSearchDto)
    {
        try 
        {
            Map<String, Object> response = customerService.findAllCustomersWithBrokenPagination(customerSearchDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /////////////////////////////////////////////////////////////////
    //Getting countries list to enable users filter with in the FE
    @CrossOrigin
    @GetMapping("/api/countries")
    public ResponseEntity<Map<String, Object>> getCountries()
    {
        try 
        {
            List<Country> countries=countryService.findAll();
            Map<String, Object> response = new HashMap<>();
            response.put("data", countries);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
