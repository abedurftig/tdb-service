package org.tdb.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arne
 * @since 20/11/2017
 */
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @OneToMany(mappedBy = "account")
    private Set<Project> projects = new HashSet<>();

    protected Account() {}

    public Account(String name) {
        super(name);
    }

    public Set<Project> getProjects() {
        return Collections.unmodifiableSet(projects);
    }

    public void addToProjects(Project project) {
        projects.add(project);
    }

}
