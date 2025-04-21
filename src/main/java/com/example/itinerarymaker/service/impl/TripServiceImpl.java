package com.example.itinerarymaker.service.impl;

import com.example.itinerarymaker.domain.Trip;
import com.example.itinerarymaker.domain.User;
import com.example.itinerarymaker.model.dto.FutureTripDTO;
import com.example.itinerarymaker.model.dto.TripDTO;
import com.example.itinerarymaker.model.dto.TripUserDTO;
import com.example.itinerarymaker.repository.TripRepository;
import com.example.itinerarymaker.service.TripService;
import com.example.itinerarymaker.util.TravelPlanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip addTrip(Trip trip) {
        tripRepository.insertTrip(trip.getDestination(), trip.getStartDate(), trip.getEndDate(), trip.getComment(), trip.getUser().getId());
        return trip;
    }

    @Override
    public List<FutureTripDTO> getUserTrips(long userId) {
        List<Object[]> tripsAndDaysUntil = tripRepository.getUserTripsAndDaysUntil(userId);
        return tripsAndDaysUntil
                .stream()
                .map(FutureTripDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripDTO> searchUserTrips(long userId, String searchParam) {
        List<Trip> trips = tripRepository.searchUserTrips(userId, searchParam);
        return trips
                .stream()
                .map(TripDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripUserDTO> getAllTrips() {
        List<Trip> tripsAndDaysUntil = tripRepository.getAllTrips();
        return tripsAndDaysUntil
                .stream()
                .map(TripUserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripUserDTO> searchAllTrips(String searchParam) {
        List<Trip> trips = tripRepository.searchAllTrips(searchParam);
        return trips
                .stream()
                .map(TripUserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getNextMonthsTravelPlan(User user) {
        LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
        List<Trip> trips = tripRepository.getTripsFromMonthAndYear(user.getId(), nextMonthDate.getMonthValue(), nextMonthDate.getYear());
        TravelPlanBuilder planBuilder = new TravelPlanBuilder();
        return planBuilder
                .withUsername(user.getUsername())
                .withMonth(nextMonthDate.getMonth())
                .withTrips(trips)
                .build();
    }

}
