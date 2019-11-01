package com.cactus.dao;

import com.cactus.entity.Bike;
import com.cactus.entity.Cactus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.cactus.entity.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    //create datasource
    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //create new employee
    @Override
    public void newEmployee(User user) {
        String sql = "INSERT INTO users (name, employee, date1, date2)"
                + " VALUES ('', ?, '', '')";
        jdbcTemplate.update(sql, user.getEmployee());
    }

    //give bike to an employee
    @Override
    public void saveBikeEmployee(User user) {
        int userId = user.getId();
        //checks current bike asociated with employee
        String currentBike = "SELECT name FROM users WHERE id=" + userId;
        currentBike = String.valueOf(jdbcTemplate.queryForList(currentBike));
        currentBike = currentBike.replace("[{name=", "").replace("}]", "");
        //date format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("Current bike equals: " + currentBike);
        try {
            //check if second date is not before first date
            Date newDate1 = format.parse(user.getDate1());
            Date newDate2 = format.parse(user.getDate2());
            long d1 = newDate1.getTime();
            long d2 = newDate2.getTime();
            if (d1 > d2) {
            } else {
                //checks if one has selected different bike for employee
                //update list
                if (!user.getName().equals(currentBike)) {
                    System.out.println("Current bike: " + currentBike);
                    String sql = "UPDATE users SET name=?, date1=?, date2=? WHERE id=?";
                    jdbcTemplate.update(sql, user.getName(),
                            user.getDate1(), user.getDate2(), user.getId());
                    String sql2 = "UPDATE elbikes SET available=1 WHERE bikename=?";
                    jdbcTemplate.update(sql2, currentBike);
                    String sql3 = "UPDATE elbikes SET available=0 WHERE bikename=?";
                    jdbcTemplate.update(sql3, user.getName());
                } else {
                    String sql = "UPDATE users SET name=?, date1=?, date2=? WHERE id=?";
                    jdbcTemplate.update(sql, user.getName(),
                            user.getDate1(), user.getDate2(), user.getId());
                    String sql2 = "UPDATE elbikes SET available=0 WHERE bikename=?";
                    jdbcTemplate.update(sql2, user.getName());
                }
            }
        } catch (Exception e) {
        }
    }

    //remove bike from employee
    @Override
    public void removeBike(int userId) {
        String biken = "SELECT name FROM users WHERE id=" + userId;
        biken = String.valueOf(jdbcTemplate.queryForList(biken));
        biken = biken.replace("[{name=", "").replace("}]", "");
        String sql2 = "UPDATE elbikes SET available=1 WHERE bikename=?";
        jdbcTemplate.update(sql2, biken);
        String sql = "UPDATE users SET name='', date1='', date2='' WHERE id=" + userId;
        jdbcTemplate.update(sql);
    }

    //save or update bike info
    @Override
    public void saveOrUpdateBike(Bike bike) {
        if (bike.getId() > 0) {
            // update
            String sql = "UPDATE elbikes SET bikename=?, status=?, available=? WHERE id=?";
            jdbcTemplate.update(sql, bike.getBikename(), bike.getStatus(), bike.getAvailable(), bike.getId());
        } else {
            // insert
            String sql = "INSERT INTO elbikes (bikename, status, available)"
                    + " VALUES (?, ?, 1)";
            jdbcTemplate.update(sql, bike.getBikename(), bike.getStatus());
        }
    }

    //delete employee
    @Override
    public void delete(int userId) {
        String biken = "SELECT name FROM users WHERE id=" + userId;
        biken = String.valueOf(jdbcTemplate.queryForList(biken));
        biken = biken.replace("[{name=", "").replace("}]", "");
        String sql2 = "UPDATE elbikes SET available=1 WHERE bikename=?";
        jdbcTemplate.update(sql2, biken);
        String sql = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(sql, userId);
    }

    //delete bike
    @Override
    public void deleteBike(int bikeId) {
        String sql = "DELETE FROM elbikes WHERE id=?";
        jdbcTemplate.update(sql, bikeId);
    }

    //create and order employee/bike list
    @Override
    public List<User> list() {
        String sql = "SELECT * FROM users ORDER BY date2 DESC";
        List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User aUser = new User();
                aUser.setId(rs.getInt("id"));
                aUser.setName(rs.getString("name"));
                aUser.setEmployee(rs.getString("employee"));
                aUser.setDate1(rs.getString("date1"));
                aUser.setDate2(rs.getString("date2"));
                return aUser;
            }
        });
        return listUser;
    }

    //displays employee in employee dropdown list
    @Override
    public List<User> optionUser() {
        String sql = "SELECT * FROM users";
        List<User> optionUser = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User oUser = new User();
                oUser.setEmployee(rs.getString("employee"));
                return oUser;
            }
        });
        return optionUser;
    }

    //creates list with all bikes
    @Override
    public List<Bike> listBike() {
        String sql = "SELECT * FROM elbikes ORDER BY status DESC, available DESC";
        List<Bike> listBike = jdbcTemplate.query(sql, new RowMapper<Bike>() {
            @Override
            public Bike mapRow(ResultSet rs2, int rowNum) throws SQLException {
                Bike abike = new Bike();
                abike.setId(rs2.getInt("id"));
                abike.setBikename(rs2.getString("bikename"));
                abike.setStatus(rs2.getBoolean("status"));
                abike.setAvailable(rs2.getBoolean("available"));
                return abike;
            }

        });
        return listBike;
    }

    //creates list with available and working bikes
    @Override
    public List<Bike> listAvailable() {
        String sql = "SELECT * FROM elbikes WHERE status=1 AND available=1";
        List<Bike> listAvailable = jdbcTemplate.query(sql, new RowMapper<Bike>() {
            @Override
            public Bike mapRow(ResultSet rs2, int rowNum) throws SQLException {
                Bike abike = new Bike();
                abike.setId(rs2.getInt("id"));
                abike.setBikename(rs2.getString("bikename"));
                abike.setStatus(rs2.getBoolean("status"));
                abike.setAvailable(rs2.getBoolean("available"));
                return abike;
            }
        });
        return listAvailable;
    }

    //get bike information
    @Override
    public Bike getBike(int bikeId) {
        String sql = "SELECT * FROM elbikes WHERE id=" + bikeId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Bike>() {
            @Override
            public Bike extractData(ResultSet rs2) throws SQLException,
                    DataAccessException {
                if (rs2.next()) {
                    Bike bike = new Bike();
                    bike.setId(rs2.getInt("id"));
                    bike.setBikename(rs2.getString("bikename"));
                    bike.setStatus(rs2.getBoolean("status"));
                    bike.setAvailable(rs2.getBoolean("available"));
                    return bike;
                }
                return null;
            }
        });
    }

    //get employee information
    @Override
    public User get(int userId) {
        String sql = "SELECT * FROM users WHERE id=" + userId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmployee(rs.getString("employee"));
                    user.setDate1(rs.getString("date1"));
                    user.setDate2(rs.getString("date2"));
                    return user;
                }
                return null;
            }
        });
    }
    
    @Override
    public ArrayList<Cactus> getAll(){
        return new ArrayList();
    }
    
    @Override
    public ArrayList<Cactus> getAllGenera(){
        return new ArrayList();
    }
}
