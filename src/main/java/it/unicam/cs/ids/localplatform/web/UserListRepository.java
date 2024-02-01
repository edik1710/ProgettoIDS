package it.unicam.cs.ids.localplatform.web;

import org.springframework.data.repository.CrudRepository;

public interface UserListRepository extends CrudRepository<UserTable, String> {
}
