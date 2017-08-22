package com.yufeimen.application.service;

import com.yufeimen.application.mapper.SalaryMapper;
import com.yufeimen.application.model.Salary;
import com.yufeimen.application.model.XLSModel;
import com.yufeimen.application.utils.ObjectUtil;
import com.yufeimen.application.utils.XLSUtil;
import com.yufeimen.application.utils.XLSXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

@Service
public class FileService {

    @Autowired
    SalaryMapper salaryMapper;

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
            System.out.println("size:"+model.getContent().size());
            for (int i = 0; i < model.getContent().size(); i++) {
                Salary salary=new Salary();
                String[] data = new String[model.getContent().get(i).size()];
                for (int j = 0; j < model.getContent().get(i).size(); j++) {
                    data[j] = (String) model.getContent().get(i).get(j);
                    System.out.print(data[j]+"   ");
                }
                salary=new ObjectUtil<Salary>().initData(salary,data,6);
                salaryMapper.insert(salary);
            }

        }

        return files;
    }

}
