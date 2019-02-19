package io.github.douglasgabriel.resources.datasources

interface Datasource<T> {

    fun getDatabase(): T

}
