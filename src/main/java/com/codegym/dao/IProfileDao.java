package com.codegym.dao;

import com.codegym.model.Profile;

public interface IProfileDao {
    Profile getProfileByAccountId(int accountId);
}
