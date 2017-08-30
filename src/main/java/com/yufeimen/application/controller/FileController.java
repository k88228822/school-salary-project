package com.yufeimen.application.controller;

import com.yufeimen.application.model.XLSModel;
import com.yufeimen.application.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @ResponseBody
    @RequestMapping("file/postFile")
    @PreAuthorize("hasRole('ADMIN')")
    public LinkedList<XLSModel> uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException {
        return fileService.upload((MultipartHttpServletRequest) request,response);
    }

}
