package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.MedicineDAO;
import com.baizhi.fifth.dao.PictureDAO;
import com.baizhi.fifth.dao.ProductDAO;
import com.baizhi.fifth.entity.Medicine;
import com.baizhi.fifth.entity.Picture;
import com.baizhi.fifth.entity.Product;
import com.baizhi.fifth.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private PictureDAO pictureDAO;
    @Autowired
    private MedicineDAO medicineDAO;
    //用page分页插件查询管理员
    private Page<Product> pages;


    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Product queryOne(String id) {
        Product product = productDAO.queryOne(id);
        System.out.println(product);
        return product;
    }
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Product> queryLikeName(Integer rows, Integer page, String name) {
        pages=PageHelper.startPage(page, rows);
        productDAO.queryLikeName(name);
        return pages;
    }
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Product> queryAll(Integer rows, Integer page) {
        pages=PageHelper.startPage(page, rows);
        productDAO.queryAll();
        return pages;
    }

    public void delete(String id) {
        //先查出此药品的详细.
        Product product = productDAO.queryOne(id);
        pictureDAO.deleteByMedicineId(product.getMedicine().getId());
        //再删除药品详细
        medicineDAO.delete(product.getMedicine().getId());
        //最后删除药品
        productDAO.delete(id);
    }

    public void insert(Product product) {
        //获得药品
        Medicine medicine = product.getMedicine();
        //获得图片集合
        List<Picture> pictures = medicine.getPictures();


        //药品详细信息入库
        medicine.setId(UUID.randomUUID().toString());
        medicineDAO.insert(medicine);
        //相关图片入库
        for (Picture picture : pictures) {
            picture.setMedicine(medicine);
            picture.setId(UUID.randomUUID().toString());
            pictureDAO.insert(picture);
        }
        //商品入库
        product.setMedicine(medicine);
        product.setId(UUID.randomUUID().toString());
        productDAO.insert(product);
    }

    public void update(Product product) {
        //更新图片
            //删除药品的图片
            pictureDAO.deleteByMedicineId(product.getMedicine().getId());
        //获得药品
        Medicine medicine = product.getMedicine();
        //获得图片集合
        List<Picture> pictures = medicine.getPictures();
            //药品图片入库
            for (Picture picture : pictures) {
                picture.setMedicine(medicine);
                picture.setId(UUID.randomUUID().toString());
                pictureDAO.insert(picture);
            }
        //更新药品和商品
        medicineDAO.update(medicine);
        productDAO.update(product);
    }

    public void alterStock(Product product) {
        productDAO.alterStock(product);
    }
}
