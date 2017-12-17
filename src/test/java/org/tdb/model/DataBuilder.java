package org.tdb.model;

abstract class DataBuilder<E extends BaseEntity> {

    public abstract E create();

}
