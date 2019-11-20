###mysql
* 停止mysql:/etc/init.d/mysql stop
* 重启mysql： systemctl start mysql.service
* 查看mysql状态： service mysqld status
* 修改密码加密方式，改成mysql_native_password

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
###canal

/opt/canal/bin
* 启停服务: ./startup.sh    ./stop.sh
* 日志/opt/canal/logs/example/example.log

###redis
* 目录：/opt/redis-5.0.4/bin
* 启停：./redis-server /opt/redis-5.0.4/redis.conf
./bin/redis-cli shutdown
* 启停时出现:(error) NOAUTH Authentication required.
这个错误是因为没有用密码登陆认证 , 先输入密码试试 .
进入redis-cli    输入auth "yourpassword"
* 展示所有key：key *
* 查看key的value:get key值




