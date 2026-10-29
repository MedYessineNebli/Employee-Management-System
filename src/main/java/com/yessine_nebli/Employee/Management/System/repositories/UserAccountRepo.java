package com.yessine_nebli.Employee.Management.System.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yessine_nebli.Employee.Management.System.entities.UserAccount;

public interface UserAccountRepo extends JpaRepository<UserAccount, UUID> {

    Optional<UserAccount> findOneByUsername(String username);

}
