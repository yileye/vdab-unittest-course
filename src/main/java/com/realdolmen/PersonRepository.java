package com.realdolmen;

public interface PersonRepository {
    /**
     * Retrieves a person by it's primary key.
     * @param id The person's primary key.
     * @return The person with the specified primary key, or null if not found.
     */
    Person find(int id);

    /**
     * Saves (insert) a new person.
     * @param person The person to save.
     */
    void save(Person person);

    /**
     * Removes (delete) an existing person.
     * @param person The person to delete. Must have it's id set.
     */
    void remove(Person person);
}
