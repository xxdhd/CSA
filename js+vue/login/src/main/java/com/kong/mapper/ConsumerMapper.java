package com.kong.mapper;


import com.kong.pojo.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


//表示是mybatis的mapper类
@Mapper
@Repository
public interface ConsumerMapper {
    List<Consumer> queryConsumerList();

    Consumer queryById(int id);

    int add(Consumer consumer);

    int update(Consumer consumer);
    int delete(int id);

}
