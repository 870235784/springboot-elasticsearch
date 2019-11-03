package com.tca.elasticsearch.repository;

import com.tca.elasticsearch.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author zhoua
 * @Param:
 * 	    Item:为实体类
 * 	    Long:为Item实体类中主键的数据类型
 * @Date 2019/10/29
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    /**
     * 根据价格区间查询
     * @param lowerPrice
     * @param higherPrice
     * @return
     */
    List<Item> findByPriceBetween(Double lowerPrice, Double higherPrice);
}
