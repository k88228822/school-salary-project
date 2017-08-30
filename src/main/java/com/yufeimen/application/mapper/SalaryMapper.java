package com.yufeimen.application.mapper;

import com.yufeimen.application.model.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface SalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    List<Salary> selectDataByYear(Map map);

    @Select("select * from SALARY where time=#{date}")
    List<Salary> selectDataByDate(Date date);

    List<Salary> selectDataByMonth(Map map);



}