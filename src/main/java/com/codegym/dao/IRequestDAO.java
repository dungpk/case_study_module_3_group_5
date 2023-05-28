package com.codegym.dao;

import com.codegym.model.Player;
import com.codegym.model.Request;

import java.util.List;

public interface IRequestDAO {
    List<Request> getRequestByIdPlayer(int idPlayer);
    int getIdPlayerByIdRequest(int idRequest);
    int getIdUserByIdRequest(int idRequest);
    void deleteRecordByRequestId(int requestId);

    void insertRequest(int hours,String description,int userId,int playerId);
}
