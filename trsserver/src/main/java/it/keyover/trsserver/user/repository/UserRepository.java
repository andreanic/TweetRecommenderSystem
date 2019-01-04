package it.keyover.trsserver.user.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.keyover.trsserver.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
