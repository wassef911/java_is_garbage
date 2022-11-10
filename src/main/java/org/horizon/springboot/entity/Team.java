package org.horizon.springboot.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.horizon.springboot.entity.Player;

import javax.persistence.OneToMany;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name_team")
	private String name;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Player> players;

	public Team(String name, Integer age) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
