package org.horizon.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.horizon.springboot.entity.Player;
import org.horizon.springboot.exception.ResourceNotFoundException;
import org.horizon.springboot.repository.PlayerRepository;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;

	@GetMapping
	public List<Player> getAllPlayers() {
		return this.playerRepository.findAll();
	}

	@GetMapping("/{id}")
	public Player getPlayerById(@PathVariable(value = "id") long playerId) {
		return this.playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found with id :" + playerId));
	}

	@PostMapping
	public Player createPlayer(@RequestBody Player player) {
		return this.playerRepository.save(player);
	}

	@PutMapping("/{id}")
	public Player updatePlayer(@RequestBody Player player, @PathVariable("id") long playerId) {
		Player existingPlayer = this.playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Player not found with id : <: %s :>", playerId)));
		existingPlayer.setName(player.getName());
		existingPlayer.setAge(player.getAge());
		return this.playerRepository.save(existingPlayer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Player> deletePlayer(@PathVariable("id") long playerId) {
		Player existingPlayer = this.playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Player not found with id : <: %s :>", playerId)));
		this.playerRepository.delete(existingPlayer);
		return ResponseEntity.ok().build();
	}
}
