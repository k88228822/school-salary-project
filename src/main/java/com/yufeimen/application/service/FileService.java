package com.yufeimen.application.service;

import com.yufeimen.application.mapper.SalaryMapper;
import com.yufeimen.application.mapper.UserMapper;
import com.yufeimen.application.model.Salary;
import com.yufeimen.application.model.XLSModel;
import com.yufeimen.application.utils.DateUtil;
import com.yufeimen.application.utils.ObjectUtil;
import com.yufeimen.application.utils.XLSUtil;
import com.yufeimen.application.utils.XLSXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class FileService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public SalaryMapper salaryMapper;

    public LinkedList<XLSModel> upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException {
        LinkedList<XLSModel> files = new LinkedList<XLSModel>();
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;
        while (itr.hasNext()) {
            XLSModel model = null;
            mpf = request.getFile(itr.next());
            String fileType = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".") + 1);

            if (fileType.equalsIgnoreCase("xlsx")) {
                model = new XLSXUtil().getFromStream(mpf.getInputStream());
            } else if (fileType.equalsIgnoreCase("xls")) {
                model = new XLSUtil().getFromStream(mpf.getInputStream());
            } else {
                throw new RuntimeException("文件类型错误");
            }

            for (int i = 0; i < model.getContent().size(); i++) {
                Salary salary=new Salary();
                String[] data = new String[model.getContent().get(i).size()];
                for (int j = 0; j < model.getContent().get(i).size(); j++) {
                    data[j] = (String) model.getContent().get(i).get(j);
                    System.out.print(data[j]+"   ");
                }
                salary=new ObjectUtil<Salary>().initData(salary,data,6);
                checkAndInsertSalary(salary);
            }

        }

        return files;
    }


    public void checkAndInsertSalary(Salary salary){
       if( userMapper.selectByName(salary.getName()+"").size()==0){
           throw new RuntimeException("用户："+salary.getName()+"尚未注册，无法上传数据");
       }

        Map<String,Object> map=new HashMap<>();
        map.put("firstDate", DateUtil.getMonthFirst(salary.getTime()));
        map.put("lastDate",DateUtil.getMonthLast(salary.getTime()));
        map.put("username",salary.getName());
        List<Salary> salaries=salaryMapper.selectDataByMonth(map);
        if(salaries.size()>0){
            salary.setId(salaries.get(0).getId());
            salaryMapper.updateByPrimaryKey(salary);
        }else {
            salaryMapper.insert(salary);
        }
    }

}
