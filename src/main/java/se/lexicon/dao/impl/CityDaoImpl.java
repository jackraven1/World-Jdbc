package se.lexicon.dao.impl;

import se.lexicon.Queries.CityQueries;
import se.lexicon.dao.CityDao;
import se.lexicon.model.City;
import se.lexicon.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao {
    @Override
    public City findById(int id) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CityQueries.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new City(
                        result.getInt("ID"),
                        result.getString("Name"),
                        result.getString("CountryCode"),
                        result.getString("District"),
                        result.getInt("Population")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cities = new ArrayList<>();
        try (Connection conn = SQLConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(CityQueries.FIND_BY_CODE)) {
            statement.setString(1, code);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                cities.add(new City(
                        result.getInt("ID"),
                        result.getString("Name"),
                        result.getString("CountryCode"),
                        result.getString("District"),
                        result.getInt("Population")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cities = new ArrayList<>();
        try (Connection conn = SQLConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(CityQueries.FIND_BY_NAME)) {
            statement.setString(1, "%"+name+"%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                cities.add(new City(
                        result.getInt("ID"),
                        result.getString("Name"),
                        result.getString("CountryCode"),
                        result.getString("District"),
                        result.getInt("Population")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;

    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = SQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(CityQueries.FIND_ALL)) {
            while (result.next()) {
                cities.add(new City(
                        result.getInt("ID"),
                        result.getString("Name"),
                        result.getString("CountryCode"),
                        result.getString("District"),
                        result.getInt("Population")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }


    @Override
    public City add(City city) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CityQueries.ADD_CITY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3, city.getDistrict());
            statement.setInt(4, city.getPopulation());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    city.setId(result.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City update(City city) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CityQueries.UPDATE_CITY)) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3, city.getDistrict());
            statement.setInt(4, city.getPopulation());
            statement.setInt(5, city.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }


    @Override
    public int delete(City city) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CityQueries.DELETE_CITY)) {
            statement.setInt(1, city.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
