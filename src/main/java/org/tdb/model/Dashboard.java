package org.tdb.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
        projects.remove(project);
    }

}
