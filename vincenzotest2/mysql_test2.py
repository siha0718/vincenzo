import pymysql


conn = pymysql.connect(host='vincenzo-database.cb8vxc2s8vcc.ap-northeast-2.rds.amazonaws.com', user='Vincenzo', password='alswl03240!', db='Vincenzo_database', charset='utf8') 

cursor = conn.cursor() 

sql = "SELECT Size FROM restaurant where Name = '카페코지숙명여대점'" 

cursor.execute(sql)

res = cursor.fetchone()
value = list(res)

print(value[0]) 

conn.commit() 
conn.close() 

values = value[0] / 1.9

print(values)
