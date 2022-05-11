package com.sora.n4bank.form;

import com.sora.n4bank.constants.RegisterLoanPaperTypeEnum;
import com.sora.n4bank.constants.RegisterLoanPaperTypeLocale;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterLoanForm {
    @NotBlank(message = "Tên không được để trống")
    private String firstName;

    @NotBlank(message = "Họ và tên đệm không được để trống")
    private String lastName;

    @NotBlank(message = "Số CMND/CCCD không được để trống")
    private String identityNumber;

    @NotNull(message = "Ngày cấp không được để trống")
    private Date identityDate;

    @NotBlank(message = "Nơi cấp không được để trống")
    private String identityAddress;

    @NotNull(message = "Giới tính không được để trống")
    private int gender;

    @NotNull(message = "Ngày sinh không được để trống")
    private Date dateOfBirth;

    @NotBlank(message = "SĐT không được để trống")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})", message = "SĐT không hợp lệ")
    private String phoneNumber;

    private String email;

    @NotBlank(message = "Ảnh CMND/CCCD mặt trước không được để trống")
    private String identityFront;

    @NotBlank(message = "Ảnh CMND/CCCD mặt sau không được để trống")
    private String identityBack;

    @NotNull(message = "Số tiền không được để trống")
    @Min(value = 1000000, message = "Số tiền tối thiếu là 1.000.000 VNĐ")
    @Max(value = 10000000000L, message = "Số tiền tối đa là 10.000.000.000 VNĐ")
    private Long amount;

    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    private String province;

    @NotBlank(message = "Quận/Huyện không được để trống")
    private String district;

    @NotBlank(message = "Phường/Xã không được để trống")
    private String wards;

    @NotNull(message = "Kỳ hạn không được để trống")
    private int cycle;

    @NotNull(message = "Loại giấy tờ thế chấp không được để trống")
    private Integer paperType;

    @NotBlank(message = "Ảnh giấy tờ thế chấp không được để trống")
    private String paperImg;

    private int registerLoanAction;

    public RegisterLoanForm(){

    }

    public void setRegisterLoanAction(int action){
        this.registerLoanAction = action;
    }

    public int getRegisterLoanAction(){
        return this.registerLoanAction;
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

    public Date getIdentityDateRaw(){
        return identityDate;
    }

    public String getIdentityDate() {
        if(identityDate != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(identityDate);
        } else {
            return null;
        }
    }

    public void setIdentityDateRaw(Date identityDate){
        this.identityDate = identityDate;
    }

    public void setIdentityDate(String identityDateStr) {
        if(!identityDateStr.isEmpty()){
            try {
                Date identityDate = new SimpleDateFormat("yyyy-MM-dd").parse(identityDateStr);
                this.identityDate = identityDate;
            } catch (ParseException e) {
                this.identityDate = null;
            }
        } else {
            this.identityDate = null;
        }
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

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDateOfBirthRaw(){
        return dateOfBirth;
    }

    public String getDateOfBirth() {
        if(dateOfBirth != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(dateOfBirth);
        }
        return null;
    }

    public void setDateOfBirthRaw(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirthStr) {
        if(!dateOfBirthStr.isEmpty()){
            try {
                Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthStr);
                this.dateOfBirth = dateOfBirth;
            } catch (ParseException e) {
                e.printStackTrace();
                this.dateOfBirth = null;
            }

        } else {
            this.dateOfBirth = null;
        }
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

    public Long getAmount() {
        if(amount == null || amount == 0){
            return null;
        }
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public Integer getPaperType() {
        return paperType;
    }

    public String getPaperTypeHtml(){
        String paperTypeOptions = "<option value=\"0\">Chọn loại giấy tờ</option>";
        for(RegisterLoanPaperTypeEnum registerLoanPaperTypeEnum: RegisterLoanPaperTypeEnum.values()){
            if(registerLoanPaperTypeEnum.getValue() == 0){
                continue;
            }
            paperTypeOptions += "<option value="+registerLoanPaperTypeEnum.getValue()+">"+getPaperTypeStr(registerLoanPaperTypeEnum.getValue())+"</option>";
        }
        return paperTypeOptions;
    }

    public String getPaperTypeStr(int paperType){
        RegisterLoanPaperTypeEnum registerLoanPaperTypeEnum = RegisterLoanPaperTypeEnum.values()[paperType];
        switch (registerLoanPaperTypeEnum){
            case RED_NOTE:
                return RegisterLoanPaperTypeLocale.RED_NOTE;
            default:
                return null;
        }
    }

    public void setPaperType(int paperType) {
        if(paperType == 0 || paperType == -1){
            this.paperType = null;
        }
        this.paperType = paperType;
    }

    public String getPaperImg() {
        return paperImg;
    }

    public void setPaperImg(String paperImg) {
        this.paperImg = paperImg;
    }
}
