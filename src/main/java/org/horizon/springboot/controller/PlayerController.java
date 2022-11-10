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
	public List<Player> getAllUsers() {
		return this.playerRepository.findAll();
	}

	@GetMapping("/{id}")
	public Player getUserById(@PathVariable(value = "id") long userId) {
		return this.playerRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found with id :" + userId));
	}

	@PostMapping
	public Player createUser(@RequestBody Player user) {
		return this.playerRepository.save(user);
	}

	@PutMapping("/{id}")
	public Player updateUser(@RequestBody Player user, @PathVariable("id") long userId) {
		Player existingPlayer = this.playerRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Player not found with id : <: %s :>", userId)));
		existingPlayer.setName(user.getName());
		existingPlayer.setAge(user.getAge());
		return this.playerRepository.save(existingPlayer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Player> deleteUser(@PathVariable("id") long userId) {
		Player existingPlayer = this.playerRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Player not found with id : <: %s :>", userId)));
		this.playerRepository.delete(existingPlayer);
		return ResponseEntity.ok().build();
	}
}
