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

import org.horizon.springboot.entity.Team;
import org.horizon.springboot.exception.ResourceNotFoundException;
import org.horizon.springboot.repository.TeamRepository;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@GetMapping
	public List<Team> getAllTeams() {
		return this.teamRepository.findAll();
	}

	@GetMapping("/{id}")
	public Team getTeamById(@PathVariable(value = "id") long teamId) {
		return this.teamRepository.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found with id :" + teamId));
	}

	@PostMapping
	public Team createTeam(@RequestBody Team team) {
		return this.teamRepository.save(team);
	}

	@PutMapping("/{id}")
	public Team updateTeam(@RequestBody Team team, @PathVariable("id") long teamId) {
		Team existingTeam = this.teamRepository.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Team not found with id : <: %s :>", teamId)));
		existingTeam.setName(team.getName());
		return this.teamRepository.save(existingTeam);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Team> deleteTeam(@PathVariable("id") long teamId) {
		Team existingTeam = this.teamRepository.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Team not found with id : <: %s :>", teamId)));
		this.teamRepository.delete(existingTeam);
		return ResponseEntity.ok().build();
	}
}
