TwoSum问题

# Hash

hash的实现,：
    - 使用256数组  26数组
    - HashMap

# Cache

Hash是无序的，所以一般需要一个双向链表来记录顺序，cache经常使用到Hash+DoubleList

LinkedHashMap的实现就是一个LRU，其内部就是Hash+双指针