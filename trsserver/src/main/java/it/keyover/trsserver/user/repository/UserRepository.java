package it.keyover.trsserver.user.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.keyover.trsserver.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	public Optional<User> findByUsernameAndPassword(String username, String password);
	public Optional<User> findByUsername(String username);
}
