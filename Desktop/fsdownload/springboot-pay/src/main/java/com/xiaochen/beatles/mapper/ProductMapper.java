package com.xiaochen.beatles.mapper;


import com.xiaochen.beatles.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    int countByExample(Product product);

    int deleteByExample(Product product);

    int deleteByPrimaryKey(String id);

    /**
     * @param record
     * @return
     */
    @Insert(value = "INSERT INTO  product(id,name,price)" + "VALUE(#{id},#{name},#{price})")
    int insert(Product record);

    /**
     * @param record
     * @return
     */
    @InsertProvider(type = ProductDynamicSqlProvider.class, method = "insertSelective")
    int insertSelective(Product record);

    /**
     * @param example
     * @return
     */
    @Results(
            id = "id", value = {
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price")
    })
    @Select(value = "select *from product")
    List<Product> selectByExample();

    /**
     * @param id
     * @return
     */
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
    })
    @Select(value = "select *from product where id=#{id}")
    Product selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") Product product);

    int updateByExample(@Param("record") Product record, @Param("example") Product product);

    /**
     * @param record
     * @return
     */
    @UpdateProvider(type = ProductDynamicSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}