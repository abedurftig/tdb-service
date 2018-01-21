package org.tdb.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arne
 * @since 20/11/2017
 */
@Entity
@Table(name = "tdb_account")
public class Account extends BaseEntity {

    @OneToMany(mappedBy = "account")
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Dashboard> dashboards = new HashSet<>();

    @OneToOne
    @JoinColumn
    private DashboardSettings dashboardSettings;

    @OneToOne
    @JoinColumn
    private User owner;

    protected Account() {}

    public Account(String name) {
        super(name);
    }

    public Account(User owner, String name) {
        super(name);
        this.owner = owner;
    }

    public User getOwner() {
        return this.owner;
    }

    public Set<Project> getProjects() {
        return Collections.unmodifiableSet(projects);
    }

    public void addToProjects(Project project) {
        projects.add(project);
    }

    public Set<Dashboard> getDashboards() {
        return Collections.unmodifiableSet(dashboards);
    }

    public void addToDashboards(Dashboard dashboard) {
        dashboards.add(dashboard);
    }

}
