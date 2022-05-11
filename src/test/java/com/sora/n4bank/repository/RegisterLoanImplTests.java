package com.sora.n4bank.repository;

import com.sora.n4bank.constants.InterestBank;
import com.sora.n4bank.model.RegisterLoan;
import com.sora.n4bank.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class RegisterLoanImplTests {

    @Autowired
    private RegisterLoanDAO registerLoanDAO;

    private UUID loanId = UUID.fromString("904e3d97-4b4c-4a68-8bb3-8e4aeada8921");

    /**
     * Test lấy danh sách vay lãi
     */
    @Order(1)
    @Test
    public void getListTest(){
        // khởi tạo

        // thực thi
        List<RegisterLoan> registerLoanList = registerLoanDAO.getList(0);

        // so sánh
        assertNotNull(registerLoanList);
        assertTrue(registerLoanList.size() > 0);
    }

    /**
     * Test lấy đúng thông tin hồ sơ vay lãi bằng id
     */
    @Order(2)
    @Test
    public void getById_CorrectTest(){
        // khởi tạo
        RegisterLoan registerLoan = new RegisterLoan();
        registerLoan.setId(UUID.fromString("5a26109c-8b7c-4a33-9422-1fb7eb52862a"));

        // thực thi
        RegisterLoan actualRegisterLoan = registerLoanDAO.get(registerLoan.getId());

        // so sánh
        assertNotNull(actualRegisterLoan);
        assertEquals(registerLoan.getId(), actualRegisterLoan.getId());
    }

    /**
     * Test lấy thông tin hồ sơ vay lãi băng id sai
     */
    @Order(3)
    @Test
    public void getById_WrongIdTest(){
        // khởi tạo
        RegisterLoan registerLoan = new RegisterLoan();
        registerLoan.setId(UUID.fromString("9f52fbc8-e61e-4df2-aa29-52565bc73656"));

        // thực thi
        RegisterLoan actualRegisterLoan = registerLoanDAO.get(registerLoan.getId());

        // so sánh
        assertNull(actualRegisterLoan);
    }

    /**
     * Test thêm hồ sơ vay lãi vào db
     */
    @Order(4)
    @Test
    public void insertRegisterLoan(){
        RegisterLoan registerLoanTest = new RegisterLoan();
        registerLoanTest.setId(loanId);
        registerLoanTest.setFirstName("Huân");
        registerLoanTest.setLastName("Dương");
        registerLoanTest.setIdentityNumber("123456789");
        registerLoanTest.setIdentityDate(new Date());
        registerLoanTest.setIdentityAddress("Cục cảnh sát");
        registerLoanTest.setGender(1);
        registerLoanTest.setDateOfBirth(new Date());
        registerLoanTest.setPhoneNumber("0866444202");
        registerLoanTest.setEmail("dbhuan212@gmail.com");
        registerLoanTest.setIdentityFront("front.jpg");
        registerLoanTest.setIdentityBack("back.jpg");
        registerLoanTest.setStatus(1);
        registerLoanTest.setAmount(10000000L);
        registerLoanTest.setProvince("Hà Nội");
        registerLoanTest.setDistrict("Phúc Thọ");
        registerLoanTest.setWards("Phụng Thượng");
        registerLoanTest.setCycle(3);
        registerLoanTest.setPaperType(1);
        registerLoanTest.setPaperImg("redNote.jpg");
        registerLoanTest.setInterestRate(InterestBank.interest); // lãi suất
        registerLoanTest.setCreatedDate(new Date());

        // thực thi
        registerLoanDAO.insert(registerLoanTest);

        // so sánh
        RegisterLoan actualRegisterLoan = registerLoanDAO.get(registerLoanTest.getId());

        assertNotNull(actualRegisterLoan);
        assertEquals(registerLoanTest.getFirstName(), actualRegisterLoan.getFirstName());;
    }

    /**
     * Test cập nhật thông tin hồ sơ vay lãi
     */
    @Test
    @Order(5)
    public void updateRegisterLoan_Test(){
        RegisterLoan registerLoanTest = registerLoanDAO.get(loanId);
        registerLoanTest.setFirstName("Vỹ");

        // thực thi
        registerLoanDAO.update(registerLoanTest);

        RegisterLoan actualRegisterLoan = registerLoanDAO.get(registerLoanTest.getId());
        // so sánh
        assertNotNull(actualRegisterLoan);
        assertEquals(registerLoanTest.getFirstName(), actualRegisterLoan.getFirstName());
    }

    /**
     * Test cập nhật trạng thái hồ sơ
     */
    @Order(6)
    @Test
    public void updateRegisterLoanStatus_Test(){

        // thực thi
        registerLoanDAO.updateStatus(loanId, 3);

        // so sánh
        RegisterLoan actualRegisterLoan = registerLoanDAO.get(loanId);

        assertNotNull(actualRegisterLoan);
        assertEquals(3, actualRegisterLoan.getStatus());
    }

    /**
     * Test xóa hồ sơ
     */
    @Test
    @Order(7)
    public void deleteRegisterLoan_Test(){
        RegisterLoan registerLoanTest = registerLoanDAO.get(loanId);

        registerLoanDAO.delete(registerLoanTest);

        RegisterLoan actualRegisterLoan = registerLoanDAO.get(loanId);

        assertNull(actualRegisterLoan);
    }
}
