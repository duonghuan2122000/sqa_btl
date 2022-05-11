package com.sora.n4bank.controller;

import com.sora.n4bank.annotation.AdminAuth;
import com.sora.n4bank.constants.InterestBank;
import com.sora.n4bank.constants.RegisterLoanStatusEnums;
import com.sora.n4bank.form.RegisterLoanForm;
import com.sora.n4bank.model.RegisterLoan;
import com.sora.n4bank.model.User;
import com.sora.n4bank.repository.RegisterLoanDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/loans")
public class RegisterLoanController {

    Logger logger = LoggerFactory.getLogger(RegisterLoanController.class);


    @Autowired
    private RegisterLoanDAO registerLoanDAO;

    @AdminAuth
    @GetMapping("")
    public String list(HttpSession session, HttpServletRequest request, Model model, @RequestParam(name = "status", defaultValue = "0") int status) {
        List<RegisterLoan> registerLoans = registerLoanDAO.getList(status);
        model.addAttribute("registerLoans", registerLoans);
        model.addAttribute("registerLoanStatusFilter", getFilterRegisterLoanStatusHtml(status));
        return "registerLoan/list";
    }

    @AdminAuth
    @GetMapping("/{loanId}/view")
    public String getView(HttpSession session, HttpServletRequest request, Model model, @PathVariable(name = "loanId") String id) {
        try {
            UUID loanId = UUID.fromString(id);
            RegisterLoan registerLoan = registerLoanDAO.get(loanId);
            model.addAttribute("registerLoan", registerLoan);
            model.addAttribute("registerLoanEdit", "/loans/" + registerLoan.getId() + "/edit");
            model.addAttribute("registerLoanApproveUrl", "/loans/" + registerLoan.getId() + "/approve");
            model.addAttribute("registerLoanDisbursementUrl", "/loans/" + registerLoan.getId() + "/disbursement");
            model.addAttribute("interestBank", InterestBank.interest + "%/năm");
            model.addAttribute("loanMoneyUrl", "/loans/" + registerLoan.getId() + "/money");
            return "registerLoan/detail";
        } catch (IllegalArgumentException ex) {
            return "error";
        }
    }

    @AdminAuth
    @GetMapping("/{loanId}/edit")
    public String getEdit(HttpSession session, HttpServletRequest request, Model model, @PathVariable(name = "loanId") String id) {
        try {
            UUID loanId = UUID.fromString(id);
            RegisterLoan registerLoan = registerLoanDAO.get(loanId);

            RegisterLoanForm registerLoanForm = new RegisterLoanForm();
            registerLoanForm.setFirstName(registerLoan.getFirstName());
            registerLoanForm.setLastName(registerLoan.getLastName());
            registerLoanForm.setGender(registerLoan.getGender());
            registerLoanForm.setDateOfBirthRaw(registerLoan.getDateOfBirth());
            registerLoanForm.setIdentityNumber(registerLoan.getIdentityNumber());
            registerLoanForm.setIdentityDateRaw(registerLoan.getIdentityDate());
            registerLoanForm.setIdentityAddress(registerLoan.getIdentityAddress());
            registerLoanForm.setIdentityFront(registerLoan.getIdentityFront());
            registerLoanForm.setIdentityBack(registerLoan.getIdentityBack());
            registerLoanForm.setProvince(registerLoan.getProvince());
            registerLoanForm.setDistrict(registerLoan.getDistrict());
            registerLoanForm.setWards(registerLoan.getWards());
            registerLoanForm.setPhoneNumber(registerLoan.getPhoneNumber());
            registerLoanForm.setEmail(registerLoan.getEmail());
            registerLoanForm.setPaperType(registerLoan.getPaperType());
            registerLoanForm.setPaperImg(registerLoan.getPaperImg());
            registerLoanForm.setAmount(registerLoan.getAmount());
            registerLoanForm.setCycle(registerLoan.getCycle());

            model.addAttribute("registerLoan", registerLoan);
            model.addAttribute("registerLoanForm", registerLoanForm);
            model.addAttribute("registerLoanEditSubmitUrl", "/loans/" + registerLoan.getId() + "/edit");
            return "registerLoan/edit";
        } catch(IllegalArgumentException ex){
            return "error";
        }
    }

    @AdminAuth
    @PostMapping("/{loanId}/edit")
    public String postEdit(HttpSession session, HttpServletRequest request, Model model, @ModelAttribute @Valid RegisterLoanForm registerLoanForm, BindingResult errors, @PathVariable(name = "loanId") String id){
        UUID loanId = UUID.fromString(id);

        RegisterLoan registerLoan = registerLoanDAO.get(loanId);
        if(registerLoanForm.getRegisterLoanAction() == 1){
            // gửii
            if(errors.hasErrors()){
                registerLoanForm.setFirstName(registerLoan.getFirstName());
                registerLoanForm.setLastName(registerLoan.getLastName());
                registerLoanForm.setGender(registerLoan.getGender());
                registerLoanForm.setDateOfBirthRaw(registerLoan.getDateOfBirth());
                registerLoanForm.setIdentityNumber(registerLoan.getIdentityNumber());
                registerLoanForm.setIdentityDateRaw(registerLoan.getIdentityDate());
                registerLoanForm.setIdentityAddress(registerLoan.getIdentityAddress());
                registerLoanForm.setIdentityFront(registerLoan.getIdentityFront());
                registerLoanForm.setIdentityBack(registerLoan.getIdentityBack());
                registerLoanForm.setProvince(registerLoan.getProvince());
                registerLoanForm.setDistrict(registerLoan.getDistrict());
                registerLoanForm.setWards(registerLoan.getWards());
                registerLoanForm.setPhoneNumber(registerLoan.getPhoneNumber());
                registerLoanForm.setEmail(registerLoan.getEmail());
                registerLoanForm.setPaperType(registerLoan.getPaperType());
                registerLoanForm.setPaperImg(registerLoan.getPaperImg());
                registerLoanForm.setAmount(registerLoan.getAmount());
                registerLoanForm.setCycle(registerLoan.getCycle());

                model.addAttribute("registerLoan", registerLoan);
                model.addAttribute("registerLoanEditSubmitUrl", "/loans/" + registerLoan.getId() + "/edit");
                model.addAttribute("registerLoanForm", registerLoanForm);
                return "registerLoan/edit";
            }
            registerLoan.setStatus(RegisterLoanStatusEnums.WAITINGAPPROVE.getValue());
        } else {
            registerLoan.setStatus(RegisterLoanStatusEnums.WAITINGAPPROVE.getValue());
        }

        // lưu thông tin vay lãi
        registerLoan.setFirstName(registerLoanForm.getFirstName());
        registerLoan.setLastName(registerLoanForm.getLastName());
        registerLoan.setIdentityNumber(registerLoanForm.getIdentityNumber());
        registerLoan.setIdentityDate(registerLoanForm.getIdentityDateRaw());
        registerLoan.setIdentityAddress(registerLoanForm.getIdentityAddress());
        registerLoan.setGender(registerLoanForm.getGender());
        registerLoan.setDateOfBirth(registerLoanForm.getDateOfBirthRaw());
        registerLoan.setPhoneNumber(registerLoanForm.getPhoneNumber());
        registerLoan.setEmail(registerLoanForm.getEmail());
        registerLoan.setIdentityFront(registerLoanForm.getIdentityFront());
        registerLoan.setIdentityBack(registerLoanForm.getIdentityBack());
        registerLoan.setCreatedDate(new Date());
        registerLoan.setAmount(registerLoanForm.getAmount());
        registerLoan.setProvince(registerLoanForm.getProvince());
        registerLoan.setDistrict(registerLoanForm.getDistrict());
        registerLoan.setWards(registerLoanForm.getWards());
        registerLoan.setCycle(registerLoanForm.getCycle());
        registerLoan.setPaperType(registerLoanForm.getPaperType());
        registerLoan.setPaperImg(registerLoanForm.getPaperImg());

        registerLoanDAO.update(registerLoan);

        return "redirect:/loans/" + loanId + "/view";
    }

    @AdminAuth
    @GetMapping("/add")
    public String getAdd(HttpSession session, HttpServletRequest request, Model model) {
        model.addAttribute("registerLoanForm", new RegisterLoanForm());
        return "registerLoan/add";
    }

    @AdminAuth
    @PostMapping("/add")
    public String postAdd(HttpSession session, HttpServletRequest request, Model model, @ModelAttribute @Valid RegisterLoanForm registerLoanForm, BindingResult errors) {
        RegisterLoan registerLoan = new RegisterLoan();
        if (registerLoanForm.getRegisterLoanAction() == 1) {
            // gửi
            if (errors.hasErrors()) {
                return "registerLoan/add";
            }
            registerLoan.setStatus(RegisterLoanStatusEnums.WAITINGAPPROVE.getValue());
        } else {
            registerLoan.setStatus(RegisterLoanStatusEnums.CREATE.getValue());
        }

        // lưu thông tin vay lãi
        registerLoan.setId(UUID.randomUUID());
        registerLoan.setFirstName(registerLoanForm.getFirstName());
        registerLoan.setLastName(registerLoanForm.getLastName());
        registerLoan.setIdentityNumber(registerLoanForm.getIdentityNumber());
        registerLoan.setIdentityDate(registerLoanForm.getIdentityDateRaw());
        registerLoan.setIdentityAddress(registerLoanForm.getIdentityAddress());
        registerLoan.setGender(registerLoanForm.getGender());
        registerLoan.setDateOfBirth(registerLoanForm.getDateOfBirthRaw());
        registerLoan.setPhoneNumber(registerLoanForm.getPhoneNumber());
        registerLoan.setEmail(registerLoanForm.getEmail());
        registerLoan.setIdentityFront(registerLoanForm.getIdentityFront());
        registerLoan.setIdentityBack(registerLoanForm.getIdentityBack());
        registerLoan.setCreatedDate(new Date());
        registerLoan.setAmount(registerLoanForm.getAmount());
        registerLoan.setProvince(registerLoanForm.getProvince());
        registerLoan.setDistrict(registerLoanForm.getDistrict());
        registerLoan.setWards(registerLoanForm.getWards());
        registerLoan.setCycle(registerLoanForm.getCycle());
        registerLoan.setPaperType(registerLoanForm.getPaperType());
        registerLoan.setPaperImg(registerLoanForm.getPaperImg());

        registerLoanDAO.insert(registerLoan);

        return "redirect:/loans";
    }

    private String getFilterRegisterLoanStatusHtml(int status) {
        String optionHtml = "<option value='0'>Tất cả</option>";
        for (RegisterLoanStatusEnums registerLoanStatusEnum : RegisterLoanStatusEnums.values()) {
            if (registerLoanStatusEnum.getValue() == 0) {
                continue;
            }
            optionHtml += "<option value='" + registerLoanStatusEnum.getValue() + "' " + (registerLoanStatusEnum.getValue() == status ? "selected" : "") + ">" + getRegisterStatusStr(registerLoanStatusEnum.getValue()) + "</option>";
        }
        return optionHtml;
    }

    private String getRegisterStatusStr(int status) {
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

    @AdminAuth
    @GetMapping("/{loanId}/approve")
    public String approveRegisterLoan(HttpSession session, HttpServletRequest request, Model model, @PathVariable(name = "loanId") String id){
        User currentUser = (User) model.getAttribute("currentUser");

        // role user = 1: nhân viên kinh doanh
        if(currentUser.getRole() == 1){
            return "redirect:/";
        }

        UUID loanId = UUID.fromString(id);
        RegisterLoan registerLoan = registerLoanDAO.get(loanId);
        if(registerLoan.getStatus() != RegisterLoanStatusEnums.WAITINGAPPROVE.getValue()){
            // không phải đang chờ phê duyệt thì chuyển về trang xem
            return "redirect:/loans/" + registerLoan.getId() + "/view";
        }

//        registerLoanDAO.update(registerLoan);
        registerLoanDAO.updateStatus(registerLoan.getId(), RegisterLoanStatusEnums.APPROVE.getValue());
        return "redirect:/loans/" + registerLoan.getId() + "/view";
    }

    @AdminAuth
    @GetMapping("/{loanId}/disbursement")
    public String disbursementRegisterLoan(HttpSession session, HttpServletRequest request, Model model, @PathVariable(name = "loanId") String id){
        User currentUser = (User) model.getAttribute("currentUser");

        // role user = 3: kế toán
        if(currentUser.getRole() != 3){
            return "redirect:/";
        }

        UUID loanId = UUID.fromString(id);
        RegisterLoan registerLoan = registerLoanDAO.get(loanId);
        if(registerLoan.getStatus() != RegisterLoanStatusEnums.APPROVE.getValue()){
            // không phải đang chờ phê duyệt thì chuyển về trang xem
            return "redirect:/loans/" + registerLoan.getId() + "/view";
        }

//        registerLoan.setStatus(4);
//
//        registerLoanDAO.update(registerLoan);

        registerLoanDAO.updateStatus(registerLoan.getId(), RegisterLoanStatusEnums.DISBURSEMENT.getValue());

        return "redirect:/loans/" + registerLoan.getId() + "/view";
    }

    @AdminAuth
    @GetMapping("/{loanId}/money")
    public String getLoanMoney(HttpSession session, HttpServletRequest request, Model model, @PathVariable(name = "loanId") String id){
        UUID loanId = UUID.fromString(id);
        RegisterLoan registerLoan = registerLoanDAO.get(loanId);
        model.addAttribute("registerLoan", registerLoan);
        model.addAttribute("registerLoanViewUrl", "/loans/" + registerLoan.getId() + "/view");
        model.addAttribute("registerLoanPayInterestUrl", "/loans/" + registerLoan.getId() + "/pay-interest");
        model.addAttribute("returnMoneyUrl", "/loans/" + registerLoan.getId() + "/return-money");
        return "registerLoan/loanMoney";
    }

    @AdminAuth
    @GetMapping("/{loanId}/pay-interest")
    public String payInterest(HttpSession session, HttpServletRequest request, Model model, @PathVariable(name = "loanId") String id){
        UUID loanId = UUID.fromString(id);
        RegisterLoan registerLoan = registerLoanDAO.get(loanId);
        if(registerLoan.getStatus() != RegisterLoanStatusEnums.DISBURSEMENT.getValue()){
            return "redirect:/";
        }
        registerLoan.setPayInterestDate(new Date());
        registerLoan.setNumMonthPayInterest(registerLoan.getNumMonthPayInterest() + registerLoan.getNumMonthInterest());
        registerLoanDAO.updatePayInterest(registerLoan);
        return "redirect:/loans/" + registerLoan.getId() + "/money";
    }

    @AdminAuth
    @GetMapping("{loanId}/return-money")
    public String returnMoney(HttpSession session, HttpServletRequest request, Model model, @PathVariable(name = "loanId") String id){
        UUID loanId = UUID.fromString(id);
        RegisterLoan registerLoan = registerLoanDAO.get(loanId);
        if(registerLoan.getStatus() != RegisterLoanStatusEnums.DISBURSEMENT.getValue()){
            return "redirect:/";
        }
//        registerLoan.setStatus(5);
//        registerLoanDAO.update(registerLoan);

        registerLoanDAO.updateStatus(registerLoan.getId(), RegisterLoanStatusEnums.RETURNMONEY.getValue());

        return "redirect:/loans/" + registerLoan.getId() + "/view";
    }
}
