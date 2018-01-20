package org.tdb.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tdb_dashboard_project", uniqueConstraints=
@UniqueConstraint(columnNames={"dashboard_id", "project_id"}))
public class DashboardProject extends BaseEntity {

    public DashboardProject(Dashboard dashboard, Project project) {
        this.dashboard = dashboard;
        this.project = project;
    }

    public DashboardProject() {}

    @ManyToOne
    private Dashboard dashboard;

    @ManyToOne
    private Project project;

}
