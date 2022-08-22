package com.revature.SERVICES;

import com.revature.DAOS.RequestDAO;
import com.revature.POJOS.RequestPojo;

import java.util.List;

public class RequestService {
    private RequestDAO dao;

    public RequestService() {
        this.dao = new RequestDAO();
    }

    public void saveRequest(RequestPojo requestPojo) {
        dao.create(requestPojo);
    }

    public RequestPojo getRequest(int id) {
        return dao.read(id);
    }

    public List<RequestPojo> getAllRequests() {
        return dao.readAll();
    }

    public List<RequestPojo> getRequestsForUser(Integer userId) {
        List<RequestPojo> requestPojoList = dao.readAll();

        for(RequestPojo requestPojo : requestPojoList) {
            if(!requestPojo.getUserId().equals(userId)) {
                requestPojoList.remove(requestPojo);
            }
        }

        return requestPojoList;
    }

    public void updateRequest(RequestPojo requestPojo) {
        dao.update(requestPojo);
    }

    public void deleteRequest(int id) {
        dao.delete(id);
    }



}