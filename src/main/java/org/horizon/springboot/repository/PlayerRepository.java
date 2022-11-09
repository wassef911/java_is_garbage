package org.horizon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.horizon.springboot.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
