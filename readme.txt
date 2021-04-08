高并发点赞项目
应用场景：处理高并发访问业务

传统点赞功能设计方案弊端：
    1.服务器频繁创建线程
    2.数据库连接池中的连接数有限：Redis缓存解决
    3.点赞功能是同步处理：MQ异步处理解决

实体类：
1.User:
    id
    name
    acount
2.Mood:
    id
    content
    praiseNum
    userId
    publishTime
3.UserMoodPraiseRel
    id
    userId
    moodId

Dao层：
1.UserDao:
    find(id)
2.MoodDao:
    findAll()
    update(mood)
    findById(id)
3.UserMoodPraiseRelDao:
    save(userMoodPraiseRel)

Mapper映射：
1.UserMapper
2.MoodMapper
3.UserMoodPraiseRelMapper

DTO类：
1.MoodDTO:
    userName
    userAcount
2.UserDTO

Service层：
1.UserService:
    find(id)
2.MoodService:
    findAll()
    praiseMood(userId,moodId)
    update(mood),findById(id)
    praiseMoodForRedis(userId,moodId)
    findAllForRedis()
3.UserMoodPraiseRelService:
    save(userMoodPraiseRel)

Controller层：
1.UserController
2.MoodController
    findAll:moodService.findAll() --> return view
    priase(moodId,userId):moodService.priaseMood --> moodService.findAll --> return view

视图层：
mood.jsp

定时器类：
PraiseDataSaveDBJob:
    1.取出Redis缓存中所有被点赞的说说id
    2.从Redis缓存中通过说说id获取所有点赞的用户id列表
    3.step3:循环保存mood_id和user_id的关联关系到MySQL数据库
    4.更新说说点赞数量:说说的总点赞数量=Redis点赞数量+数据库的点赞数量

mq类：
    生产者：MoodProducer
    消费者：MoodConsumer
收到点赞指令后，获取userId和moodId，生成moodDTO对象，使用moodProducer对象将消息moodDTO发送到目标地点
在配置文件中设置了moodConsumer对象监听目标地点，可调用其onMessage方法