package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.SysAuth;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysAuthMapper extends BaseMapper<SysAuth> {

    /**
     * 根据用户id获取按钮权限
     */
    @Select("select sa.id,sa.auth_name,sa.auth_code,sa.parent_id from sys_auth sa left join role_auth ra on sa.id = ra.auth_id where " +
            "sa.is_del = 0 and sa.type = 0 and ra.role_id in " +
            "(select sr.id from sys_role sr join user_role ur on sr.id = ur.role_id where sr.is_del = 0 and sr.is_enable = 0 and ur.user_id = #{userId})" +
            " order by sa.parent_id")
    List<SysAuth> getAuthByUserId(Integer userId);

    /**
     * 根据用户id获取菜单权限
     */
    @Select("select sa.id,sa.auth_name,sa.auth_code,sa.parent_id,sa.menu_url,sa.menu_icon,sa.menu_order from sys_auth sa left join role_auth ra on sa.id = ra.auth_id where " +
            "sa.is_del = 0 and sa.type = 1 and ra.role_id in " +
            "(select sr.id from sys_role sr join user_role ur on sr.id = ur.role_id where sr.is_del = 0 and sr.is_enable = 0 and ur.user_id = #{userId})" +
            " group by sa.id order by sa.menu_order")
    List<SysAuth> getMenuByUserId(Integer userId);

    /**
     * 根据权限id获取权限详情
     */
    @Select("select id,auth_name,auth_code,type,menu_url,parent_id,menu_icon,menu_order from sys_auth where id = #{id} and is_del=0")
    SysAuth getOneAuth(Integer id);

    /**
     * 根据权限标识统计权限数量
     */
    @Select("select count(1) from sys_auth where auth_code = #{authCode} and is_del=0")
    Integer countAuthCode(String authCode);

    /**
     * 权限取消全部角色
     */
    @Delete("delete from role_auth where auth_id = #{authId}")
    void deleteRole(Integer authId);

    /**
     * 删除权限
     */
    @Update("update sys_auth set is_del = 1 where id = #{id} and is_del = 0")
    Boolean delAuth(Integer id);

    @Select("select DISTINCT sa.id,sa.auth_name,sa.auth_code,sa.parent_id,sa.type,sa.menu_icon,sa.menu_order from sys_auth sa left join role_auth ra on sa.id = ra.auth_id where " +
            "sa.is_del = 0 and ra.role_id = #{roleId} order by sa.parent_id")
    List<SysAuth> getAuthByRole(Integer roleId);

    /**
     * 根据角色获取权限id
     */
    @Select("select auth_id from role_auth where role_id in (${roleId})")
    List<Integer> getIdByRole(String roleId);
}
