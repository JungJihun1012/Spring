package kr.hs.sdh.workbook1.repository;

import kr.hs.sdh.workbook1.entity.Login;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginRepository {

    @Select("select * from member where id = #{id}")
    Login findMemberById(String id);

    @Delete("delete from member where id = #{id}")
    void deleteMemberById(String id);


}
