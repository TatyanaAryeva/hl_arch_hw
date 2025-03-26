package org.hlarch.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.hlarch.model.data.CredentialsDm;
import org.hlarch.model.data.UserDm;
import org.hlarch.model.data.UserRegistrationRequestDm;
import org.hlarch.model.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static java.sql.Types.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;


    public boolean isUserExists(String username) {
        return jdbcTemplate.query(IS_USER_EXISTS_QUERY, new Object[]{username}, IS_USER_EXISTS_QUERY_TYPES,
                (rs, i) -> rs.getString("username")).stream().findFirst().isPresent();

    }

    public int register(UserRegistrationRequestDm registrationRequestDm) {
        try {
            return jdbcTemplate.queryForObject(REGISTER_QUERY, new Object[]{
                    registrationRequestDm.getUsername(),
                    registrationRequestDm.getEncryptedPassword(),
                    registrationRequestDm.getName(),
                    registrationRequestDm.getLastName(),
                    registrationRequestDm.getDateOfBirth(),
                    registrationRequestDm.getGender(),
                    StringEscapeUtils.escapeSql(registrationRequestDm.getHobbies()),
                    StringEscapeUtils.escapeSql(registrationRequestDm.getCity())
            }, REGISTER_QUERY_TYPES, Integer.class);
        } catch (Exception e) {
            log.error("Can not save info about user", e);
        }
        return -1;
    }

    public List<UserDm> get(int id) {
        return jdbcTemplate.query(GET_QUERY,
                new Object[]{id},
                GET_QUERY_TYPES,
                (rs, i) -> new UserDm(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("hobbies"),
                        rs.getString("city")
                )
        );
    }

    public boolean isValidCredentials(CredentialsDm credentialsDm) {
        return jdbcTemplate.query(IS_CREDENTIALS_VALID_QUERY,
                new Object[]{credentialsDm.getUsername(), credentialsDm.getEncryptedPassword()},
                IS_CREDENTIALS_VALID_QUERY_TYPES,
                (rs, i) -> rs.getString("username")).stream().findFirst().isPresent();
    }

    public boolean login(CredentialsDm credentialsDm) {
        int result = jdbcTemplate.update(LOGIN_QUERY,
                new Object[]{
                        credentialsDm.getUsername(),
                        credentialsDm.getToken()}, LOGIN_QUERY_TYPES);
        return result != 0;
    }

    public List<UserDm> search(String namePrefix, String lastNamePrefix) {
        return jdbcTemplate.query(SEARCH_QUERY,
                new Object[]{
                        StringEscapeUtils.escapeSql(namePrefix).concat("%"),
                        StringEscapeUtils.escapeSql(lastNamePrefix).concat("%")
                },
                SEARCH_QUERY_TYPES,
                (rs, i) -> new UserDm(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("hobbies"),
                        rs.getString("city")
                ));
    }

    public boolean isLogged(String username, UUID token) {
        return jdbcTemplate.query(IS_LOGGED_QUERY, new Object[]{username, token}, IS_LOGGED_QUERY_TYPES,
                (rs, i) -> rs.getString("username")).stream().findFirst().isPresent();
    }

    private static final String IS_USER_EXISTS_QUERY = "SELECT username FROM hlarch.user WHERE username = ?";
    private static final int[] IS_USER_EXISTS_QUERY_TYPES = new int[]{VARCHAR};

    private static final String IS_CREDENTIALS_VALID_QUERY = "SELECT username FROM hlarch.user WHERE username = ? AND password = ?";
    private static final int[] IS_CREDENTIALS_VALID_QUERY_TYPES = new int[]{VARCHAR, VARCHAR};

    private static final String REGISTER_QUERY = "INSERT INTO hlarch.user(username, password, name, last_name, " +
            "date_of_birth, gender, hobbies, city) VALUES(?,?,?,?,?,?,?,?) RETURNING id";
    private static final int[] REGISTER_QUERY_TYPES = new int[]{VARCHAR, VARCHAR, VARCHAR, VARCHAR, DATE, VARCHAR, VARCHAR, VARCHAR};

    private static final String GET_QUERY = "SELECT id, username, password, name, last_name, date_of_birth, gender, hobbies, city FROM hlarch.user WHERE id = ?";
    private static final int[] GET_QUERY_TYPES = new int[]{INTEGER};

    private static final String LOGIN_QUERY = "INSERT INTO hlarch.session (username, token, expiration_time) VALUES(?,?, NOW() + INTERVAL '15 MINUTES')";
    private static final int[] LOGIN_QUERY_TYPES = new int[]{VARCHAR, VARCHAR};

    private static final String IS_LOGGED_QUERY = "SELECT username FROM hlarch.session WHERE username =? AND token = ? AND expiration_time > NOW() - INTERVAL '15 MINUTES'";
    private static final int[] IS_LOGGED_QUERY_TYPES = new int[]{VARCHAR};

    private static final String SEARCH_QUERY = "SELECT id, username, password, name, last_name, date_of_birth, gender, hobbies, city " +
            "FROM hlarch.user WHERE name LIKE ? AND last_name LIKE ? " +
            "ORDER BY id";
    private static final int[] SEARCH_QUERY_TYPES = new int[]{VARCHAR, VARCHAR};


}
