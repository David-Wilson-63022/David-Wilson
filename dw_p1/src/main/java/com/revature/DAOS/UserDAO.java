package com.revature.DAOS;

import com.revature.POJOS.UserPojo;
import com.revature.SERVICES.DatasourceService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DatasourceCRUD<UserPojo>{

    Connection connection;

    public UserDAO() {
        this.connection = DatasourceService.getConnection();
    }


    @Override
    public void create(UserPojo userPojo) {
        try {
            String sql = "INSERT INTO users (username, passw, fname, lname, email, administrator) VALUES (?, ?, ?, ?, ?, false)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, userPojo.getUsername());
            pstmt.setString(2, userPojo.getPassword());
            pstmt.setString(3, userPojo.getFirstName());
            pstmt.setString(4, userPojo.getLastName());
            pstmt.setString(5, userPojo.getPassword());

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                Integer key = keys.getInt("user_id");
                userPojo.setUserId(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserPojo read(int id) {
        UserPojo userPojo = new UserPojo();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                userPojo.setUserId(results.getInt("user_id"));
                userPojo.setUsername(results.getString("username"));
                userPojo.setPassword(results.getString("passw"));
                userPojo.setFirstName(results.getString("fname"));
                userPojo.setLastName(results.getString("lname"));
                userPojo.setEmail(results.getString("email"));
                userPojo.setAdministrator(results.getBoolean("administrator"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userPojo;
    }

    @Override
    public List<UserPojo> readAll() {
        List<UserPojo> userPojoList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();



            while(results.next()) {
                UserPojo userPojo = new UserPojo();
                userPojo.setUserId(results.getInt("user_id"));
                userPojo.setUsername(results.getString("username"));
                userPojo.setPassword(results.getString("passw"));
                userPojo.setFirstName(results.getString("fname"));
                userPojo.setLastName(results.getString("lname"));
                userPojo.setEmail(results.getString("email"));
                userPojo.setAdministrator(results.getBoolean("administrator"));

                userPojoList.add(userPojo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userPojoList;
    }

    @Override
    public void update(UserPojo userPojo) {

        try {
            String sql = "UPDATE users SET username = ?, passw = ?, fname = ?, lname = ?, email = ?, administrator = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userPojo.getUsername());
            pstmt.setString(2, userPojo.getPassword());
            pstmt.setString(3,userPojo.getFirstName());
            pstmt.setString(4, userPojo.getLastName());
            pstmt.setString(5, userPojo.getEmail());
            pstmt.setBoolean(6, userPojo.isAdministrator());
            pstmt.setInt(7, userPojo.getUserId());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void administratorUpdate(Integer userId, Boolean administrator){
        try{
            String sql = "UPDATE users SET administrator = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, administrator);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        }

        public UserPojo logInUser(String credentials, String password) {
            UserPojo user = new UserPojo();
            try {
                String sql = "SELECT * FROM users WHERE email = ? AND passw = ? OR username = ? AND passw = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, credentials);
                pstmt.setString(2, password);
                pstmt.setString(3, credentials);
                pstmt.setString(4, password);
                ResultSet results = pstmt.executeQuery();

                if (results.next()) {
                    user.setUserId(results.getInt("user_id"));
                    user.setUsername(results.getString("username"));
                    user.setPassword(results.getString("passw"));
                    user.setFirstName(results.getString("fname"));
                    user.setLastName(results.getString("lname"));
                    user.setEmail(results.getString("email"));
                    user.setAdministrator(results.getBoolean("administrator"));

                } else {
                    throw new Exception("Incorrect Username/Email or Password");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException("Incorrect Username/Email or Password");
            }
            return user;
        }
    }

