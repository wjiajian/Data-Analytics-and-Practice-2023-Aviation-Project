package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.Aftn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
public interface AftnMapper extends BaseMapper<Aftn> {

    //获取机场飞机起飞的数量
    @Select("select kg_airport.AIRPORT_CNAME as airCname,count(*) as adepCount\n" +
            "from kg_airport INNER JOIN kg_aftn on kg_airport.AIRPORT_CODE4 = kg_aftn.adep\n" +
            "GROUP BY kg_aftn.adep;")
    List<Aftn> getAftnByAdep();

    //获取机场飞机降落的数量
    @Select("select kg_airport.AIRPORT_CNAME as airCname,count(*) as adesCount\n" +
            "from kg_airport INNER JOIN kg_aftn on kg_airport.AIRPORT_CODE4 = kg_aftn.ades\n" +
            "GROUP BY kg_aftn.ades")
    List<Aftn> getAftnByAdes();

    @Select("SELECT\n" +
            "adep.airCname,adep.adepCount,ades.adesCount\n" +
            "FROM\n" +
            "(\n" +
            "SELECT\n" +
            "kg_airport.AIRPORT_CNAME AS airCname,\n" +
            "count(*) AS adesCount \n" +
            "FROM\n" +
            "kg_airport\n" +
            "INNER JOIN kg_aftn ON kg_airport.AIRPORT_CODE4 = kg_aftn.ades \n" +
            "GROUP BY\n" +
            "kg_aftn.ades \n" +
            ") AS ades right JOIN (\n" +
            "SELECT\n" +
            "kg_airport.AIRPORT_CNAME AS airCname,\n" +
            "count(*) AS adepCount \n" +
            "FROM\n" +
            "kg_airport\n" +
            "INNER JOIN kg_aftn ON kg_airport.AIRPORT_CODE4 = kg_aftn.adep \n" +
            "GROUP BY\n" +
            "kg_aftn.adep \n" +
            ") AS adep ON ades.airCname = adep.airCname")
    List<Aftn> getAftnList();
}