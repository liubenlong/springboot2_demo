local val = redis.call('get',KEYS[1]);
return val;