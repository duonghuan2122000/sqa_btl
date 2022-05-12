package com.sora.n4bank.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class RegisterLoanTests {

    /**
     * Test lấy ngày vay lãi theo định dạng dd/MM/yyyy
     */
    @Test
    public void getLoanDateStr_Test(){
        RegisterLoan registerLoan = new RegisterLoan();
        Calendar c = Calendar.getInstance();
        c.set(2022, 4, 12);
        String dateStr = "12/05/2022";
        registerLoan.setLoanDate(c.getTime());

        assertEquals(dateStr, registerLoan.getLoanDateStr());
    }

    /**
     * Test lấy ngày vay lãi case null
     */
    @Test
    public void getLoanDateStr_NullTest(){
        RegisterLoan registerLoan = new RegisterLoan();

        assertNull(registerLoan.getLoanDateStr());
    }

    /**
     * Lấy ngày cấp CMND
     */
    @Test
    public void getIdentityDate_Test(){
        RegisterLoan registerLoan = new RegisterLoan();
        Calendar c = Calendar.getInstance();
        c.set(2022, 4, 12);
        String dateStr = "12/05/2022";
        registerLoan.setIdentityDate(c.getTime());

        assertEquals(dateStr, registerLoan.getIdentityDateFormat());

    }

    /**
     * Test lấy ngày CMND case null
     */
    @Test
    public void getIdentityDate_NullTest(){
        RegisterLoan registerLoan = new RegisterLoan();

        assertNull(registerLoan.getIdentityDateFormat());
    }

    /**
     * Test lấy giới tính
     */
    @Test
    public void getGender_Test(){
        RegisterLoan registerLoan = new RegisterLoan();

        registerLoan.setGender(1);
        assertEquals("Nam", registerLoan.getGenderStr());

        registerLoan.setGender(2);
        assertEquals("Nữ", registerLoan.getGenderStr());

        registerLoan.setGender(3);
        assertEquals("Khác", registerLoan.getGenderStr());
    }

    /**
     * Test lấy ngày sinh
     */
    @Test
    public void getDateOfBirth_Test(){
        RegisterLoan registerLoan = new RegisterLoan();
        Calendar c = Calendar.getInstance();
        c.set(2022, 4, 12);
        String dateStr = "12/05/2022";
        registerLoan.setDateOfBirth(c.getTime());

        assertEquals(dateStr, registerLoan.getDateOfBirthFormat());
    }

    /**
     * Test ngày sinh case null
     */
    @Test
    public void getDateOfBirth_NullTest(){
        RegisterLoan registerLoan = new RegisterLoan();

        assertNull(registerLoan.getDateOfBirthFormat());
    }

    /**
     * Test lấy trạng thái
     */
    public void getStatusLocale_Test(){
        RegisterLoan registerLoan = new RegisterLoan();

        registerLoan.setStatus(1);
        assertEquals("Tạo mới", registerLoan.getStatusLocale());

        registerLoan.setStatus(2);
        assertEquals("Chờ phê duyệt", registerLoan.getStatusLocale());

        registerLoan.setStatus(3);
        assertEquals("Phê duyệt", registerLoan.getStatusLocale());

        registerLoan.setStatus(4);
        assertEquals("Đã giải ngân", registerLoan.getStatusLocale());

        registerLoan.setStatus(5);
        assertEquals("Đã trả nợ", registerLoan.getStatusLocale());
    }

    /**
     * Test lấy số tiền
     */
    @Test
    public void getAmount_Test(){
        RegisterLoan registerLoan = new RegisterLoan();
        registerLoan.setAmount(10000000L);

        String amountStr = "10,000,000 VNĐ";
        assertEquals(amountStr, registerLoan.getAmountWithFormat());
    }
}
