package com.example.hotel_reservation;
import com.example.hotel_reservation.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelReservationRepository extends JpaRepository<Hotel, Integer>{

    List<Hotel> findByName(String name);

    List<Hotel> findByPrice(Integer price);
}

