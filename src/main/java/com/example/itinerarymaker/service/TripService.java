package com.example.itinerarymaker.service;

import com.example.itinerarymaker.domain.Trip;
import com.example.itinerarymaker.domain.User;
import com.example.itinerarymaker.model.dto.FutureTripDTO;
import com.example.itinerarymaker.model.dto.TripDTO;
import com.example.itinerarymaker.model.dto.TripUserDTO;

import java.util.List;

public interface TripService {

    public Trip addTrip(Trip trip);

    public List<FutureTripDTO> getUserTrips(long id);

    public List<TripDTO> searchUserTrips(long id, String searchParam);

    public List<TripUserDTO> getAllTrips();

    public List<TripUserDTO> searchAllTrips(String searchParam);

    public String getNextMonthsTravelPlan(User user);

}
