package org.tdb.model;

import javax.persistence.*;

@Entity
@Table(name = "tdb_dashboard_project", uniqueConstraints=
@UniqueConstraint(columnNames={"dashboard_id", "project_id"}))
public class DashboardProject extends BaseEntity {

    public DashboardProject(Dashboard dashboard, Project project) {
        this.dashboard = dashboard;
        this.project = project;
    }

    public DashboardProject() {}

    @ManyToOne(optional=false)
    private Dashboard dashboard;

    @ManyToOne(optional=false)
    private Project project;

    public Long getProjectId() {
        return this.project.getId();
    }

    public String getProjectName() {
        return this.project.getName();
    }

    public Long getDashboardId() {
        return this.dashboard.getId();
    }

}
