package com.uottawa.thirstycactus.taskcactus.domain;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peter Nguyen on 11/20/17.
 */

public class Parent extends Person {

    // ATTRIBUTES

    private String hashedPIN;         // 4 digit PIN password

    // ASSOCIATIONS

    private List<Resource> resources; // parents have exclusive rights to add resources; [*] multiplicity


    // CONSTRUCTOR

    /**
     * Constructor of Parent class
     *
     * @param id id of the parent in the database
     * @param firstName
     * @param lastName
     * @param birthDate
     *
     * @param hashedPIN
     *
     * @throws IllegalArgumentException if the hashedPIN is not a valid 4 digit PIN
     */
    public Parent(int id, String firstName, String lastName, Date birthDate, String hashedPIN) throws IllegalArgumentException
    {
        super(id, firstName, lastName, birthDate);

        setHashedPIN(hashedPIN); // used in setter to specify restrictions on the hashPIN

        this.resources = new LinkedList<>();
    }


    public Parent(String firstName, String lastName, Date birthDate, String hashedPIN) throws IllegalArgumentException
    {
        this(0, firstName, lastName, birthDate, hashedPIN);
    }

    // =============================================================================================

    // BIDIRECTIONAL LINKS

    // =============================================================================================


    /**
     * Adds a resource to the list of resources
     *
     * UNIDIRECTIONAL:
     *  - set as protected to allow only the package access it
     *  - so the user doesn't accidentally use this method
     * @param resource
     */
    protected void linkResource(Resource resource)
    {
        resources.add(resource);
    }

    /**
     * Removes a resource to the list of resources
     *
     * UNIDIRECTIONAL:
     *  - set as protected to allow only the package access it
     *  - so the user doesn't accidentally use this method
     * @param resource
     */
    protected void unlinkResource(Resource resource)
    {
        resources.remove(resource);
    }


    /**
     * Assigns a Task to a person
     *
     * @param person pointer to person
     * @param task task to be assigned
     */
    public void assignTask(Person person, Task task, Date date, boolean completed, String notes)
    {
        person.assignTask(task, date, completed, notes); // creates a bidirectional link from Task to person
    }


    // =============================================================================================

    // GETTERS/SETTERS (some comments omitted due to self explanatory nature)

    // =============================================================================================

    /**
     * Sets the 4 digit password required to login as a parent.
     * Currently stored as PLAIN TEXT.
     * ** Might implement hashing and salting later.
     *
     * @param hashedPIN 4 digit password
     * @throws IllegalArgumentException if the PIN is not 4 characters long
     */
    public void setHashedPIN(String hashedPIN) throws IllegalArgumentException
    {
        if (hashedPIN.length() == 4) // check if the PIN is 4 characters long
        {
            this.hashedPIN = hashedPIN;
        }
        else
        {
            throw new IllegalArgumentException("The PIN has to be exactly 4 digits.");
        }
    }

    public String getHashedPIN()
    {
        return hashedPIN;
    }


}
