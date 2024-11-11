package kr.hs.sdh.workbook1.service;

import kr.hs.sdh.workbook1.entity.Login;
import kr.hs.sdh.workbook1.entity.Role;
import kr.hs.sdh.workbook1.repository.LoginRepository;
import kr.hs.sdh.workbook1.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    private final RoleRepository roleRepository;

    public LoginService(LoginRepository loginRepository, RoleRepository roleRepository) {
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
    }

    public Login getMember(String id) {
        return this.loginRepository.findMemberById(id);
    }

    public void removeHamburger(String id) {
        this.loginRepository.deleteMemberById(id);
    }

    public Set<Role> getRoles(String memberId) {

        return new HashSet<>(this.roleRepository.findAllById(memberId));
    }
}
