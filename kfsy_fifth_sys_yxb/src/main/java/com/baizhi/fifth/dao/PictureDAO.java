package com.baizhi.fifth.dao;


import com.baizhi.fifth.entity.Picture;

import java.util.List;

public interface PictureDAO extends BaseDAO<Picture> {
    //根据药品主键查图片
    List<Picture> queryByMedicineId(String id);

}
