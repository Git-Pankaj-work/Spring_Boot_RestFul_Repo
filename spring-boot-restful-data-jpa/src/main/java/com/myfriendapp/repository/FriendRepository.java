package com.myfriendapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfriendapp.entity.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

}
