package com.assignment.security.repository;

import com.assignment.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dto on 17-Apr-17.
 */
public interface AuthorityRepository extends JpaRepository <Authority,Long> {
}
