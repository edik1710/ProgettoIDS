package it.unicam.cs.ids.localplatform.web;

import org.springframework.data.repository.CrudRepository;

/**
 * This interface is used to manage the UserTable in the database.
 */
public interface UserListRepository extends CrudRepository<UserTable, String> {
}
