package com.example.hotel_reservation.controller;

import com.example.hotel_reservation.HotelReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hotel_reservation.model.Hotel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelReservationController implements ErrorController {

    @Autowired
    private HotelReservationRepository hoteldao;

    @GetMapping("/hotelsList")
    public List<Hotel> listAllHotels() {
        List<Hotel> hotelList = hoteldao.findAll();
        return hotelList;
    }

    @GetMapping("/")
    public String home(){
        return "<h1>Welcome User</h1> <h2>The following functionalities are available:</h2> <ul><li><a href='/hotelsList'>Get List of hotels</a></li> <li>link for post request(in postman): /addHotel</li> <li>To filter based on price and name use: '/hotel/name?name=your_value' For ex: <a href='/hotel/price?price=200'>price filter</a></li></ul>";
    }

    @GetMapping("/hotel/name")
    public ResponseEntity<List<Hotel>> getHotelByName(@RequestParam String name) {
        return new ResponseEntity<List<Hotel>>(hoteldao.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/hotel/price")
    public ResponseEntity<List<Hotel>> getHotelByPrice(@RequestParam Integer price) {
        return new ResponseEntity<List<Hotel>>(hoteldao.findByPrice(price), HttpStatus.OK);
    }

    @RequestMapping(value= "/addHotel",method= RequestMethod.POST,consumes="application/json")
    public String reserveHotel(@RequestBody Hotel hotel) {
        hoteldao.save(hotel);
        return "Hotel details have been added for: "+hotel.getName();
    }


    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div> <a href='/'>Goto Home Page</a><body></html>",
                statusCode, exception==null?"N/A":exception.getMessage());
    }

}
