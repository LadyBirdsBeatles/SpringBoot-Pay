package com.xiaochen.beatles.mapper;

import com.xiaochen.beatles.pojo.Product;
import org.apache.ibatis.jdbc.SQL;

public class ProductDynamicSqlProvider {
    /**
     * @param product
     * @return
     */
    public String insertSelective(Product product) {
        return new SQL() {
            {
                INSERT_INTO("product");
                if (product.getName() != null) {
                    VALUES("name", product.getName());
                }
                if (product.getPrice() != null) {
                    VALUES("price", product.getPrice());
                    //SET("name", person.getName());
                    //Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'Java' in 'field list'
                }

            }
        }.toString();
    }

    /**
     * @return
     */
    public String updateByPrimaryKeySelective(Product product) {
        return new SQL() {
            {
                UPDATE("product");
                if (product.getName() != null) {
                    SET("name={name}");
                }
                if (product.getPrice() != null) {
                    SET("price={price}");
                }
                WHERE("id= #{id}");
            }

        }.toString();
    }
}
