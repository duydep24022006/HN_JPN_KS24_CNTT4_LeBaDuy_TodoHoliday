package com.example.codeholiday.repository;

import com.example.codeholiday.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITodoRepository extends JpaRepository<Todo,Long> {

}
