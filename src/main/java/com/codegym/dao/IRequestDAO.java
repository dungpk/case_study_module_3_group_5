package com.codegym.dao;

import com.codegym.model.Request;

import java.util.List;

public interface IRequestDAO {
    List<Request> getRequestByIdPlayer(int idPlayer);
}
