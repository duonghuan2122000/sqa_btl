package com.sora.n4bank.repository;

import com.sora.n4bank.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDAOImplTests {

    @Autowired
    private UserDAO userDAO;

    /**
     * Test lấy đúng thông tin user bằng username và password
     */
    @Test
    public void getUserByUsernameAndPassword_CorrectTest(){
        // init (khởi tạo)
        String passwordRaw = "123456";
        String passwordHash = md5(passwordRaw);

        User user = new User(
                UUID.fromString("ce5ca794-2d0f-418f-a2f3-2c0f24433dc7"),
                "Kinh doanh",
                "N4Bank",
                "sale",
                passwordHash,
                1,
                null,
                null
        );

        // thực thi
        User actualUser = userDAO.get(user.getUsername(), passwordRaw);

        // assert (so sánh)
        assertNotNull(actualUser);
        assertEquals(user.getId(), actualUser.getId());
        assertEquals(user.getUsername(), actualUser.getUsername());
        assertEquals(user.getPassword(), passwordHash);
    }

    /**
     * Lấy thông tin user bằng username và password case sai username
     */
    @Test
    public void GetUserByUsernameAndPassword_WrongUsernameTest(){
        // init (khởi tạo)
        String passwordRaw = "123456";
        String passwordHash = md5(passwordRaw);

        User user = new User(
                UUID.fromString("ce5ca794-2d0f-418f-a2f3-2c0f24433dc7"),
                "Kinh doanh",
                "N4Bank",
                "abcxyz123qwe",
                passwordHash,
                1,
                null,
                null
        );

        // thực thi
        User actualUser = userDAO.get(user.getUsername(), passwordHash);

        // so sánh
        assertNull(actualUser);
    }

    @Test
    public void getUserByUsernameAndPassword_WrongPasswordTest(){
        // init (khởi tạo)
        String passwordRaw = "123456789";
        String passwordHash = md5(passwordRaw);

        User user = new User(
                UUID.fromString("ce5ca794-2d0f-418f-a2f3-2c0f24433dc7"),
                "Kinh doanh",
                "N4Bank",
                "sale",
                passwordHash,
                1,
                null,
                null
        );

        // thực thi
        User actualUser = userDAO.get(user.getUsername(), passwordHash);

        // so sánh
        assertNull(actualUser);
    }

    /**
     * Test lấy đúng thông tin user bằng id
     */
    @Test
    public void getUserById_CorrectTest(){
        // khởi tạo
        String passwordRaw = "123456";
        String passwordHash = md5(passwordRaw);

        User user = new User(
                UUID.fromString("ce5ca794-2d0f-418f-a2f3-2c0f24433dc7"),
                "Kinh doanh",
                "N4Bank",
                "sale",
                passwordHash,
                1,
                null,
                null
        );

        // thực thi
        User actualUser = userDAO.get(user.getId());

        // so sánh
        assertNotNull(actualUser);
        assertEquals(user.getId(), actualUser.getId());
        assertEquals(user.getUsername(), actualUser.getUsername());
        assertEquals(user.getPassword(), passwordHash);
    }

    /**
     * Test lấy thông tin user bằng id case sai id
     */
    @Test
    public void getUserById_WrongIdTest(){
        // khởi tạo
        String passwordRaw = "123456";
        String passwordHash = md5(passwordRaw);

        User user = new User(
                UUID.fromString("ce5ca794-2d0f-418f-a2f3-2c0f24433dc6"),
                "Kinh doanh",
                "N4Bank",
                "sale",
                passwordHash,
                1,
                null,
                null
        );

        User actualUser = userDAO.get(user.getId());

        // so sánh
        assertNull(actualUser);
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
