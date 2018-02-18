package org.tdb.model;

import javafx.util.Pair;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tdb_dashboard")
public class Dashboard extends BaseEntity {

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "dashboard",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<DashboardProject> projects = new HashSet<>();

    public Dashboard(String name, Account account) {
        super(name);
        this.account = account;
    }

    public Dashboard() {}

    public void addProject(Project project) {
        projects.add(new DashboardProject(this, project));
    }

    public void removeProject(Project project) {
         projects.removeIf(d -> d.getProjectId().equals(project.getId()));
    }

    public List<Pair<Long, String>> getDashboardItems() {
        return this.projects.stream()
                .map(dashboardProject -> new Pair<>(dashboardProject.getProjectId(), dashboardProject.getProjectName()))
                .collect(Collectors.toList());
    }

}
