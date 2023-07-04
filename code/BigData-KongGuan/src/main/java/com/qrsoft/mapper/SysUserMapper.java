package com.qrsoft.mapper;

import com.qrsoft.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysUserMapper {

    @Select("select id,account,password,name,is_enable,type,user_type_id " +
            "from sys_user where account = #{account} and is_del = 0")
    SysUser getByAccount(String account);
}