package com.sora.n4bank.repository;

import com.sora.n4bank.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO{

    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User get(String username, String password) {
        String sql = "select * from user where username = ? limit 1";
        try {
            User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(UUID.fromString(rs.getString("Id")));
                    user.setUsername(rs.getString("Username"));
                    user.setPassword(rs.getString("Password"));
                    user.setRole(rs.getInt("Role"));
                    return user;
                }
            }, username);

            if(user == null){
                return null;
            }

            if(!user.getPassword().equalsIgnoreCase(md5(password))){
                return null;
            }

            return user;
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public User get(UUID userId) {
        String sql = "select * from user where Id = ? limit 1";
        try {
            User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(UUID.fromString(rs.getString("Id")));
                    user.setUsername(rs.getString("Username"));
                    user.setPassword(rs.getString("Password"));
                    user.setRole(rs.getInt("Role"));
                    return user;
                }
            }, userId.toString());

            if(user == null){
                return null;
            }

            return user;
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    private String md5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] data = md.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
