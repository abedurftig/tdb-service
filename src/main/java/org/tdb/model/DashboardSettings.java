package org.tdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tdb_dashboard_settings")
public class DashboardSettings extends BaseEntity {

    private Dashboard defaultDashboard;

    DashboardSettings() {}

}
