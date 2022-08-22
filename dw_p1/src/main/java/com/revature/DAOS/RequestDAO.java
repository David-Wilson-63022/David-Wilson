package com.revature.DAOS;

import com.revature.POJOS.RequestPojo;
import com.revature.SERVICES.DatasourceService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RequestDAO implements DatasourceCRUD<RequestPojo>{
    Connection connection;

    public RequestDAO() {
        connection = DatasourceService.getConnection();
    }

    @Override
    public void create(RequestPojo requestPojo) {
        try {
            String sql = "INSERT INTO requests (reim_amount, trans_desc, req_status, user_id) VALUES (?, ?, 'Pending', ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, requestPojo.getReimbursementAmount());
            pstmt.setString(2, requestPojo.getTransDescription());
            pstmt.setInt(3, requestPojo.getUserId());

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                Integer key = keys.getInt("req_id");
                requestPojo.setRequestId(key);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RequestPojo read(int id) {
        RequestPojo requestPojo = new RequestPojo();
        try {
            String sql = "SELECT * FROM requests WHERE req_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                requestPojo.setRequestId(results.getInt("req_id"));
                requestPojo.setReimbursementAmount(results.getInt("reim_amount"));
                requestPojo.setTransDescription(results.getString("trans_desc"));
                requestPojo.setRequestStatus(results.getString("req_status"));
                requestPojo.setUserId(results.getInt("user_id"));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


        return requestPojo;
    }

    @Override
    public List<RequestPojo> readAll() {
        List<RequestPojo> requestPojoList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM requests";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()) {
                RequestPojo requestPojo = new RequestPojo();

                requestPojo.setRequestId(results.getInt("req_id"));
                requestPojo.setReimbursementAmount(results.getInt("reim_amount"));
                requestPojo.setTransDescription(results.getString("trans_desc"));
                requestPojo.setRequestStatus(results.getString("req_status"));
                requestPojo.setUserId(results.getInt("user_id"));

                requestPojoList.add(requestPojo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requestPojoList;
    }

    @Override
    public void update(RequestPojo requestPojo) {
        try {
            String sql = "UPDATE requests SET req_id = ?, reim_amount = ?, trans_desc = ?, req_status = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, requestPojo.getRequestId());
            pstmt.setInt(2, requestPojo.getReimbursementAmount());
            pstmt.setString(3, requestPojo.getTransDescription());
            pstmt.setString(4, requestPojo.getRequestStatus());
            pstmt.setInt(5, requestPojo.getUserId());

            System.out.println("BREADCRUMB BEFORE EXECUTE UPDATE");
            pstmt.executeUpdate();
            System.out.println("BREADCRUMB AFTER EXECUTE UPDATE");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM requests WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);

            System.out.println("BREADCRUMB BEFORE execute update");
            pstmt.executeUpdate();
            System.out.println("BREADCRUMB AFTER DELETION (execute update)");

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}