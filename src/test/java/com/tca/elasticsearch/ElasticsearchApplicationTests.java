package com.tca.elasticsearch;

import com.tca.elasticsearch.entity.Item;
import com.tca.elasticsearch.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class ElasticsearchApplicationTests {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 创建index
	 */
	@Test
	public void createIndex() {
		log.info("开始创建item index!");
		elasticsearchTemplate.createIndex(Item.class);
		log.info("创建item index成功!");
	}

	/**
	 * 删除index
	 */
	@Test
	public void deleteIndex() {
		log.info("开始删除item index!");
		elasticsearchTemplate.deleteIndex(Item.class);
	}

	/**
	 * 添加document
	 */
	@Test
	public void insertDocument() {
		Item item = Item.builder().id(1L)
				.category("武侠")
				.brand("三联")
				.images("hello")
				.price(38.88D)
				.title("神雕侠侣").build();
		itemRepository.save(item);
		log.info("插入document成功!");
	}

	/**
	 * 批量添加document
	 */
	@Test
	public void insertBatchDocument() {
		List<Item> itemList = new ArrayList<>();
		itemList.add(Item.builder().id(2L)
						.category("武侠")
						.brand("三联")
						.images("hello")
						.price(25.66D)
						.title("射雕英雄传").build());
		itemList.add(Item.builder().id(3L)
						.category("武侠")
						.brand("三联")
						.images("hello")
						.price(26.88D)
						.title("武穆遗书").build());
		itemRepository.saveAll(itemList);
		log.info("批量插入document成功!");
	}

	/**
	 * 根据id更新document (先删除,再插入)
	 */
	@Test
	public void updateDocument() {
		Item item = Item.builder().id(1L)
				.category("武侠")
				.brand("三联")
				.images("hello")
				.price(38.88D)
				.title("九阴真经").build();
		itemRepository.save(item);
	}

	/**
	 * 根据id删除document
	 */
	@Test
	public void deleteDocument() {
		itemRepository.deleteById(1L);
	}

	/**
	 * 基本查询 - 1
	 */
	@Test
	public void queryDocument_1() {
		Iterable<Item> items = itemRepository.findAll(Sort.by("price").ascending());
		items.forEach(item -> System.out.println(item));
	}

	/**
	 * 基本查询 - 2
	 */
	@Test
	public void queryDocument_2() {
		List<Item> items = itemRepository.findByPriceBetween(0D, 30D);
		items.forEach(item -> System.out.println(item));
	}



}
