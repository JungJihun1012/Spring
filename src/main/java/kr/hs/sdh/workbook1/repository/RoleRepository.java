package kr.hs.sdh.workbook1.repository;

import kr.hs.sdh.workbook1.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRepository {
    @Select("select * from role where id = #{memberId}")
    public List<Role> findAllById(final String memberId);
}
