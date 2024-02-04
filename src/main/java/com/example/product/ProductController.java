package com.example.product;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProductController {
	@Autowired
	ProductDAO productDao;
	@RequestMapping("/list")
	public List<Map<String, Object>> list(@RequestParam(defaultValue="") String product_name){
		return productDao.list(product_name);
	}

	@RequestMapping("/insert")
	public void insert(@RequestParam Map<String, Object> map, @RequestParam(required = false)MultipartFile img, HttpServletRequest request) {
		String filename="-";
		if(img != null && !img.isEmpty()) {
			filename=img.getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path=application.getRealPath("/static/images/");
				img.transferTo(new File(path + filename));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("filename", filename);
		productDao.insert(map);
	}


}
