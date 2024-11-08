package kr.hs.sdh.workbook1.service;

import kr.hs.sdh.workbook1.entity.Login;
import kr.hs.sdh.workbook1.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login getMember(String id) {
        return this.loginRepository.findMemberById(id);
    }

    public void removeHamburger(String id) {
        this.loginRepository.deleteMemberById(id);
    }
}
