package entity;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the bio of the user.
     * @return the bio of the user.
     */
    String getBio();

    /**
     * Returns the college of the user.
     * @return the college of the user.
     */
    String getCollege();

    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    String getEmail();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the program of the user.
     * @return the program of the user.
     */
    String getProgram();

    /**
     * Returns the year of the user.
     * @return the year of the user.
     */
    String getYear();


}
