package com.realdolmen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * An implementation of the PersonRepository interface, but with some "mistakes".
 * See comments further in the code to give you some hints.
 */
public class JdbcPersonRepository implements PersonRepository {
    /**
     CREATE TABLE `people` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `firstName` varchar(255) DEFAULT NULL,
     `lastName` varchar(255) DEFAULT NULL,
     `street` varchar(255) DEFAULT NULL,
     `number` varchar(255) DEFAULT NULL,
     `city` varchar(255) DEFAULT NULL,
     `postalcode` varchar(255) DEFAULT NULL,
     `birthDate` date DEFAULT NULL,
     PRIMARY KEY (`id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
    */

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Person find(final int id) {
        logger.info("Finding person by id " + id);
        return execute(new StatementExecutor<Person>() {
            @Override
            public Person execute(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement("select * from people p where p.id = ?");
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                // What happens if there is no person with this id?
                // Did you test that scenario?
                return new PersonMapper().implode(resultSet);
            }
        });
    }

    @Override
    public void remove(final Person person) {
        logger.info("Removing person with id " + person.getId());
        execute(new StatementExecutor<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement("delete from people where id = ?");
                statement.setInt(1, person.getId());
                validateSuccess(statement, "Unable to remove person");
                return null;
            }
        });
    }

    private void validateSuccess(PreparedStatement statement, String message) throws SQLException {
        if(statement.executeUpdate() != 1) {
            throw new RepositoryException(message);
        }
    }

    @Override
    public void save(final Person person) {
        logger.info("Saving person " + person.name());
        execute(new StatementExecutor<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(
                    "insert into people(firstName, lastName, birthDate, street, number, postalCode, city) values(?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
                );
                new PersonMapper().explode(person, statement);
                validateSuccess(statement, "Unable to save person");
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                person.setId(generatedKeys.getInt(1));
                return null;
            }
        });
    }

    private static interface StatementExecutor<T> {
        T execute(Connection connection) throws SQLException;
    }

    private static class PersonMapper {
        private Person implode(ResultSet rs) throws SQLException {
            Person person = new Person(
                    rs.getString("firstName"),
                    null, // Bug! Oops! rs.getString("lastName"),
                    rs.getDate("birthDate"),
                    new Address(
                            rs.getString("street"),
                            rs.getString("number"),
                            null
//                            Bug! Silly me!
//                            new City(
//                                    rs.getString("city"),
//                                    rs.getString("postalCode")
//                            )
                    )
            );
            person.setId(rs.getInt("id"));
            return person;
        }

        private void explode(Person person, PreparedStatement ps) throws SQLException {
//            Bug! Not paying attention!
//            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setDate(3, new Date(person.getBirthDate().getTime()));
            ps.setString(4, person.getAddress().getStreet());
            ps.setString(5, person.getAddress().getNumber());
            ps.setString(6, person.getAddress().getCity().getName());
//            Bug! It's not my day!
//            ps.setString(7, person.getAddress().getCity().getPostalCode());
        }
    }

    /**
     * Creates a new JDBC connection.
     * @return A newly opened JDBC connection.
     * @throws SQLException When a connection could not be made.
     */
    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
    }

    private static <T> T execute(StatementExecutor<T> statementExecutor) {
        try(Connection connection = createConnection()) {
            return statementExecutor.execute(connection);
        } catch(SQLException exception) {
            throw new RepositoryException("Unable to execute statement", exception);
        }
    }
}
