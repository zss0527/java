### hierarchy of key

key in redis allow multiple words hierarchy  
general hierarchy: `projectname:businessname:type:id`  
eg: hima:product:1, hima:user:1  
if value is a object, need serialize this object to json to save.

### generic commands  

**`keys pattern`**: query all keys matched the pattern  
**`del key [key...]`**: delete the key  
**`exists key`**: check if the key exists  
**`expire key seconds [confition]`**: set expire time for the key  
**`ttl key`**: check left time for the key

### string commands  

value type for string in redis is character, but the character is divided info 3 kind:  
1. string: normal string  
2. int: integer, can decrement and increment  
3. float: float, can decrement and increment  

size for string in redis can't exceed 512m.  
**`set key value [conditon] [get] [expiration]`**: create or update one key value  
**`get key`**: retrieve value for the key  
**`mset key1 value1 [key2 value2 ...]`**: create or update many key value  
**`mget key1 [key2 ...]`**: retrieve many value for these keys  
**`incr key`**: let this integer key increment 1   
**`incrby key seconds`**: let this integer key increment seconds  
**`incrbyfloat key seconds`**: let this float key increment seconds  
**`setnx key value`**: Set the value of a key, only if the key does not exist  
**`setex key seconds`**: Set the value and expiration of a key

### hash commands  

**`HSET key field_value [field_value ...]`**: Set the string value of a hash field  
**`HGET key field`**: Get the value of a hash field  
**`HMSET key field_value [field_value ...]`**: Set multiple hash fields to multiple values  
**`HMGET key field [field ...]`**: Get the values of all the given hash fields  
**`HGETALL key`**: Get all the fields and values in a hash  
**`HKEYS key`**: Get all the fields in a hash  
**`HVALS key`**: Get all the values in a hash  
**`HINCRBY key field increment`**: Increment the integer value of a hash field by the given number  
**`HSETNX key field value`**: Set the value of a hash field, only if the field does not exist  

### list commands  

**`LPUSH key element [element ...]`**: Prepend one or multiple elements to a list  
**`LPOP key [count]`**: Remove and get the first elements in a list  
**`RPUSH key element [element ...]`**: Append one or multiple elements to a list  
**`RPOP key [count]`**: Remove and get the last elements in a list  
**`LRANGE key start stop`**: Get a range of elements from a list  
**`BLPOP key [key ...] timeout`**: Remove and get the first element in a list, or block until one is available  
**`BRPOP key [key ...] timeout`**: Remove and get the last element in a list, or block until one is available  

### set commands  

**`SADD key member [member ...]`**: Add one or more members to a set  
**`SREM key member [member ...]`**: Remove one or more members from a set  
**`SCARD key`**: Get the number of members in a set  
**`SISMEMBER key member`**: Determine if a given value is a member of a set  
**`SMEMBERS key`**: Get all the members in a set  
**`SINTER key [key ...]`**: Intersect multiple sets  
**`SDIFF key [key ...]`**: Subtract multiple sets  
**`SUNION key [key ...]`**: Add multiple sets  

### sortedset commands  
**`ZADD key [condition] [comparison] [change] [increment] score_member [score_member ...]`**: Add one or more members to a sorted set, or update its score if it already exists  
**`ZREM key member [member ...]`**: Remove one or more members from a sorted set  
**`ZSCORE key member`**: Get the score associated with the given member in a sorted set  
**`ZRANK key member`**: Determine the index of a member in a sorted set  
**`ZCARD key`**: Get the number of members in a sorted set  
**`ZCOUNT key min max`**: Count the members in a sorted set with scores within the given values  
**`ZINCRBY key increment member`**: Increment the score of a member in a sorted set  
**`ZRANGE key min max [sortby] [rev] [offset_count] [withscores]`**: Return a range of members in a sorted set  
**`ZRANGEBYSCORE key min max [withscores] [offset_count]`**: Return a range of members in a sorted set, by score  
**`ZDIFF numkeys key [key ...] [withscores]`**: Subtract multiple sorted sets  
**`ZINTER numkeys key [key ...] [weight [weight ...]] [aggregate] [withscores]`**: Intersect multiple sorted sets  
**`ZUNION numkeys key [key ...] [weight [weight ...]] [aggregate] [withscores]`**: Add multiple sorted sets  


