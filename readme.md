1.下载elasticsearch, 解压

2.elasticsearch-head插件
    2.1 下载, 解压
    2.2 下载nodejs, 安装 
            验证: node -v
    2.3 安装grunt, 执行命令 npm install -g grunt -cli
            验证: grunt -version
    2.4 插件安装: 在主目录下, 执行 npm install
    2.5 修改elasticsearch 配置文件 elasticsearch.yml
            http.cors.enabled: true
            http.cors.allow-origin: "*"
    2.6 启动elasticsearch-head插件 
            进入 elasticsearch-head-master, grunt server 或 npm run start
            
3.基本概念
    索引 index    --->    database 数据库
    类型 type     --->    table 表
    文档 document --->    row 行
    字段 field    --->    column 列
    分片 shard  
    
4.使用springboot-data 操作elasticsearch
    4.1 修改 elasticsearch 主配置文件 elasticsearch.yml
            cluster.name: my-application
            node.name: node-1
            network.host: 0.0.0.0
            http.port: 9200
            discovery.seed_hosts: ["127.0.0.1", "[::1]"]
            cluster.initial_master_nodes: ["node-1"]
    4.2 添加依赖
            <!-- elasticsearch启动器 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
            </dependency>
    4.3 添加主配置
    4.4 创建实体类
            @Document 作用在实体类，标记实体类为文档对象，一般有两个属性
                indexName：对应索引库名称
                type：对应在索引库中的类型
                shards：分片数量，默认5
                replicas：副本数量，默认1
            @Id 作用在成员变量，标记一个字段作为id主键
            @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
                type：字段类型，是枚举：FieldType，可以是text、long、short、date、integer、object等
                text：存储数据时候，会自动分词，并生成索引
                keyword：存储数据时候，不会分词建立索引
                Numerical：数值类型，分两类
                            基本数据类型：long、interger、short、byte、double、float、half_float
                            浮点数的高精度类型：scaled_float
                            需要指定一个精度因子，比如10或100。elasticsearch会把真实值乘以这个因子后存储，取出时再还原。
                Date：日期类型
                elasticsearch可以对日期格式化为字符串存储，但是建议我们存储为毫秒值，存储为long，节省空间。
                index：是否索引，布尔类型，默认是true
                store：是否存储，布尔类型，默认是false
                analyzer：分词器名称，这里的ik_max_word即使用ik分词器

