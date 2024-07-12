package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface ParkingService {
    Result getMyParking(int userId);
    Result bindParking(int parkingId, String licensePlate);
}
