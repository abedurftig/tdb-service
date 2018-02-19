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

    public List<DashboardItem> getDashboardItems() {
        return this.projects.stream()
                .map(dashboardProject -> new DashboardItem(dashboardProject.getProjectId(), dashboardProject.getProjectName()))
                .collect(Collectors.toList());
    }

    public static class DashboardItem {

        private Long projectId = null;
        private String projectName = "";

        public DashboardItem(Long projectId, String projectName) {
            this.projectId = projectId;
            this.projectName = projectName;
        }

        public Long getProjectId() {
            return projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null) {
                return false;
            }

            if (obj instanceof DashboardItem) {
                DashboardItem casted = (DashboardItem) obj;
                return projectId.equals(casted.projectId) && projectName.equals(casted.projectName);
            } else {
                return false;
            }

        }

    }

}
