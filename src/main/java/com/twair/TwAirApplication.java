package com.twair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class TwAirApplication {
	@RequestMapping("/")
	public String home(Model model) {
        model.addAttribute("locations", DataSource.instance().fetchLocations());
		return "FlightSearch";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute(value="searchForm") SearchForm searchForm, Model model) throws Exception {
		model.addAttribute("locations", DataSource.instance().fetchLocations());
		try {
			FlightSearch matchingFlights = DataSource.instance().fetchFlights().byLocation(searchForm.getFrom(), searchForm.getTo());
			matchingFlights = matchingFlights.byDeparture(searchForm.getDepartureDate());
			matchingFlights = matchingFlights.byClassType(searchForm.getClassType());
			matchingFlights = matchingFlights.byAvailableSeats(searchForm.getClassType(), searchForm.getNumberSeats());
			List<FlightWithQueryInfo> matchingFlightWithQueryInfo = new ArrayList<>();
			for (Flight flight : matchingFlights.getFlightList()) {
				matchingFlightWithQueryInfo.add(new FlightWithQueryInfo(flight, searchForm.getNumberSeats
								(), searchForm.getClassType()));
			}
			model.addAttribute("flights_with_query_info", matchingFlightWithQueryInfo);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flights", new ArrayList());
		}
		return "FlightSearch";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TwAirApplication.class, args);
	}
}
