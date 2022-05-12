package com.sora.n4bank.repository;

import com.sora.n4bank.constants.InterestBank;
import com.sora.n4bank.model.RegisterLoan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class RegisterLoanDAOImpl implements RegisterLoanDAO{

    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(RegisterLoanDAOImpl.class);

    public RegisterLoanDAOImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<RegisterLoan> getList(int status) {
        String sql = "select * from registerLoan where status = ? order by createdDate desc, updatedDate desc";
        if(status == 0){
            sql = "select * from registerLoan where 0 = ? order by createdDate desc, updatedDate desc";
        }
        List<RegisterLoan> registerLoans = jdbcTemplate.query(sql, new RowMapper<RegisterLoan>() {
            @Override
            public RegisterLoan mapRow(ResultSet rs, int rowNum) throws SQLException {
                RegisterLoan registerLoan = new RegisterLoan();
                logger.info(rs.getString("Id"));
                registerLoan.setId(UUID.fromString(rs.getString("Id")));
                registerLoan.setFirstName(rs.getString("FirstName"));
                registerLoan.setLastName(rs.getString("LastName"));
                registerLoan.setIdentityNumber(rs.getString("IdentityNumber"));
                registerLoan.setIdentityDate(rs.getDate("IdentityDate"));
                registerLoan.setIdentityAddress(rs.getString("IdentityAddress"));
                registerLoan.setGender(rs.getInt("Gender"));
                registerLoan.setDateOfBirth(rs.getDate("DateOfBirth"));
                registerLoan.setPhoneNumber(rs.getString("PhoneNumber"));
                registerLoan.setEmail(rs.getString("Email"));
                registerLoan.setIdentityFront(rs.getString("IdentityFront"));
                registerLoan.setIdentityBack(rs.getString("IdentityBack"));
                registerLoan.setCreatedDate(rs.getDate("CreatedDate"));
                registerLoan.setUpdatedDate(rs.getDate("UpdatedDate"));
                registerLoan.setStatus(rs.getInt("Status"));
                registerLoan.setAmount(rs.getLong("Amount"));
                registerLoan.setProvince(rs.getString("Province"));
                registerLoan.setDistrict(rs.getString("District"));
                registerLoan.setWards(rs.getString("Wards"));
                registerLoan.setCycle(rs.getInt("Cycle"));
                registerLoan.setPaperType(rs.getInt("PaperType"));
                registerLoan.setPaperImg(rs.getString("PaperImg"));
                registerLoan.setLoanDate(rs.getDate("LoanDate"));
                registerLoan.setInterestRate(rs.getDouble("InterestRate"));
                registerLoan.setPayInterestDate(rs.getDate("PayInterestDate"));
                return registerLoan;
            }
        }, status);
        return registerLoans;
    }

    @Override
    public RegisterLoan get(UUID loanId) {
        String sql = "select * from registerLoan where Id = ?";
        RegisterLoan registerLoan = null;
        try {
            registerLoan = jdbcTemplate.queryForObject(sql, new RowMapper<RegisterLoan>() {
                @Override
                public RegisterLoan mapRow(ResultSet rs, int rowNum) throws SQLException {
                    RegisterLoan registerLoan = new RegisterLoan();
                    registerLoan.setId(loanId);
                    registerLoan.setFirstName(rs.getString("FirstName"));
                    registerLoan.setLastName(rs.getString("LastName"));
                    registerLoan.setIdentityNumber(rs.getString("IdentityNumber"));
                    registerLoan.setIdentityDate(rs.getDate("IdentityDate"));
                    registerLoan.setIdentityAddress(rs.getString("IdentityAddress"));
                    registerLoan.setGender(rs.getInt("Gender"));
                    registerLoan.setDateOfBirth(rs.getDate("DateOfBirth"));
                    registerLoan.setPhoneNumber(rs.getString("PhoneNumber"));
                    registerLoan.setEmail(rs.getString("Email"));
                    registerLoan.setIdentityFront(rs.getString("IdentityFront"));
                    registerLoan.setIdentityBack(rs.getString("IdentityBack"));
                    registerLoan.setCreatedDate(rs.getDate("CreatedDate"));
                    registerLoan.setUpdatedDate(rs.getDate("UpdatedDate"));
                    registerLoan.setStatus(rs.getInt("Status"));
                    registerLoan.setAmount(rs.getLong("Amount"));
                    registerLoan.setProvince(rs.getString("Province"));
                    registerLoan.setDistrict(rs.getString("District"));
                    registerLoan.setWards(rs.getString("Wards"));
                    registerLoan.setCycle(rs.getInt("Cycle"));
                    registerLoan.setPaperType(rs.getInt("PaperType"));
                    registerLoan.setPaperImg(rs.getString("PaperImg"));
                    registerLoan.setLoanDate(rs.getDate("LoanDate"));
                    registerLoan.setInterestRate(rs.getDouble("InterestRate"));
                    registerLoan.setPayInterestDate(rs.getDate("PayInterestDate"));
                    registerLoan.setNumMonthPayInterest(rs.getInt("NumMonthPayInterest"));
                    return registerLoan;
                }
            }, loanId.toString());
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return registerLoan;
    }

    @Override
    public void insert(RegisterLoan registerLoan) {
        String sql = "insert into registerLoan " +
                "(id, firstName, lastName, identityNumber, identityDate, " +
                "identityAddress, gender, dateOfBirth, phoneNumber, email, identityFront, " +
                "identityBack, createdDate, status, amount, province, district, wards, cycle, paperType, paperImg," +
                "interestRate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowEffect = jdbcTemplate.update(
                sql,
                registerLoan.getId().toString(),
                registerLoan.getFirstName(),
                registerLoan.getLastName(),
                registerLoan.getIdentityNumber(),
                registerLoan.getIdentityDate(),
                registerLoan.getIdentityAddress(),
                registerLoan.getGender(),
                registerLoan.getDateOfBirth(),
                registerLoan.getPhoneNumber(),
                registerLoan.getEmail(),
                registerLoan.getIdentityFront(),
                registerLoan.getIdentityBack(),
                registerLoan.getCreatedDate(),
                registerLoan.getStatus(),
                registerLoan.getAmount(),
                registerLoan.getProvince(),
                registerLoan.getDistrict(),
                registerLoan.getWards(),
                registerLoan.getCycle(),
                registerLoan.getPaperType(),
                registerLoan.getPaperImg(),
                InterestBank.interest);

        logger.info(String.valueOf(rowEffect));
    }

    @Override
    public void update(RegisterLoan registerLoan) {
        String sql = "UPDATE registerLoan" +
                " SET" +
                " FirstName = ?" +
                " ,LastName = ?" +
                " ,IdentityNumber = ?" +
                " ,IdentityDate = ?" +
                " ,IdentityAddress = ?" +
                " ,Gender = ?" +
                " ,DateOfBirth = ?" +
                " ,PhoneNumber = ?" +
                " ,Email = ?" +
                " ,IdentityFront = ?" +
                " ,IdentityBack = ?" +
                " ,CreatedDate = ?" +
                " ,UpdatedDate = ?" +
                " ,Status = ?" +
                " ,Amount = ?" +
                " ,Province = ?" +
                " ,District = ?" +
                " ,Wards = ?" +
                " ,Cycle = ?" +
                " ,PaperType = ?" +
                " ,PaperImg = ?" +
                " ,LoanDate = ?" +
                " WHERE" +
                "  Id = ?" +
                ";";
        jdbcTemplate.update(sql,
                registerLoan.getFirstName(),
                registerLoan.getLastName(),
                registerLoan.getIdentityNumber(),
                registerLoan.getIdentityDate(),
                registerLoan.getIdentityAddress(),
                registerLoan.getGender(),
                registerLoan.getDateOfBirth(),
                registerLoan.getPhoneNumber(),
                registerLoan.getEmail(),
                registerLoan.getIdentityFront(),
                registerLoan.getIdentityBack(),
                registerLoan.getCreatedDate(),
                new Date(),
                registerLoan.getStatus(),
                registerLoan.getAmount(),
                registerLoan.getProvince(),
                registerLoan.getDistrict(),
                registerLoan.getWards(),
                registerLoan.getCycle(),
                registerLoan.getPaperType(),
                registerLoan.getPaperImg(),
                new Date(),
                registerLoan.getId().toString());
    }

    @Override
    public void updateStatus(UUID loanId, int status) {
        String sql = "update registerLoan " +
                "set Status = ? " +
                "where Id = ?";

        jdbcTemplate.update(sql,
                status,
                loanId.toString());
    }

    @Override
    public void updatePayInterest(RegisterLoan registerLoan) {
        String sql = "update registerLoan " +
                "set PayInterestDate = ?, " +
                "NumMonthPayInterest = ? " +
                "where Id = ?";
        jdbcTemplate.update(sql,
                registerLoan.getPayInterestDate(),
                registerLoan.getNumMonthPayInterest(),
                registerLoan.getId().toString());
    }

    @Override
    public void delete(RegisterLoan registerLoan) {
        String sql = "delete from registerLoan where Id = ?";
        jdbcTemplate.update(sql, registerLoan.getId().toString());
    }


}
