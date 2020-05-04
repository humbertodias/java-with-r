library(sqldf)
con <- dbConnect(SQLite())
s <- 'create table tt("id" integer primary key, "name" TEXT)'
dbSendQuery(con, s)
dbSendQuery(con, "insert into tt values(1, 'Hello')")
dbSendQuery(con, "insert into tt values(2, 'World')")
rows <- dbGetQuery(con, "select * from tt")
dbDisconnect(con)            # Close connection
print(rows[2])