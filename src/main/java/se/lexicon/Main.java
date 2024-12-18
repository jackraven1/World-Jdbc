package se.lexicon;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.impl.CityDaoImpl;
import se.lexicon.model.City;
import se.lexicon.util.SQLConnection;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CityDao cityDao = new CityDaoImpl();
        City city = cityDao.findById(60);
        System.out.println(city);
        List<City> citiesByCode = cityDao.findByCode("USA");
        citiesByCode.forEach(System.out::println);
        List<City> citiesByName = cityDao.findByName("sto");
        citiesByName.forEach(System.out::println);
        List<City> allCities = cityDao.findAll();
        allCities.forEach(System.out::println);
        City newCity = new City("Jean", "USA", "jari", 12345);
        cityDao.add(newCity);
        System.out.println("Added: " + newCity);
        newCity.setPopulation(54565);
        cityDao.update(newCity);
        System.out.println("Updated: " + newCity);
        cityDao.delete(newCity);
        System.out.println("Deleted: " + newCity);
    }

}
