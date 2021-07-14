package com.bezngor.crud_hibernate.model;

import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "devs_teams",
        joinColumns = @JoinColumn(name = "id_team"),
        inverseJoinColumns = @JoinColumn(name = "id_dev")
    )
    private List<Developer> devs;

    @Column (name = "status")
    private Integer status;

    public Team() {
    }

    public Team(Integer id, String name, TeamStatus status, List<Developer> devs) {
        this.id = id;
        this.name = name;
        this.status = status.getValue();
        this.devs = devs;
    }

    public Team(String name, List<Developer> devs) {
        this.name = name;
        this.devs = devs;
    }

    public Team(String name, TeamStatus status, List<Developer> devs) {
        this.name = name;
        this.status = status.getValue();
        this.devs = devs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDevs() {
        return devs;
    }

    public void setDevs(List<Developer> devs) {
        this.devs = devs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status.getValue();
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", devs=" + devs +
                ", status=" + status +
                '}';
    }
}
