package com.sending.sendsmsdemo.Repository;

import com.sending.sendsmsdemo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendSmsRepo extends JpaRepository<User,Long> {

}
