package com.rest.webservices.restful_web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

// class to use filtering logic to the API's response 
@RestController		// injecting HTTP methods to the class
public class FilterController {
	
	// filtering one of the values out
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		
		// -- second iteration - dynamic filtering --
		SomeBean someBean = new SomeBean("valueA", "valueB", "valueC");	// instancing 'SomeBean' so it may be wrapped by 'MappingJacksonValue'
		
		return filterBuilder(someBean);
		// -- / --
		
		// -- first iteration - static filtering --
		//return new SomeBean("value1", "value2", "value3"); // just sending the values to the API
	}
	
	// filtering a cell from a list 
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {	// filtering-out 'field3'
		
		// -- second iteration - dynamic filtering --
		
		// instancing the list to be filtered
		List<SomeBean> list = Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6")
		);
		
		return filterBuilder(list);
		// -- / --
		
		// -- first iteration - static filtering --
		/*return Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6")
		);*/	// just sending the values to the API
		// -- / --
	}
	
	// setting a filter builder method so its not duplicated for every new filter instance
	private MappingJacksonValue filterBuilder(Object obj) {
		// wrapping the list object to receive the filter configurations
		MappingJacksonValue mappingJacksonValues = new MappingJacksonValue(obj);

		// configuring the filter itself
		// OBS: parameters should be the keys, not the values
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2"); // removing every value except the defined ones

		// defining the filters' list to be used
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		// adding the filters' list to the wrapped object
		mappingJacksonValues.setFilters(filters);
		
		return mappingJacksonValues;
	}
}
