package com.sora.n4bank.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.sora.n4bank.constants.RegisterLoanPaperTypeEnum;
import com.sora.n4bank.constants.RegisterLoanPaperTypeLocale;
import com.sora.n4bank.constants.RegisterLoanStatusEnums;
import com.sora.n4bank.controller.AuthenticationController;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterLoan {
    private UUID id;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private Date identityDate;
    private String identityAddress;
    private int gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String identityFront;
    private String identityBack;
    private Date createdDate;
    private Date updatedDate;
    private int status;
    private Long amount;
    private String province;
    private String district;
    private String wards;
    private int cycle;
    private int paperType;
    private String paperImg;
    private Date loanDate;
    private double interestRate;
    private Date payInterestDate;
    private Integer numMonthPayInterest;

    public RegisterLoan() {

    }

    public Integer getNumMonthPayInterest() {
        return numMonthPayInterest;
    }

    public void setNumMonthPayInterest(Integer numMonthPayInterest) {
        this.numMonthPayInterest = numMonthPayInterest;
    }

    public Date getPayInterestDate() {
        return payInterestDate;
    }

    public String getPayInterestDateFormat(){
        if(payInterestDate != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(payInterestDate);
        }
        return null;
    }

    public void setPayInterestDate(Date payInterestDate) {
        this.payInterestDate = payInterestDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public String getLoanDateStr(){
        if(loanDate != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(loanDate);
        }
        return null;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getInterestRateStr(){
        return interestRate + "%/năm";
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public RegisterLoan(UUID id, String firstName, String lastName, String identityNumber, Date identityDate, String identityAddress, int gender, Date dateOfBirth, String phoneNumber, String email, String identityFront, String identityBack, Date createdDate, Date updatedDate, int status, long amount, String province, String district, String wards, int cycle, int paperType, String paperImg) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.identityDate = identityDate;
        this.identityAddress = identityAddress;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identityFront = identityFront;
        this.identityBack = identityBack;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
        this.amount = amount;
        this.province = province;
        this.district = district;
        this.wards = wards;
        this.cycle = cycle;
        this.paperType = paperType;
        this.paperImg = paperImg;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Date getIdentityDate() {
        return identityDate;
    }

    public String getIdentityDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (identityDate != null) {
            return dateFormat.format(identityDate);
        }
        return null;
    }

    public void setIdentityDate(Date identityDate) {
        this.identityDate = identityDate;
    }

    public String getIdentityAddress() {
        return identityAddress;
    }

    public void setIdentityAddress(String identityAddress) {
        this.identityAddress = identityAddress;
    }

    public int getGender() {
        return gender;
    }

    public String getGenderStr() {
        switch (gender) {
            case 1:
                return "Nam";
            case 2:
                return "Nữ";
            default:
                return "Khác";
        }
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfBirthFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (dateOfBirth != null) {
            return dateFormat.format(dateOfBirth);
        }
        return null;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityFront() {
        return identityFront;
    }

    public void setIdentityFront(String identityFront) {
        this.identityFront = identityFront;
    }

    public String getIdentityBack() {
        return identityBack;
    }

    public void setIdentityBack(String identityBack) {
        this.identityBack = identityBack;
    }

    public String getCreatedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(createdDate);
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusLocale() {
        RegisterLoanStatusEnums registerLoanStatusEnum = RegisterLoanStatusEnums.values()[status];
        switch (registerLoanStatusEnum) {
            case CREATE:
                return "Tạo mới";
            case WAITINGAPPROVE:
                return "Chờ phê duyệt";
            case APPROVE:
                return "Phê duyệt";
            case DISBURSEMENT:
                return "Đã giải ngân";
            case RETURNMONEY:
                return "Đã trả nợ";
        }
        return null;
    }

    public String getStatusHtml() {
        RegisterLoanStatusEnums registerLoanStatusEnum = RegisterLoanStatusEnums.values()[status];
        switch (registerLoanStatusEnum) {
            case CREATE:
                return "<div class='text-warning'>Tạo mới</div>";
            case WAITINGAPPROVE:
                return "<div class='text-warning'>Chờ phê duyệt</div>";
            case APPROVE:
                return "<div class='text-success'>Phê duyệt</div>";
            case DISBURSEMENT:
                return "<div class='text-success'>Đã giải ngân</div>";
            case RETURNMONEY:
                return "<div class='text-success'>Đã trả nợ</div>";
        }
        return null;
    }

    public String getOptionByStatusHtml() {
        RegisterLoanStatusEnums registerLoanStatusEnum = RegisterLoanStatusEnums.values()[status];
        String optionHtml = "";
        switch (registerLoanStatusEnum) {
            case CREATE:
                optionHtml += "<a href='/loans/" + id + "/edit' class='btn btn-warning'>Cập nhật</a>";
                optionHtml += "<a href='/loans/" + id + "/view' class='btn btn-success ms-2'>Xem</a>";
                break;
            case WAITINGAPPROVE:
                optionHtml += "<a href='/loans/" + id + "/edit' class='btn btn-warning'>Cập nhật</a>";
                optionHtml += "<a href='/loans/" + id + "/view' class='btn btn-success ms-2'>Xem</a>";
                break;
            case APPROVE:
                optionHtml += "<a href='/loans/" + id + "/view' class='btn btn-success'>Xem</a>";
                break;
            case DISBURSEMENT:
                optionHtml += "<a href='/loans/" + id + "/money' class='btn btn-success'>Thông tin tiền lãi</a>";
                optionHtml += "<a href='/loans/" + id + "/view' class='btn btn-success ms-2'>Xem</a>";
                break;
        }
        return optionHtml;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public String getAmountWithFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        return decimalFormat.format(amount) + " VNĐ";
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return wards + " " + district + " " + province;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public int getCycle() {
        return cycle;
    }

    public String getCycleStr() {
        switch (cycle) {
            case 1:
                return "1 Tháng";
            case 2:
                return "3 Tháng";
            case 3:
                return "6 Tháng";
            case 4:
                return "1 Năm";
            case 5:
                return "1 Năm 6 Tháng";
            case 6:
                return "2 Năm";
            case 7:
                return "2 Năm 6 Tháng";
            case 8:
                return "3 Năm";
            default:
                return null;
        }
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getPaperType() {
        return paperType;
    }

    public String getPaperTypeStr() {
        RegisterLoanPaperTypeEnum registerLoanPaperTypeEnum = RegisterLoanPaperTypeEnum.values()[paperType];
        switch (registerLoanPaperTypeEnum) {
            case RED_NOTE:
                return RegisterLoanPaperTypeLocale.RED_NOTE;
            default:
                return null;
        }
    }

    public String getPaperTypeStr(int paperType) {
        RegisterLoanPaperTypeEnum registerLoanPaperTypeEnum = RegisterLoanPaperTypeEnum.values()[paperType];
        switch (registerLoanPaperTypeEnum) {
            case RED_NOTE:
                return RegisterLoanPaperTypeLocale.RED_NOTE;
            default:
                return null;
        }
    }

    public String getPaperTypeHtml() {
        String paperTypeOptions = "<option value=\"0\">Chọn loại giấy tờ</option>";
        for (RegisterLoanPaperTypeEnum registerLoanPaperTypeEnum : RegisterLoanPaperTypeEnum.values()) {
            if (registerLoanPaperTypeEnum.getValue() == 0) {
                continue;
            }
            paperTypeOptions += "<option value=" + registerLoanPaperTypeEnum.getValue() + ">" + getPaperTypeStr(registerLoanPaperTypeEnum.getValue()) + "</option>";
        }
        return paperTypeOptions;
    }

    public void setPaperType(int paperType) {
        this.paperType = paperType;
    }

    public String getPaperImg() {
        return paperImg;
    }

    public void setPaperImg(String paperImg) {
        this.paperImg = paperImg;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    /**
     * Số tháng chưa trả tiền lãi
     * @return
     */
    public int getNumMonthInterest(){
        if(status == 4){
            Date mPayInterestDate = getPayInterestDate();
            if(mPayInterestDate == null){
                mPayInterestDate = getLoanDate();
            }
            LocalDate payInterestLocalDate = Instant.ofEpochMilli(mPayInterestDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentLocalDate = LocalDate.now();

            Period period = Period.between(payInterestLocalDate, currentLocalDate);
            int months = period.getMonths();
            return months;
        }
        return 0;
    }

    public Long getInterestAmount(){
        if(status == 4){
            int months = getNumMonthInterest();
            return (long)Math.ceil(months * amount * interestRate / 100 / 12);
        }
        return 0L;
    }

    public String getInterestAmountStr(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        return decimalFormat.format(getInterestAmount()) + " VNĐ";
    }

    public Long getTotalReturnMoney(){
        Long totalReturnMoney = getAmount() + getInterestAmount();
        return totalReturnMoney;
    }

    public String getTotalReturnMoneyStr(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        return decimalFormat.format(getTotalReturnMoney()) + " VNĐ";
    }
}
